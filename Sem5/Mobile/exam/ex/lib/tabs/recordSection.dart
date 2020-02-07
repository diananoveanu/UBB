import 'dart:convert';

import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/server/ObjectServerHelper.dart';
import 'package:exam/service/ObjectService.dart';
import 'package:exam/tabs/addPage.dart';
import 'package:exam/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:logger/logger.dart';
import 'package:progress_dialog/progress_dialog.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:web_socket_channel/io.dart';

class RecordPage extends StatefulWidget {
  RecordPage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  RecordState createState() => RecordState();
}

class RecordState extends State<RecordPage> {
  ProgressDialog pr;
  static String url = 'http://172.30.113.188:2702';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  IOWebSocketChannel channel;
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);

  List<Request> _list = <Request>[];
  var logger = Logger(
    filter: null, // Use the default LogFilter (-> only log in debug mode)
    printer: PrettyPrinter(), // Use the PrettyPrinter to format and print log
    output: null, // Use the default LogOutput (-> send everything to console)
  );

  RecordState() {
    logger.i("trying to connect to websocket");
    try {
      channel = IOWebSocketChannel.connect("ws://172.30.113.188:2702");
      channel.stream.listen((data) {
        var d = jsonDecode(data);
        _showToast(d['name'] + " was added!");
      }, onError: (error) {
        _showToast("Socket connection failed");
      });
    } catch (e) {
      print("NO MORE WEBSOCKET EXCEPTION");
    }
  }

  _showToast(message) {
    Fluttertoast.showToast(
        msg: message,
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.CENTER,
        timeInSecForIos: 2,
        backgroundColor: Colors.pink,
        textColor: Colors.white,
        fontSize: 16.0);
  }

  @override
  void initState() {
    super.initState();
    _initList();
  }

  @override
  Widget build(BuildContext context) {
    pr = new ProgressDialog(context,
        type: ProgressDialogType.Normal, isDismissible: true, showLogs: false);
    return Scaffold(
      appBar: AppBar(
        title: Text("Your Files"),
        actions: <Widget>[
          IconButton(
            icon: Icon(CupertinoIcons.add),
            onPressed: () {
              addRequest();
            },
          ),
          IconButton(
            icon: Icon(CupertinoIcons.refresh),
            onPressed: () {
              sync();
            },
          )
        ],
      ),
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("Name: " +
              _list[index].name.toString() +
              "\n" +
              "Status: " +
              _list[index].status.toString() +
              "\n" +
              "Size: " +
              _list[index].size.toString() +
              "\n" +
              "Location: " +
              _list[index].location.toString() +
              "\n" +
              "Used: " +
              _list[index].usage.toString()),
        ),
      ),
    );
  }

  void addRequest() async {
    logger.i("Adding file");
    Navigator.push(context, MaterialPageRoute(builder: (context) => AddPage()))
        .then((value) async {
      var id = await service.size();
      var request = value as Request;
      request.id = id * -1;
      bool isActive = false;
      try {
        isActive = await server.isActive();
      } catch (e) {
        _showToast("Error on checking if the server is active");
        isActive = false;
      }
      pr.show();
      if (isActive) {
        server.addRequest(request);
        server.synchronize();
      } else {
        print("Adding request to db " + request.toString());
        service.addObject(request);
      }
      new Future.delayed(const Duration(seconds: 2)).then((value) =>
          pr.hide().then((value) => print("WAS HIDDEN: " + value.toString())));
      var cpy = _list;
      cpy.add(request);
      setState(() {
        _list = cpy;
      });
    });
  }

  void sync() async {
    server.synchronize();
  }

  _readFromServer() async {
    logger.i("reading from server");
    var items = await server.getAll();
    setState(() {
      _list = items;
    });
  }

  _readFromDb() async {
    logger.i("reading from db");
    var items = await service.getObjects();
    setState(() {
      _list = items;
    });
  }

  void _initList() async {
    bool isActive = false;
    try {
      isActive = await server.isActive();
    } catch (e) {
      isActive = false;
    }
    var itms = await service.getObjects();
    print(itms.length);
    if (itms.length == 0) {
//      _readFromDb();
      if (isActive) {
        _readFromServer();
      } else {
        _showToast("Unable to connect to the internet");
      }
    } else {
      _readFromDb();
    }
  }
}
