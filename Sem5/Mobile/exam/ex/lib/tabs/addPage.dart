import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/service/ObjectService.dart';
import 'package:exam/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';

class AddPage extends StatefulWidget {
//  final String studentName;

  const AddPage();

  @override
  State createState() {
    return AddPageState();
  }
}

class AddPageState extends State<AddPage> {
//  String studentName;
  CupertinoTextField nameText;
  TextEditingController nameController = TextEditingController();
  CupertinoTextField statusText;
  TextEditingController statusController = TextEditingController();
  CupertinoTextField sizeText;
  TextEditingController sizeController = TextEditingController();
  CupertinoTextField locationText;
  TextEditingController locationController = TextEditingController();
  CupertinoTextField usageText;
  TextEditingController usageController = TextEditingController();

  RaisedButton addButton;
  ObjectService service =
      ObjectService(ObjectRepository(Utils.getDbFromJson()));
  BuildContext context;
  State parent;

  AddPageState() {
    nameText = CupertinoTextField(
      controller: nameController,
    );
    statusText = CupertinoTextField(
      controller: statusController,
    );
    sizeText = CupertinoTextField(
      controller: sizeController,
    );
    locationText = CupertinoTextField(
      controller: locationController,
    );
    usageText = CupertinoTextField(
      controller: usageController,
    );

    addButton = RaisedButton(
      onPressed: addAnnouncement,
      child: Text('Add'),
    );
  }

  Widget _buildTiles() {
    return Scaffold(
        appBar: AppBar(
          title: Text("Add a file"),
        ),
        body: Container(
            alignment: Alignment.center,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text("File name"),
                SizedBox(height: 10),
                nameText,
                SizedBox(height: 40),
                Text("Status"),
                SizedBox(height: 10),
                statusText,
                SizedBox(height: 40),
                Text("Size"),
                SizedBox(height: 10),
                sizeText,
                SizedBox(height: 40),
                Text("Location"),
                SizedBox(height: 10),
                locationText,
                SizedBox(height: 40),
                Text("Usage"),
                SizedBox(height: 10),
                usageText,
                SizedBox(height: 40),
                addButton
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
        nameController.text,
        statusController.text,
        sizeController.text,
        locationController.text,
        usageController.text);
    Navigator.pop(context, object);
  }
}
