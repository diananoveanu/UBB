import 'package:first_laboratory/service/MealService.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'domain/Meal.dart';

class AddEdit extends StatefulWidget {


  final Meal t;
  final bool mode;

  const AddEdit(this.t, this.mode);

  @override
  State createState() {
    return AddEditState(t, mode);
  }
}

class AddEditState extends State<AddEdit>{
  Meal t;
  bool edit;
  TextFormField descriptionText;
  TextFormField countText;
  TextEditingController descriptionController = TextEditingController();
  TextEditingController countController = TextEditingController();
  CupertinoButton addEditButton;
  MealService service = MealService();
  BuildContext context;
  State parent;

  AddEditState(Meal t, bool mode) {
    this.t = t;
    this.edit = mode;
    descriptionText = TextFormField(
      controller: descriptionController,
      decoration: InputDecoration(
       
        hintText: "Meal Description",
        labelText: "Meal Description",
       
      ),
    );
    countText = TextFormField(
      controller: countController,
      decoration: InputDecoration(
       
        hintText: "Meal Count",
        labelText: "Meal Count",
      
      ),
      keyboardType: TextInputType.number,
    );
    descriptionController.text = t.mealDescription;
    countController.text = t.mealCount.toString();
    if (edit) {
    
      addEditButton = CupertinoButton(
        onPressed: editAnnouncement,
        child: Text(
          "Edit",
          style: TextStyle(color: Colors.black.withOpacity(0.6), fontSize: 24),
          ),
        color: CupertinoColors.white,

      );
    } else {
      
      addEditButton = CupertinoButton(
        onPressed: addAnnouncement,
        child: Text(
          "Add",
          style: TextStyle(color: Colors.black.withOpacity(0.6), fontSize: 24),
          ),
        color: CupertinoColors.white,
        
      );
    }
  }

  Text getMiddleText(){
    if(edit){
      return Text("Edit");
    }
    return Text("Add");
  }

  Widget _buildTiles(Meal root) {
    return CupertinoPageScaffold(
      
      backgroundColor: Color(0xffDBD2F4),
        navigationBar: CupertinoNavigationBar(
          previousPageTitle: 'Meal List',
          transitionBetweenRoutes: true,
          middle: getMiddleText(),
          backgroundColor: Color(0xffDBD2F4),
        ),
        key: UniqueKey(),
        child: new Scaffold(
        backgroundColor: Color(0xffDBD2F4),
        body: SafeArea(
          top: false,
          bottom: false,
          child:  new Form(
              key: UniqueKey(),
              autovalidate: true,
              child: new ListView(
                padding: const EdgeInsets.symmetric(horizontal: 16.0),
                children: <Widget>[
                  //descriptionText,
                  descriptionText,
                  countText,
                  new Container(
                    padding: const EdgeInsets.only(top: 20.0),
                    child: addEditButton,
                  )
                ],
              )
            )
        )
      )
    );
  }

  @override
  Widget build(BuildContext context) {
    this.context = context;
    return _buildTiles(t);
  }

  addAnnouncement() {

    Meal t = Meal.withoutID(
        descriptionController.text,
        int.parse(countController.text)
        );
    Navigator.pop(context, t);
  }

  editAnnouncement() {

    Meal t = Meal(
        this.t.id,
        descriptionController.text, 
        int.parse(countController.text),);

    print(t);
    Navigator.pop(context,t);
  }

}