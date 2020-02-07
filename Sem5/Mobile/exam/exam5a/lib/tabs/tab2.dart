import 'package:exam5a/database/ObjectDbHelper.dart';
import 'package:exam5a/domain/IObject.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/ObjectRepository.dart';
import 'package:exam5a/server/ObjectServerHelper.dart';
import 'package:exam5a/service/ObjectService.dart';
import 'package:exam5a/tabs/detailPage.dart';
import 'package:exam5a/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AllPage extends StatefulWidget {
  AllPage({Key key, this.title, this.list}) : super(key: key);
  final String title;
  final List<IObject> list;

  @override
  AllState createState() => AllState(list);
}

class AllState extends State<AllPage> {
  String name = "";
  static String url = 'http://192.168.0.52:2501';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);
  bool isActive = true;

  List<Request> _list = <Request>[];

  AllState(this._list) {}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("title: " +
              _list[index].title.toString() +
              "\n" +
              "used: " +
              _list[index].usedCount.toString() +
              "\n" +
              "pages: " +
              _list[index].pages.toString()),
          trailing: IconButton(
            onPressed: null,
            icon: Icon(CupertinoIcons.info),
          ),
          onTap: () {
            detailTapped(index);
          },
        ),
      ),
    );
  }

  void detailTapped(int index) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => DetailPage(_list[index].id))).then((t) async {
      int status = await server.borrow(t[0], t[1]);
      print(status);
      if (status == 200) {
        _readAvailableServer();
      }
//      int id = await service.editObject(t.id, t);
//      if (id != null && status == 200) {
//        setState(() {
//          _list.removeAt(index);
//          _list.insert(index, t);
////        });}
      else {
        showAlertDialog("Status", "Book can't be borrowed");
        isActive = false;
      }
    });
  }

  void showAlertDialog(String title, String message) {
    AlertDialog alertDialog = AlertDialog(
      title: Text(title),
      content: Text(message),
    );
    showDialog(context: context, builder: (_) => alertDialog);
  }

  void _readAvailableServer() async {
    var items = await server.getAvailable();
    setState(() {
      _list = items;
    });
  }
}
