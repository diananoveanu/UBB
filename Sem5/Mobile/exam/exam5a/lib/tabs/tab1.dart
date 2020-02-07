import 'package:exam5a/database/ObjectDbHelper.dart';
import 'package:exam5a/domain/IObject.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/ObjectRepository.dart';
import 'package:exam5a/server/ObjectServerHelper.dart';
import 'package:exam5a/service/ObjectService.dart';
import 'package:exam5a/tabs/addPage.dart';
import 'package:exam5a/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:progress_dialog/progress_dialog.dart';
import 'package:shared_preferences/shared_preferences.dart';

class StudentPage extends StatefulWidget {
  StudentPage({Key key, this.title, this.list}) : super(key: key);
  final String title;
  final List<IObject> list;

  @override
  StudentState createState() => StudentState(list);
}

class StudentState extends State<StudentPage> {
  ProgressDialog pr;
  static String url = 'http://192.168.0.52:2501';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);

  List<Request> _list = <Request>[];

  StudentState(this._list) {}

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
            onPressed: (){
              sync();
            },
          )
        ],
      ),
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("title: " +
              _list[index].title.toString() +
              "\n" +
              "cost: " +
              _list[index].pages.toString() +
              "\n" +
              "estimated cost: " +
              _list[index].usedCount.toString()),
        ),
      ),
    );
  }

  void addRequest() async {
    String name = await getStringValuesSF();
    Navigator.push(
            context, MaterialPageRoute(builder: (context) => AddPage(name)))
        .then((value) async {
      var id = await service.size();
      var request = value as Request;
      request.id = id * -1;
      bool isActive = await server.isActive();
      if (isActive) {
        server.addRequest(request);
        server.synchronize(name);
      } else {
        print("Adding request to db " + request.toString());
        service.addObject(request);
      }
      _list.add(request);
    });
  }

  getStringValuesSF() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String stringValue = prefs.getString('name');
    return stringValue;
  }

  void sync() async {
      server.synchronize(await getStringValuesSF());
  }
}
