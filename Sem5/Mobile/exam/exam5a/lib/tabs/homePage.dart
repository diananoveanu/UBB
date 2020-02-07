import 'dart:convert';

import 'package:exam5a/database/ObjectDbHelper.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/ObjectRepository.dart';
import 'package:exam5a/server/ObjectServerHelper.dart';
import 'package:exam5a/service/ObjectService.dart';
import 'package:exam5a/tabs/tab0.dart';
import 'package:exam5a/tabs/tab1.dart';
import 'package:exam5a/tabs/tab2.dart';
import 'package:exam5a/tabs/tab3.dart';
import 'package:exam5a/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:web_socket_channel/io.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

class Home extends StatefulWidget {
  final IOWebSocketChannel channel =
      IOWebSocketChannel.connect("ws://192.168.0.52:2501");

  @override
  State<StatefulWidget> createState() {
    return _HomeState(channel: channel);
  }
}

class _HomeState extends State<Home> {
  final WebSocketChannel channel;

  String name = "";
  static String url = 'http://192.168.0.52:2501';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);
  bool isActive = true;
  int _currentIndex = 0;
  static List<Request> allList = <Request>[];
  static List<Request> openList = <Request>[];
  static List<Request> studentList = <Request>[];

  _HomeState({this.channel}) {
    channel.stream.listen((data) {
      var d = jsonDecode(data);
      Fluttertoast.showToast(
          msg: d['title'] + " was added!",
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.CENTER,
          timeInSecForIos: 1,
          backgroundColor: Colors.red,
          textColor: Colors.white,
          fontSize: 16.0
      );
    });
  }

  final List<Widget> _children = [
    SignInPage(
      title: "",
    ),
    StudentPage(title: "", list: studentList),
    AllPage(list: openList),
    ReportsPage(list: allList),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Exam',
          style: new TextStyle(color: Colors.white70, fontSize: 14.0),
        ),
      ),
      body: _children[_currentIndex], // new
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        onTap: onTabTapped, // new
        currentIndex: _currentIndex, // new
        items: [
          new BottomNavigationBarItem(
            icon: Icon(CupertinoIcons.settings, color: Colors.grey),
            title: Text("Student",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
            backgroundColor: Colors.white70,
          ),
          new BottomNavigationBarItem(
            icon: Icon(CupertinoIcons.book, color: Colors.grey),
            backgroundColor: Colors.white70,
            title: Text("Your Books",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
          ),
          new BottomNavigationBarItem(
            icon: Icon(CupertinoIcons.folder_open, color: Colors.grey),
            backgroundColor: Colors.white70,
            title: Text("Available",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
          ),
          new BottomNavigationBarItem(
            icon: Icon(CupertinoIcons.news, color: Colors.grey),
            backgroundColor: Colors.white70,
            title: Text("Reports",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
          )
        ],
      ),
    );
  }

  void onTabTapped(int index) async {
    bool isActive = await  server.isActive();
    if (index == 1) {
      if(isActive) {
        _readStudentsRequestsFromServer();
      }else{
        _readStudentsRequestsFromDB();
      }
    } else if (index == 2) {
      _readAvailableServer();
    } else if (index == 3) {
      _readAllServer();
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
//          _HomeState.allList.clear();
//          _HomeState.allList.addAll(ts);
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
    if(await server.isActive()) {
      var items = await server.getAll();
      items.forEach((element) {
        allList.add(element);
      });
      var itms = await server.getAvailable();
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
  }

  void _readAllServer() async {
    allList.clear();
    var itms = await server.getAll();
    itms.forEach((element) {
      allList.add(element);
    });
  }

  void _readAvailableServer() async {
    openList.clear();
    var items = await server.getAvailable();
    items.forEach((element) {
      openList.add(element);
    });
  }

  void _readStudentsRequestsFromServer() async {
    String localName = await getStringValuesSF();
    if (name == "") {
      if (localName != "") {
        name = localName;
        print("NAME" + this.name);
        var it = await server.getStudentAll(localName);
        it.forEach((element) async {
          if (!studentList.contains(element)) {
            studentList.add(element);
          }
        });
      }
    } else if (name != "") {
      studentList.clear();
      name = localName;
      print("NAME" + this.name);
      var it = await server.getStudentAll(localName);
      it.forEach((element) async {
        if (!studentList.contains(element)) {
          studentList.add(element);
        }
      });
    }
  }

  @override
  void initState() {
    super.initState();
    print("I was called");
    //_readFromDB();
    openList.clear();
    allList.clear();
    studentList.clear();
    _readFromServer();
  }

  getStringValuesSF() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String stringValue = prefs.getString('name');
    return stringValue;
  }

  void _readStudentsRequestsFromDB() async {
    String nam = await getStringValuesSF();
    if(nam != ""){
      studentList.clear();
      var it = await service.getObjects();
      print("LOCAL OBJECTS ARE: " + it.toString());
      it.forEach((element) { studentList.add(element);});
    }
    else{
      studentList.clear();
    }
  }
}
