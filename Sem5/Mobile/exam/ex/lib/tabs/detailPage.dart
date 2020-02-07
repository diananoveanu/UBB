import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/ObjectRepository.dart';
import 'package:exam/server/ObjectServerHelper.dart';
import 'package:exam/service/ObjectService.dart';
import 'package:exam/utils/utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:logger/logger.dart';
import 'package:progress_dialog/progress_dialog.dart';
import 'package:web_socket_channel/io.dart';

class DetailPage extends StatefulWidget {
  final String location;

  const DetailPage(this.location);

  @override
  State createState() {
    return DetailPageState(this.location);
  }
}

class DetailPageState extends State<DetailPage> {
  ProgressDialog pr;
  static String url = 'http://172.30.113.188:2702';
  static ObjectDbHelper dbHelper = Utils.getDbFromJson();
  IOWebSocketChannel channel;
  ObjectServerHelper server =
      ObjectServerHelper(dbHelper, Request.noConstr(), url);
  static ObjectRepository repository = ObjectRepository(dbHelper);
  ObjectService service = ObjectService(repository);

  var logger = Logger(
    filter: null, // Use the default LogFilter (-> only log in debug mode)
    printer: PrettyPrinter(), // Use the PrettyPrinter to format and print log
    output: null, // Use the default LogOutput (-> send everything to console)
  );
  List<Request> _list = <Request>[];

  final String location;

  DetailPageState(this.location) {}

  @override
  Widget build(BuildContext context) {
    pr = new ProgressDialog(context,
        type: ProgressDialogType.Normal, isDismissible: true, showLogs: false);
    return Scaffold(
      appBar: AppBar(
        title: Text("Files from " + this.location),
      ),
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          trailing: IconButton(
            onPressed: () {
              deleteFile(index);
            },
            icon: Icon(CupertinoIcons.delete),
          ),
          title: Text("Name: " +
              _list[index].name.toString() +
              "\n" +
              "Status: " +
              _list[index].status.toString() +
              "\n" +
              "Size: " +
              _list[index].size.toString() +
              "\n" +
              "Location: " +
              _list[index].location.toString() +
              "\n" +
              "Used: " +
              _list[index].usage.toString()),
        ),
      ),
    );
  }

  @override
  initState() {
    super.initState();
    _getAllFromLocation();
  }

  _getAllFromLocation() async {
    logger.i("Server retrieved all files at location " + this.location);

    var items = await server.getAllFromLocation(this.location);
    setState(() {
      _list = items;
    });
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

  void deleteFile(index) async {
    logger.i("File at index " + index.toString() + " was deleted.");
    bool isActive = await server.isActive();
    if (!isActive) {
      _showToast("Delete operation not available offline");
    } else {
      var t = _list[index];
      int status = await server.delete(t.id.toString());
      if (status == 200) {
        _getAllFromLocation();
        setState(() {
          _list.removeAt(index);
        });
      } else {
        _showToast("Delete operation unsuccessful");
      }
      _showToast("Delete operation successful");
      isActive = false;
    }
  }
}
