import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/service/ObjectService.dart';

import 'package:exam/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SignInPage extends StatefulWidget {
  SignInPage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _SignInPageState createState() => _SignInPageState();
}

class _SignInPageState extends State<SignInPage> {
  TextStyle style = TextStyle(fontFamily: 'Montserrat', fontSize: 20.0);

  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);



  @override
  Widget build(BuildContext context) {
    TextEditingController ctr = TextEditingController();
    var emailField = CupertinoTextField(
      obscureText: false,
      controller: ctr,
    );
    final loginButon = Material(
      borderRadius: BorderRadius.circular(10.0),
      color: Colors.grey,
      child: CupertinoButton(
        onPressed: () {
          addStringToSF(ctr.text);
        },
        child: Text("Save",
            textAlign: TextAlign.center,
            style: style.copyWith(
                color: Colors.white, fontWeight: FontWeight.bold)),
      ),
    );

    return Scaffold(
      body: Center(
        child: Container(
          color: Colors.white,
          child: Padding(
            padding: const EdgeInsets.all(36.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                SizedBox(height: 45.0),
                emailField,
                SizedBox(height: 25.0),
                SizedBox(
                  height: 35.0,
                ),
                loginButon,
                SizedBox(
                  height: 15.0,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  addStringToSF(String name) async {
    var lst = await service.getObjects();
    lst.forEach((element) {service.deleteObject(element.id);});
    print("RECV NAME: " + name);
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setString('name', name);
  }
}
