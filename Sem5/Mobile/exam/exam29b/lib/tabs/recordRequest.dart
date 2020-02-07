import 'package:exam29b/domain/Request.dart';
import 'package:exam29b/repository/TestObjRepository.dart';
import 'package:exam29b/service/TestObjService.dart';
import 'package:exam29b/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AddRequest extends StatefulWidget {
  final String studentName;

  const AddRequest(this.studentName);

  @override
  State createState() {
    return AddRequestState(studentName);
  }
}

class AddRequestState extends State<AddRequest> {
  String studentName;
  TextField bText;
  TextEditingController bController = TextEditingController();
  TextField cText;
  TextEditingController cController = TextEditingController();
  TextField dText;
  TextEditingController dController = TextEditingController();
  TextField eText;
  TextEditingController eController = TextEditingController();

  RaisedButton addEditButton;
  RequestService service =
  RequestService(RequestRepository(Utils.getDbFromJson()));
  BuildContext context;
  State parent;

  AddRequestState(this.studentName) {
    bText = TextField(
      controller: bController,
    );
    cText = TextField(
      controller: cController,
    );
    dText = TextField(
      controller: dController,
    );
    eText = TextField(
      controller: eController,
    );

    addEditButton = RaisedButton(
      onPressed: addAnnouncement,
      child: Text('Add'),
    );
  }

  Widget _buildTiles() {
    return Scaffold(
        body: Container(
            alignment: Alignment.center,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[bText, cText, dText, eText, addEditButton],
            )));
  }

  @override
  Widget build(BuildContext context) {
    this.context = context;
    return _buildTiles();
  }

  addAnnouncement() {
    Request t = Request.withoutId(
        bController.text, studentName, cController.text,
        int.parse(dController.text),
        int.parse(eController.text));
    Navigator.pop
    (
    context
    ,
    t
    );
  }
}
