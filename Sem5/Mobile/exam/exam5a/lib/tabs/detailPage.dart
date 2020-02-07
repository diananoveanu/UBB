import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/ObjectRepository.dart';
import 'package:exam5a/service/ObjectService.dart';
import 'package:exam5a/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DetailPage extends StatefulWidget {
  final int id;

  const DetailPage(this.id);

  @override
  State createState() {
    return DetailPageState(id);
  }
}

class DetailPageState extends State<DetailPage> {
  String studentName;
  final int id;

//  CupertinoTextField titleText;
//  TextEditingController titleController = TextEditingController();
//  CupertinoTextField statusText;
//  TextEditingController statusController = TextEditingController();
//  CupertinoTextField pagesText;
//  TextEditingController pagesController = TextEditingController();
//  CupertinoTextField usedCountText;
//  TextEditingController usedCountController = TextEditingController();

  RaisedButton doneButton;
  ObjectService service =
      ObjectService(ObjectRepository(Utils.getDbFromJson()));
  BuildContext context;
  State parent;

  DetailPageState(this.id) {
//    titleText = CupertinoTextField(
//      controller: titleController,
//    );
//    statusText = CupertinoTextField(
//      controller: statusController,
//    );
//    pagesText = CupertinoTextField(
//      controller: pagesController,
//    );
//    usedCountText = CupertinoTextField(
//      controller: usedCountController,
//    );

    doneButton = RaisedButton(
      onPressed: borrowBook,
      child: Text('Borrow'),
    );
  }

  Widget _buildTiles() {
    return Scaffold(
        body: Container(
            alignment: Alignment.center,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
//                titleText,
//                statusText,
//                pagesText,
//                usedCountText,
                doneButton
              ],
            )));
  }

  @override
  Widget build(BuildContext context) {
    this.context = context;
    return _buildTiles();
  }

//  addAnnouncement() {
//    Request object = this.r;
//    Navigator.pop(context, object);
//  }

  borrowBook() async {
    String name = await getStringValuesSF();
    List<String> resp = <String>[];
    resp.add(this.id.toString());
    resp.add(name);
    Navigator.pop(context, resp);
  }

  getStringValuesSF() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String stringValue = prefs.getString('name');
    return stringValue;
  }

}
