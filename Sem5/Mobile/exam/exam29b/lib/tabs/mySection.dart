import 'package:exam29b/database/TestObjDbHelper.dart';
import 'package:exam29b/domain/IObject.dart';
import 'package:exam29b/domain/Request.dart';
import 'package:exam29b/repository/TestObjRepository.dart';
import 'package:exam29b/server/TestOnjServerHelper.dart';
import 'package:exam29b/service/TestObjService.dart';
import 'package:exam29b/tabs/recordRequest.dart';
import 'package:exam29b/utils/utils.dart';
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
  static String url = 'http://192.168.0.52:2902';
  static RequestDbHelper dbHelper = Utils.getDbFromJson();
  RequestServerHelper server =
      RequestServerHelper(dbHelper, Request.noConstr(), url);
  static RequestRepository repository = RequestRepository(dbHelper);
  RequestService service = RequestService(repository);

  List<Request> _list = <Request>[];

  StudentState(this._list) {}

  @override
  Widget build(BuildContext context) {
    pr = new ProgressDialog(context,
    type: ProgressDialogType.Normal, isDismissible: true, showLogs: false);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        actions: <Widget>[
          IconButton(
            icon: Icon(CupertinoIcons.add),
            onPressed: () {
              addRequest();
            },
          )
        ],
      ),
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("name: " +
              _list[index].name.toString() +
              "\n" +
              "cost: " +
              _list[index].cost.toString() +
              "\n" +
              "estimated cost: " +
              _list[index].eCost.toString()),
          trailing: IconButton(
            onPressed: null,
            icon: Icon(Icons.delete),
          ),
        ),
      ),
    );
  }

  void addRequest() async {
    String name = await getStringValuesSF();
    Navigator.push(
            context, MaterialPageRoute(builder: (context) => AddRequest(name)))
        .then((value) async {
      var id = await service.size();

      var request = value as Request;
      request.id = id * -1;

      bool isActive = await server.isActive();
      pr.show();
      if (!isActive) {
        service.addObject(request);
      } else {
        server.addRequest(request);
      }
      await new Future.delayed(const Duration(seconds : 1));
      pr.update(
        progress: 100,
        message: "Please wait...",
        progressWidget: Container(
            padding: EdgeInsets.all(8.0), child: CircularProgressIndicator()),
        maxProgress: 100.0,
        progressTextStyle: TextStyle(
            color: Colors.black, fontSize: 13.0, fontWeight: FontWeight.w400),
        messageTextStyle: TextStyle(
            color: Colors.black, fontSize: 19.0, fontWeight: FontWeight.w600),
      );
      
      pr.hide().then((value) => {print("PR WAS: " + value.toString())});
    });
  }

  getStringValuesSF() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String stringValue = prefs.getString('name');
    return stringValue;
  }
}
