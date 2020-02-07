//import 'package:exam/database/ObjectDbHelper.dart';
//import 'package:exam/domain/IObject.dart';
//import 'package:exam/domain/Request.dart';
//import 'package:exam/repository/ObjectRepository.dart';
//import 'package:exam/server/ObjectServerHelper.dart';
//import 'package:exam/service/ObjectService.dart';
//import 'package:exam/tabs/detailPage.dart';
//import 'package:exam/utils/utils.dart';
//import 'package:flutter/cupertino.dart';
//import 'package:flutter/material.dart';
//import 'package:fluttertoast/fluttertoast.dart';
//import 'package:progress_dialog/progress_dialog.dart';
//
//class AllPage extends StatefulWidget {
//  AllPage({Key key, this.title}) : super(key: key);
//  final String title;
//
//  @override
//  AllState createState() => AllState();
//}
//
//class AllState extends State<AllPage> {
//  String name = "";
//  static String url = 'http://192.168.0.52:2501';
//  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
//  ObjectServerHelper server =
//      ObjectServerHelper(dbHelper, Request.noConstr(), url);
//  static ObjectRepository repository = ObjectRepository(dbHelper);
//  ObjectService service = ObjectService(repository);
//  bool isActive = true;
//
//  List<Request> _list = <Request>[];
//
//  AllState() {}
//
//  @override
//  void initState() {
//    super.initState();
//    _readFromServer();
//  }
//
//  @override
//  Widget build(BuildContext context) {
//    return Scaffold(
//      body: ListView.builder(
//        itemCount: _list.length,
//        itemBuilder: (BuildContext context, int index) => ListTile(
//          title: Text("title: " +
//              _list[index].title.toString() +
//              "\n" +
//              "used: " +
//              _list[index].usedCount.toString() +
//              "\n" +
//              "pages: " +
//              _list[index].pages.toString()),
//          trailing: IconButton(
//            onPressed: null,
//            icon: Icon(CupertinoIcons.info),
//          ),
//          onTap: () {
//            detailTapped(index);
//          },
//        ),
//      ),
//    );
//  }
//
//  void detailTapped(int index) {
//
//    Navigator.push(
//        context,
//        MaterialPageRoute(
//            builder: (context) => DetailPage(_list[index].id))).then((t) async {
//      int status = await server.borrow(t[0], t[1]);
//      print(status);
//      if (status == 200) {
//        _readFromServer();
//
//      } else {
//        showAlertDialog("Status", "Book can't be borrowed");
//      }
//    });
//  }
//
//  void showAlertDialog(String title, String message) {
//    AlertDialog alertDialog = AlertDialog(
//      title: Text(title),
//      content: Text(message),
//    );
//    showDialog(context: context, builder: (_) => alertDialog);
//  }
//
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
//      var items = await server.getAvailable();
//      setState(() {
//        _list = items;
//      });
//    }catch(e){
//      _showToast("Error on fetching available!");
//    }
//  }
//}
