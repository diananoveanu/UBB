import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:template/domain/IObject.dart';
import 'package:template/repository/TestObjRepository.dart';
import 'package:template/screens/AddEdit.dart';
import 'package:template/server/TestOnjServerHelper.dart';
import 'package:template/service/TestObjService.dart';
import 'package:template/utils/utils.dart';
import 'package:web_socket_channel/io.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

import 'database/TestObjDbHelper.dart';
import 'domain/TestObj.dart';

void main() => runApp(MyApp());

void testDB() async {
  var dbHelper = Utils.getDbFromJson();
  dbHelper.deleteAll();
  var t = TestObj(-1, 'geo', 'diana');
  await dbHelper.insert(t);
  //t.b = 'Ale';
  await dbHelper.update(t.id, t);
  await dbHelper.delete(2);
  var t1 = await dbHelper.getItem(1);
  print(t1.toString() + "T!!TT!T!T!T!TT!T!TT!T!T!");
  var ts = dbHelper.getItems();
  ts.asStream().forEach((t) => print(t));
}

void testServer() async {
  var server = TestObjServerHelper(Utils.getDbFromJson(), TestObj.noConstr(),
      'http://192.168.0.94:5000/api/item');
  bool isActive = await server.isActive();
  print("Server is: " + isActive.toString());

  var ts = server.getItems();
  ts.asStream().forEach((t) => print(t));

  var t = TestObj.withoutId('b10', 'c10');
  var response = await server.insert(t);
  print(response);
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Text App',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: MyHomePage(title: 'Test App'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;
  final IOWebSocketChannel channel =
      IOWebSocketChannel.connect("ws://192.168.0.52:3000");

  @override
  MyHomePageState createState() => MyHomePageState(channel: channel);
}

class MyHomePageState extends State<MyHomePage> {
  final WebSocketChannel channel;
  static String url = 'http://192.168.0.52:3000';
  static TestObjDbHelper dbHelper = Utils.getDbFromJson();
  TestObjServerHelper server =
      TestObjServerHelper(dbHelper, TestObj.noConstr(), url);
  static TestObjRepository repository = TestObjRepository(dbHelper);
  TestObjService service = TestObjService(repository);
  bool isActive = false;
  List<IObject> _list = <TestObj>[];

  MyHomePageState({this.channel}) {
    channel.stream.listen((data) {
      var d = jsonDecode(data);
      print("RECV FROM WEB SOCKET: " + d.toString());
    });
  }

  @override
  void initState() {
    super.initState();
    print("I was called");
    _readFromDB();
  }

  _readFromDB() async {
    List items = await service.getObjects();
    items.forEach((item) => this._list.add(item));
    print(this._list);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.refresh),
            onPressed: () {
              print("SYNCHING");
              sync();
            },
          )
        ],
      ),
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text(_list[index].toString()),
          onTap: () {
            editItem(index);
          },
          trailing: IconButton(
            icon: Icon(Icons.delete),
            onPressed: () {
              deleteItem(index);
            },
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: addItem,
        tooltip: 'Add',
        child: Icon(Icons.add),
      ),
    );
  }

  void sync() async {
    isActive = await server.isActive();
    if (isActive) {
      server.synchronize().then((ts) {
        setState(() {
          //print(ts);
          this._list.clear();
          this._list.addAll(ts);
        });
      });
      showAlertDialog("Yes!", "SYNC DONE SUCCESSFULLY!");
    } else {
      showAlertDialog("No", "SERVER IS OFFLINE!");
    }
  }

  void showAlertDialog(String title, String message) {
    AlertDialog alertDialog = AlertDialog(
      title: Text(title),
      content: Text(message),
    );
    showDialog(context: context, builder: (_) => alertDialog);
  }

  void deleteItem(index) async {
    if (!isActive) {
      showAlertDialog("Status", "Delete operation is noat available offline!");
    } else {
      var t = _list[index];
      int status = await server.delete(t.id.toString());
      int id = await service.deleteObject(t.id);
      if (status == 204 && id != null) {
        setState(() {
          _list.removeAt(index);
        });
      } else {
        showAlertDialog("Status", "Delete operation was not succesfull");
        isActive = false;
      }
    }
  }

  void addItem() {
    Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => AddEdit(TestObj(-8, "b", "c"), false)))
        .then((t) async {
      if (!isActive) {
        var id = await service.size();
        var a = t as TestObj;
        a.id = id * -1;
        a = await service.addObject(a);
        print(await service.getObjects());
        if (a != null) {
          setState(() {
            _list.add(a);
          });
        }
      } else {
        var response = await server.insert(t);
        if (response.statusCode != 201) {
          showAlertDialog("Status", "Add operation was unsuccesfull");
        } else {
          var a = t as TestObj;
          var data = json.decode(response.body) as Map;
          print(data);
          a.id = data['id'];
          a = await service.addObject(a);
          print(await service.getObjects());
          if (a != null) {
            setState(() {
              _list.add(a);
            });
          }
        }
      }
    });
  }

  void editItem(int index) async {
    if (!isActive) {
      showAlertDialog("Status", "Update opearation is not avaliable offline!");
    } else {
      Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => AddEdit(_list[index], true)))
          .then((t) async {
        int status = await server.update(t);
        int id = await service.editObject(t.id, t);
        if (id != null && status == 200) {
          setState(() {
            _list.removeAt(index);
            _list.insert(index, t);
          });
        } else {
          showAlertDialog("Status", "Update opeartion was unsuccesfull");
          isActive = false;
        }
      });
    }
  }
}
