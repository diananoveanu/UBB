import 'dart:convert';

import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/IObject.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/server/ObjectServerHelper.dart';
import 'package:exam/service/ObjectService.dart';
import 'package:exam/tabs/addPage.dart';
import 'package:exam/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:progress_dialog/progress_dialog.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:web_socket_channel/io.dart';

class StudentPage extends StatefulWidget {
  StudentPage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  StudentState createState() => StudentState();
}

class StudentState extends State<StudentPage> {
  ProgressDialog pr;
  static String url = 'http://192.168.0.52:2501';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  IOWebSocketChannel channel;
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);

  List<Request> _list = <Request>[];

  StudentState() {
    try {
      channel = IOWebSocketChannel.connect("ws://192.168.0.52:2501");
      channel.stream.listen((data) {
        var d = jsonDecode(data);
        _showToast( d['title'] + " was added!");
      },
      onError:(error) { _showToast("Socket connection failed"); }
      );
    } catch (e) {
      print("NO MORE WEBSOCKET EXCEPTION");
    }
  }

  _showToast(message){
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
        title: Text("Your Books"),
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
//              sync();
            },
          )
        ],
      ),
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("title: " +
              _list[index].name.toString() +
              "\n" +
              "cost: " +
              _list[index].name.toString() +
              "\n" +
              "estimated cost: " +
              _list[index].name.toString()),
        ),
      ),
    );
  }

  void addRequest() async {
    String name = await getStringValuesSF();
    Navigator.push(
            context, MaterialPageRoute(builder: (context) => AddPage()))
        .then((value) async {
      var id = await service.size();
      var request = value as Request;
      request.id = id * -1;
      bool isActive = false;
      try {
        isActive = await server.isActive();
      }catch(e){
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

  getStringValuesSF() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String stringValue = prefs.getString('name');
    return stringValue;
  }

//  void sync() async {
//    server.synchronize(await getStringValuesSF());
//  }

  _readFromServer() async {
    String name = await getStringValuesSF();
    var items = await server.getAll();
    setState(() {
      _list = items;
    });
  }

  _readFromDb() async {
    var items = await service.getObjects();
    setState(() {
      _list = items;
    });
  }

  void _initList() async {
    //pr.show();
    bool isActive = false;
    try{
      isActive = await server.isActive();
    }catch(e){
      isActive = false;
    }
    if (isActive) {
      _readFromServer();
    } else {
      _readFromDb();
    }
    //pr.hide().then((value) => print("Progress Dialog was hidden: " + value.toString()));
  }
}
