import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:template/domain/TestObj.dart';
import 'package:template/service/TestObjService.dart';
import 'package:template/utils/utils.dart';
import 'package:template/repository/TestObjRepository.dart';

class AddEdit extends StatefulWidget {
  final TestObj t;
  final bool mode;

  const AddEdit(this.t, this.mode);

  @override
  State createState() {
    return AddEditState(t, mode);
  }
}

class AddEditState extends State<AddEdit> {
  TestObj t;
  bool edit;
  TextField bText;
  TextEditingController bController = TextEditingController();
  TextField cText;
  TextEditingController cController = TextEditingController();
  RaisedButton addEditButton;
  TestObjService service =
  TestObjService(TestObjRepository(Utils.getDbFromJson()));
  BuildContext context;
  State parent;

  AddEditState(TestObj t, bool mode) {
    this.t = t;
    this.edit = mode;
    bText = TextField(
      controller: bController,
    );
    cText = TextField(
      controller: cController,
    );
    bController.text = t.title;
    cController.text = t.date;
    if (edit) {
      addEditButton = RaisedButton(
        onPressed: editAnnouncement,
        child: Text('Edit'),
      );
    } else {
      addEditButton = RaisedButton(
        onPressed: addAnnouncement,
        child: Text('Add'),
      );
    }
  }

  Widget _buildTiles(TestObj root) {
    return Scaffold(
        body: Container(
            alignment: Alignment.center,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[bText, cText, addEditButton],
            )));
  }

  @override
  Widget build(BuildContext context) {
    this.context = context;
    return _buildTiles(t);
  }

  addAnnouncement() {
    TestObj t = TestObj.withoutId(
      bController.text,
      cController.text,
    );
    Navigator.pop(context, t);
  }

  editAnnouncement() {
    TestObj t = TestObj(
      this.t.id,
      bController.text,
      cController.text,
    );
    Navigator.pop(context, t);
  }
}
