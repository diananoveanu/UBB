//import 'dart:math';
//
//import 'package:exam/database/ObjectDbHelper.dart';
//import 'package:exam/domain/IObject.dart';
//import 'package:exam/domain/Request.dart';
//import 'package:exam/repository/ObjectRepository.dart';
//import 'package:exam/server/ObjectServerHelper.dart';
//import 'package:exam/service/ObjectService.dart';
//import 'package:exam/utils/utils.dart';
//import 'package:flutter/material.dart';
//import 'package:fluttertoast/fluttertoast.dart';
//import 'package:progress_dialog/progress_dialog.dart';
//
//class ReportsPage extends StatefulWidget {
//  ReportsPage({Key key, this.title}) : super(key: key);
//  final String title;
//
//  @override
//  ReportsState createState() => ReportsState();
//}
//
//class ReportsState extends State<ReportsPage> {
//  List<Request> _list = <Request>[];
//  String name = "";
//  static String url = 'http://192.168.0.52:2501';
//  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
//  ObjectServerHelper server =
//      ObjectServerHelper(dbHelper, Request.noConstr(), url);
//  static ObjectRepository repository = ObjectRepository(dbHelper);
//  ObjectService service = ObjectService(repository);
//
//  bool isActive = true;
//
//  @override
//  void initState() {
//    super.initState();
//    _readFromServer();
//  }
//
//  @override
//  Widget build(BuildContext context) {
//    _list.sort((a, b) => a.usedCount.compareTo(b.usedCount));
//    var lst = <Request>[];
//    _list.reversed.forEach((element) {
//      lst.add(element);
//    });
//    _list = lst;
//    // _list = _list.sublist(0, 9);
//
//    return Scaffold(
//      body: ListView.builder(
//        itemCount: min(10, _list.length),
//        itemBuilder: (BuildContext context, int index) => ListTile(
//          title: Text("title: " +
//              _list[index].title.toString() +
//              "\n" +
//              "used count: " +
//              _list[index].usedCount.toString()),
//        ),
//      ),
//    );
//  }
//  _showToast(message){
//    Fluttertoast.showToast(
//        msg: message,
//        toastLength: Toast.LENGTH_SHORT,
//        gravity: ToastGravity.CENTER,
//        timeInSecForIos: 2,
//        backgroundColor: Colors.pink,
//        textColor: Colors.white,
//        fontSize: 16.0);
//  }
//
//  void _readFromServer() async {
//    try {
//      var it = await server.getAll();
//      setState(() {
//        _list = it;
//      });
//    }catch(e){
//      _showToast("Error on fetching all");
//    }
//  }
//}
