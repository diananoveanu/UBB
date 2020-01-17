import 'dart:convert';

import 'package:first_laboratory/DBHelper.dart';
import 'package:first_laboratory/domain/Meal.dart';
import 'package:http/http.dart' as http;

class ServerHelper{

  static ServerHelper _databaseHelper;
  String url = "http://192.168.0.189:5000/api/item/";

  ServerHelper._createInstance();
  DBHelper local = DBHelper();

  factory ServerHelper(){
    if (_databaseHelper == null) {
      _databaseHelper = ServerHelper
          ._createInstance();
    }
    return _databaseHelper;
  }

  Future<bool> isActive() async {
    var response;

    try {
      response = await http.get(url).timeout(const Duration(seconds: 3));
    } catch (e) {
      return false;
    }

    return response != null && response.statusCode == 201;
  }

  Future<http.Response> insert(Meal t) async {
    var body = t.toMap();
    var response;
    body.remove('id');
    try {
      print("Insert at " + this.url + "\n With Body: " + body.toString());
      response = await http.post(this.url, body: json.encode(body)).timeout(const Duration(seconds: 3));
    } catch (e) {
      return http.Response("error", 404);
    }
    return response;
  }

  Future<int> update(Meal t) async {
    print("Update...");
    var response;
    try {
      response = await http.put(this.url + t.id.toString(), body: json.encode(t.toMap())).timeout(const Duration(seconds: 3));
    } catch (e) {
      print("Response Update... " );
      return 404;
    }
    
    return response.statusCode;
  }

  Future<int> delete(String id) async {
    var response;
    try {
      response = await http.delete(this.url + "del/" + id).timeout(const Duration(seconds: 3));
    } catch (e) {
      return 404;
    }
    return response.statusCode;
  }

  Future<List<Meal>> getItems() async {
    final response = await http.get(this.url);

    var MealsMapList = await json.decode(response.body);

    print("maplist as I got it: " + MealsMapList.toString());

    return List.generate(MealsMapList.length, (i) {
      return Meal(
          MealsMapList[i]['id'],
          MealsMapList[i]['mealDescription'],
          MealsMapList[i]['mealCount'],
      );
    });
  }

  Future<List<Meal>> synchronize() async{
    print("server  active");

    List<Meal> localMeals = await local.getItems();
    List<Meal> serverMeals = await this.getItems();
    for (Meal localMeal in localMeals) {
      print(localMeal);
      if (localMeal.id < 0) {
        print("inserting to server: " + localMeal.toString());
        await this.insert(localMeal);
      }
      local.delete(localMeal.id);
    }

    serverMeals = await this.getItems();

    for (Meal t in serverMeals) {
      local.insert(t);
    }

    localMeals = await local.getItems();

    print("sync finished");
    return Future(() => localMeals);
  }

}