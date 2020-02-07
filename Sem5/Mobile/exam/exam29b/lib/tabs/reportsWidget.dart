import 'package:exam29b/domain/IObject.dart';
import 'package:exam29b/domain/Request.dart';
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

  ReportsState(this._list) {}

  @override
  Widget build(BuildContext context) {
    _list.sort((a, b) => a.cost.compareTo(b.cost));
    var lst = <Request>[];
    _list.reversed.forEach((element) {
      lst.add(element);
    });
    _list = lst;

    return Scaffold(
      body: ListView.builder(
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) => ListTile(
          title: Text("name: " +
              _list[index].name.toString() +
              "\n" +
              "cost: " +
              _list[index].cost.toString() +
              "\n" +
              "estimated cost: " +
              _list[index].eCost.toString()),
          trailing: IconButton(
            onPressed: null,
            icon: Icon(Icons.delete),
          ),
        ),
      ),
    );
  }
}
