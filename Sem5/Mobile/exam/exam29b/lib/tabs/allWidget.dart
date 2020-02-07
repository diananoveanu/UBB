import 'package:exam29b/domain/IObject.dart';
import 'package:exam29b/domain/Request.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AllPage extends StatefulWidget {
  AllPage({Key key, this.title, this.list}) : super(key: key);
  final String title;
  final List<IObject> list;

  @override
  AllState createState() => AllState(list);
}

class AllState extends State<AllPage> {
  List<Request> _list = <Request>[];

  AllState(this._list) {}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("name: " +
              _list[index].name.toString() +
              "\n" +
              "estimated cost: " +
              _list[index].eCost.toString()),
          trailing: IconButton(
            onPressed: null,
            icon: Icon(CupertinoIcons.info),
          ),
        ),
      ),
    );
  }
}
