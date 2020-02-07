import 'package:exam/tabs/manageSection.dart';
import 'package:exam/tabs/recordSection.dart';
import 'package:exam/tabs/topSection.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import 'package:progress_dialog/progress_dialog.dart';

class Home extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HomeState();
  }
}

class _HomeState extends State<Home> {
  bool isActive = true;
  int _currentIndex = 0;
  var logger = Logger(
    filter: null, // Use the default LogFilter (-> only log in debug mode)
    printer: PrettyPrinter(), // Use the PrettyPrinter to format and print log
    output: null, // Use the default LogOutput (-> send everything to console)
  );

  ProgressDialog pr;

  _HomeState() {}

  final List<Widget> _children = [
    RecordPage(title: ""),
    LocationPage(title: ""),
    TopPage(title: ""),
  ];

  @override
  Widget build(BuildContext context) {
    pr = new ProgressDialog(context,
        type: ProgressDialogType.Normal, isDismissible: true, showLogs: false);
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
            icon: Icon(CupertinoIcons.folder_open, color: Colors.grey),
            title: Text("Record",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
            backgroundColor: Colors.white70,
          ),
          new BottomNavigationBarItem(
            icon: Icon(CupertinoIcons.location, color: Colors.grey),
            backgroundColor: Colors.white70,
            title: Text("Manage",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
          ),
          new BottomNavigationBarItem(
            icon: Icon(CupertinoIcons.bookmark, color: Colors.grey),
            title: Text("Top",
                style: new TextStyle(
                    color: const Color(0xFF06244e), fontSize: 14.0)),
            backgroundColor: Colors.white70,
          ),
        ],
      ),
    );
  }

  void onTabTapped(int index) async {
    pr.show();
    new Future.delayed(const Duration(seconds: 1)).then((value) =>
        pr.hide().then((value) => print("WAS HIDDEN: " + value.toString())));
    logger.i("Tab changed to " + index.toString());
    setState(() {
      _currentIndex = index;
    });
  }

  void showAlertDialog(String title, String message) {
    AlertDialog alertDialog = AlertDialog(
      title: Text(title),
      content: Text(message),
    );
    showDialog(context: context, builder: (_) => alertDialog);
  }
}
