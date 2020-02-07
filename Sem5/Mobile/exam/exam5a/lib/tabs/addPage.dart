import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/ObjectRepository.dart';
import 'package:exam5a/service/ObjectService.dart';
import 'package:exam5a/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AddPage extends StatefulWidget {
  final String studentName;

  const AddPage(this.studentName);

  @override
  State createState() {
    return AddPageState(studentName);
  }
}

class AddPageState extends State<AddPage> {
  String studentName;
  CupertinoTextField titleText;
  TextEditingController titleController = TextEditingController();
  CupertinoTextField statusText;
  TextEditingController statusController = TextEditingController();
  CupertinoTextField pagesText;
  TextEditingController pagesController = TextEditingController();
  CupertinoTextField usedCountText;
  TextEditingController usedCountController = TextEditingController();

  RaisedButton addEditButton;
  ObjectService service =
      ObjectService(ObjectRepository(Utils.getDbFromJson()));
  BuildContext context;
  State parent;

  AddPageState(this.studentName) {
    titleText = CupertinoTextField(
      controller: titleController,
    );
    statusText = CupertinoTextField(
      controller: statusController,
    );
    pagesText = CupertinoTextField(
      controller: pagesController,
    );
    usedCountText = CupertinoTextField(
      controller: usedCountController,
    );

    addEditButton = RaisedButton(
      onPressed: addAnnouncement,
      child: Text('Add'),
    );
  }

  Widget _buildTiles() {
    return Scaffold(
        appBar: AppBar(
          title: Text("Record a book"),
        ),
        body: Container(
            alignment: Alignment.center,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text("Book title"),
                SizedBox(height: 10),
                titleText,
                SizedBox(height: 40),
                Text("Status"),
                SizedBox(height: 10),
                statusText,
                SizedBox(height: 40),
                Text("Number of pages"),
                SizedBox(height: 10),
                pagesText,
                SizedBox(height: 40),
                Text("Times used"),
                SizedBox(height: 10),
                usedCountText,
                SizedBox(height: 40),
                addEditButton
              ],
            )));
  }

  @override
  Widget build(BuildContext context) {
    this.context = context;
    return _buildTiles();
  }

  addAnnouncement() {
    Request object = Request.withoutId(
        titleController.text,
        statusController.text,
        studentName,
        pagesController.text,
        usedCountController.text);
    Navigator.pop(context, object);
  }
}
