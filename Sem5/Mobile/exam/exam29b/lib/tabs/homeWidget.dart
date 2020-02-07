import 'dart:convert';

import 'package:exam29b/database/TestObjDbHelper.dart';
import 'package:exam29b/domain/Request.dart';
import 'package:exam29b/repository/TestObjRepository.dart';
import 'package:exam29b/server/TestOnjServerHelper.dart';
import 'package:exam29b/service/TestObjService.dart';
import 'package:exam29b/tabs/allWidget.dart';
import 'package:exam29b/tabs/reportsWidget.dart';
import 'package:exam29b/tabs/signInWidget.dart';
import 'package:exam29b/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:web_socket_channel/io.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

import 'mySection.dart';

class Home extends StatefulWidget {
  final IOWebSocketChannel channel =
      IOWebSocketChannel.connect("ws://192.168.0.52:2902");

  @override
  State<StatefulWidget> createState() {
    return _HomeState(channel: channel);
  }
}

class _HomeState extends State<Home> {
  final WebSocketChannel channel;

  String name = "";
  static String url = 'http://192.168.0.52:2902';
  static RequestDbHelper dbHelper = Utils.getDbFromJson();
  RequestServerHelper server =
      RequestServerHelper(dbHelper, Request.noConstr(), url);
  static RequestRepository repository = RequestRepository(dbHelper);
  RequestService service = RequestService(repository);
  bool isActive = true;
  int _currentIndex = 0;
  static List<Request> filledList = <Request>[];
  static List<Request> openList = <Request>[];
  static List<Request> studentList = <Request>[];

  _HomeState({this.channel}) {
    channel.stream.listen((data) {
      var d = jsonDecode(data);
      print("RECV FROM WEB SOCKET: " + d.toString());
    });
  }

  final List<Widget> _children = [
    SignInPage(title: "Name?"),
    StudentPage(title: "Your Requests", list: studentList),
    ReportsPage(title: "Reports", list: filledList),
    AllPage(title: "All Open Requests", list: openList),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Flutter App'),
      ),
      body: _children[_currentIndex], // new
      bottomNavigationBar: BottomNavigationBar(
        onTap: onTabTapped, // new
        currentIndex: _currentIndex, // new
        items: [
          new BottomNavigationBarItem(
              icon: Icon(Icons.settings), title: Text("User")),
          new BottomNavigationBarItem(
            icon: Icon(Icons.person),
            title: Text('My Section'),
          ),
          new BottomNavigationBarItem(
            icon: Icon(Icons.album),
            title: Text('Records'),
          ),
          new BottomNavigationBarItem(
              icon: Icon(Icons.people), title: Text('All Section'))
        ],
      ),
    );
  }

  void onTabTapped(int index) async {
    openList.clear();
    filledList.clear();
    _readFromServer();
    String localName = "";
    print(name);
    bool isActive = await server.isActive();
    if (!isActive) {
      var it = await service.getObjects();
      studentList.clear();
      it.forEach((element) {
        studentList.add(element);
      });
    } else {
      if (_currentIndex == 0 && index == 1) {
        localName = await getStringValuesSF();
        if (name == "") {
          name = localName;
          print("NAME" + this.name);

          var it = await server.getStudentAll(localName);
          it.forEach((element) async{
            if (!studentList.contains(element)) {
              studentList.add(element);
              var id = await service.size();
              element.id = id * -1;
//              service.addObject(element);
//              print(element.toString() + " was added");
            }
          });
        } else if (name != localName) {
          name = localName;
          studentList.forEach((el) {
            service.deleteObject(el.id);
          });
          studentList = [];
          print("NAME" + this.name);

          var it = await server.getStudentAll(localName);
          it.forEach((element) async{
            if (!studentList.contains(element)) {
              studentList.add(element);
              var id = await service.size();
              element.id = id * -1;
//              service.addObject(element);
//              print(element.toString() + " was added");
            }
          });
        }
      }
    }
    setState(() {
      _currentIndex = index;
    });
  }

  _readFromDB() async {
    List items = await service.getObjects();

    items.forEach((item) => studentList.add(item));
    print("LIST: " + studentList.toString());
  }

//  void sync() async {
//    isActive = await server.isActive();
//    if (isActive) {
//      server.synchronize().then((ts) {
//        setState(() {
//          //print(ts);
//          _HomeState.filledList.clear();
//          _HomeState.filledList.addAll(ts);
//        });
//      });
//      showAlertDialog("Yes!", "SYNC DONE SUCCESSFULLY!");
//    } else {
//      showAlertDialog("No", "SERVER IS OFFLINE!");
//    }
//  }

  void showAlertDialog(String title, String message) {
    AlertDialog alertDialog = AlertDialog(
      title: Text(title),
      content: Text(message),
    );
    showDialog(context: context, builder: (_) => alertDialog);
  }

  void _readFromServer() async {
    var items = await server.getFilled();
    items.forEach((element) {
      filledList.add(element);
    });

    var itms = await server.getOpen();
    itms.forEach((element) {
      openList.add(element);
    });
    if (name != "") {
      studentList.clear();
      var it = await server.getStudentAll(name);
      it.forEach((element) {
        studentList.add(element);
      });
    }
  }

  @override
  void initState() {
    super.initState();
    print("I was called");
    //_readFromDB();
    _readFromServer();
  }

  getStringValuesSF() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String stringValue = prefs.getString('name');
    return stringValue;
  }
}
