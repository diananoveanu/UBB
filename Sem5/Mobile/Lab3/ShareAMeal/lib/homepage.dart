import 'dart:convert';
//import 'dart:io';

import 'package:first_laboratory/ServerHelper.dart';
import 'package:first_laboratory/addedit.dart';
import 'package:first_laboratory/domain/Meal.dart';
import 'package:first_laboratory/main.dart';
import 'package:first_laboratory/service/MealService.dart';
import 'package:first_laboratory/themes.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title, this.name, this.image, this.email, this.tBlock}) : super(key: key);
  final String title;
  final String name;
  final String email;
  final NetworkImage image;
  final ThemeBloc tBlock;
  @override
  MyHomePageState createState() => MyHomePageState();

}

class MyHomePageState extends State<MyHomePage> {
  MealService service = MealService();
  ServerHelper server = ServerHelper();
  List<Meal> _list = <Meal>[];
  bool isActive = false;
  
  

  @override
  void initState() {
    super.initState();
    print("I was called");
    _readFromDb();
  }

  _readFromDb() async {
    List items = await service.getMeals();
    print(items);
    setState(() {
      items.forEach((item) {
        this._list.add(item);
      });
    });
  }

  void deleteItem(int index) async {

    if (!isActive){
      showAlertDialog("Status", "Delete operation is not available offline!");
    }
    else{
      Meal t = _list[index];
      int status = await server.delete(t.id.toString());
      int id = await service.deleteMeal(t.id);
      if(status == 204 && id != null)
      {
        setState(() {
          _list.removeAt(index);
        });
      }
      else{
        showAlertDialog("Status", "Delete operation was not successfull!");
        isActive = false;
      }
    }
  }

  void addItem() {
    Navigator.push(context, CupertinoPageRoute(
        builder: (context) => AddEdit(Meal(-8, "MealDescription", 0), false)))
        .then((t) async
            {
              if(!isActive)
              {
                var id = await service.getCount();
                var a = t as Meal;
                a.id = id * -1;
                a = await service.addMeal(a);
                print(await service.getMeals());
                if(a != null)
                {
                  setState((){
                  _list.add(a);})
                ;}
              }
              else{
                
                var response = await server.insert(t);
                if(response.statusCode != 201){
                  showAlertDialog("Status", "Add operation was unsuccessfull!");
                }
                else{
                  var a = t as Meal;
                  var data = json.decode(response.body) as Map;
                  print(data);
                  a.id = data['id'];
                  a = await service.addMeal(a);
                  print(await service.getMeals());
                  if(a != null)
                  {
                    setState((){
                      _list.add(a);})
                    ;}

                }
              }
            });
  }

  void editItem(int index) async{
    if (!isActive){
      showAlertDialog("Status", "Update operation is not available offline!");
    }
    else{
      Navigator.push(context, CupertinoPageRoute(
          builder: (context) => AddEdit(_list[index], true)))
          .then((t) async
              {
                int status = await server.update(t);
                int id = await service.editMeal(t.id, t);
                if(id != null && status == 200)
                {
                  setState((){
                    _list.removeAt(index);
                    _list.insert(index, t);
                  })
                ;}
                else{
                  showAlertDialog("Status", "Update operation was unsuccessfull!");
                  isActive = false;
                }
              });
    }
  }

  void showAlertDialog(String title, String message) {
    showDialog(
      context: context,
      builder: (BuildContext context)
      {
        return new CupertinoAlertDialog(
          title: new Text(title),
          content: new Text(message),
          actions: [
            CupertinoDialogAction(
                isDefaultAction: true,
                child: new Text("Ok"),
                onPressed: () {
                  Navigator.pop(context, 'Cancel');
                }
            ),
            
          ],
        );
      }
  );

   
  }

  Future<bool> sync() async{
    isActive = await server.isActive();
    if(isActive){
      server.synchronize().then((ts) {
        setState(() {
          print(ts);
          this._list.clear();
          this._list.addAll(ts);
        });
        showAlertDialog("Status", "Sync done successfully!");
      });
    }
    else{
      showAlertDialog("Status", "Server is not available!");
    }
    return isActive;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      
      drawer: Drawer(
        child: ListView(
        children: <Widget>[
          UserAccountsDrawerHeader(
            accountName: Text(widget.name),
            accountEmail: Text(widget.email),
            currentAccountPicture: CircleAvatar(
              backgroundImage: widget.image,
            ),
            
          ),
          ListTile(
            title: Text("Go Back"),
            trailing: Icon(Icons.exit_to_app),
            onTap: (){
                Navigator.of(context).pop();
                Navigator.of(context).push(MaterialPageRoute(
                builder: (BuildContext context) => LoginPage()));
            },
          ),
          ListTile(
            title: Text("Change to dark theme"),
            trailing: Icon(Icons.arrow_forward),
            onTap: (){
              widget.tBlock.selectedTheme.add(_buildDarkTheme());
            },
          ),
          ListTile(
            title: Text("Change to light theme"),
            trailing: Icon(Icons.arrow_forward),
            onTap: (){
              widget.tBlock.selectedTheme.add(_buildLightTheme());
            },
          ),
        ],
  ),
      ),
      backgroundColor: Color(0xffDBD2F4),
      appBar: AppBar(
        backgroundColor: Color(0xffDBD2F4),
        title: Text(widget.title),
        actions: <Widget>[
            CupertinoSwitch(
            value: isActive,
            onChanged: (bool value) {
              if(value){
                if(!isActive){
                  print("SYNCHING...");
                  sync().then((onValue){
                    
                  });
                  
                }else{
                  isActive = false;
                }
              }
            },
            activeColor: Colors.green.shade300,
            // onChanged,: ()  {
            //   print("SYNCHING...");
              // sync().then((onValue){
              //   print(onValue);
              // });
            // }
          ),
          CupertinoButton(
              onPressed: addItem,
              child: Text(
                "+",
                style: TextStyle(color: Colors.black.withOpacity(0.6), fontSize: 24),
                ),
              color: Color(0xffDBD2F4),
              padding: const EdgeInsets.all(2.0),
          ),
        ],
      ),
      body: ListView.separated(
        separatorBuilder: (context, index) => Divider(
          color: Colors.black,
        ),
        itemCount: _list.length,
        itemBuilder: (BuildContext context, int index) {
          return Slidable(
            actionPane: SlidableDrawerActionPane(),
            actionExtentRatio: 0.25,
            child: Container(
              color: Color(0xffDBD2F4),
              child: ListTile(
                title: Text(_list[index].toString()),
                onTap: () => editItem(index),
                
              ),
            ),
            secondaryActions: <Widget>[
              IconSlideAction(
                caption: 'Delete',
                color: Colors.redAccent,
                icon: CupertinoIcons.delete,
                onTap: () => deleteItem(index),
              ),
            ],
          );
        }
      ),
      
    );
  }
}

DemoTheme _buildLightTheme() {
    return DemoTheme(
        'light',
        ThemeData(
          brightness: Brightness.light,
          accentColor: Colors.blue,
          primaryColor: Color(0xffDBD2F4),
        ));
  }


  DemoTheme _buildDarkTheme() {
    return DemoTheme(
        'dark',
        ThemeData.dark()
        );
  }