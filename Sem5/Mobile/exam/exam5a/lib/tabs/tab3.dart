import 'dart:math';

import 'package:exam5a/domain/IObject.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:flutter/material.dart';

class ReportsPage extends StatefulWidget {
  ReportsPage({Key key, this.title, this.list}) : super(key: key);
  final String title;
  final List<IObject> list;

  @override
  ReportsState createState() => ReportsState(list);
}

class ReportsState extends State<ReportsPage> {
  List<Request> _list = <Request>[];

  ReportsState(this._list) {
    print("LIST LEN: " + this._list.length.toString());
  }

  @override
  Widget build(BuildContext context) {
    _list.sort((a, b) => a.usedCount.compareTo(b.usedCount));
    var lst = <Request>[];
    _list.reversed.forEach((element) {
      lst.add(element);
    });
    _list = lst;
   // _list = _list.sublist(0, 9);

    return Scaffold(
      body: ListView.builder(
        itemCount: min(10, _list.length),
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("title: " +
              _list[index].title.toString() +
              "\n" +
              "used count: " +
              _list[index].usedCount.toString()),
        ),
      ),
    );
  }
}
