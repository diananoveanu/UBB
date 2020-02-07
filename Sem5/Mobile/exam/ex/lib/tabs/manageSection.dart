import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/IObject.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/server/ObjectServerHelper.dart';
import 'package:exam/service/ObjectService.dart';
import 'package:exam/tabs/detailPage.dart';
import 'package:exam/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:logger/logger.dart';
import 'package:progress_dialog/progress_dialog.dart';

class LocationPage extends StatefulWidget {
  LocationPage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  LocationState createState() => LocationState();
}

class LocationState extends State<LocationPage> {
  static String url = 'http://172.30.113.188:2702';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);
  bool isActive = true;

  List<Request> _list = <Request>[];
  List<String> _locations = <String>[];

  var logger = Logger(
    filter: null, // Use the default LogFilter (-> only log in debug mode)
    printer: PrettyPrinter(), // Use the PrettyPrinter to format and print log
    output: null, // Use the default LogOutput (-> send everything to console)
  );

  LocationState() {}

  @override
  void initState() {
    super.initState();
    _readFromServer();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        itemCount: _locations.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text(_locations[index]),
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
    logger.i(
        'Files from location at index ' + index.toString() + " will be shown");
    Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => DetailPage(_locations[index])))
        .then((t) async {
      print(t);

      //      int status = await server.borrow(t);
//      print(status);
//      if (status == 200) {
//        _readFromServer();
//      } else {
//        showAlertDialog("Status", "Cannot access files at location");
//      }
    });
  }

  void showAlertDialog(String title, String message) {
    AlertDialog alertDialog = AlertDialog(
      title: Text(title),
      content: Text(message),
    );
    showDialog(context: context, builder: (_) => alertDialog);
  }

  _showToast(message) {
    Fluttertoast.showToast(
        msg: message,
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.CENTER,
        timeInSecForIos: 2,
        backgroundColor: Colors.pink,
        textColor: Colors.white,
        fontSize: 16.0);
  }

  void _readFromServer() async {
    logger.i("reading from server");
    try {
      List<String> items = await server.getLocations();
      print(items);
      setState(() {
        _locations = items;
      });
    } catch (e) {
      _showToast("Error on fetching locations!");
    }
  }
}
