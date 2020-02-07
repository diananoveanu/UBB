import 'dart:math';

import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/server/ObjectServerHelper.dart';
import 'package:exam/service/ObjectService.dart';
import 'package:exam/utils/utils.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:logger/logger.dart';

class TopPage extends StatefulWidget {
  TopPage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  TopState createState() => TopState();
}

class TopState extends State<TopPage> {
  List<Request> _list = <Request>[];

  var logger = Logger(
    filter: null, // Use the default LogFilter (-> only log in debug mode)
    printer: PrettyPrinter(), // Use the PrettyPrinter to format and print log
    output: null, // Use the default LogOutput (-> send everything to console)
  );

  static String url = 'http://172.30.113.188:2702';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);

  bool isActive = true;

  @override
  void initState() {
    super.initState();
    _readFromServer();
  }

  @override
  Widget build(BuildContext context) {
    _list.sort((a, b) => a.usage.compareTo(b.usage));
    var lst = <Request>[];
    _list.reversed.forEach((element) {
      lst.add(element);
    });
    _list = lst;
    _list = _list.sublist(0, 10);

    return Scaffold(
      body: ListView.builder(
        itemCount: min(10, _list.length),
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("name: " +
              _list[index].name.toString() +
              "\n" +
              "status: " +
              _list[index].status.toString() +
              "\n" +
              "location: " +
              _list[index].location.toString() +
              "\n" +
              "usage: " +
              _list[index].usage.toString()),
        ),
      ),
    );
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

  void _readFromServer() async {
    logger.i("reading from server");
    try {
      var it = await server.getAll();
      setState(() {
        _list = it;
      });
    } catch (e) {
      _showToast("Error on fetching all");
    }
  }
}
