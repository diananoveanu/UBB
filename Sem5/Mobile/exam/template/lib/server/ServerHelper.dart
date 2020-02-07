import 'dart:convert';

import 'package:template/database/DBHelper.dart';
import 'package:template/domain/IObject.dart';
import 'package:http/http.dart' as http;

class ServerHelper<T extends IObject> {
  String url;
  DBHelper local;
  T data;

  ServerHelper(DBHelper local, T data, String url) {
    this.local = local;
    this.data = data;
    this.url = url;
  }

  Future<bool> isActive() async {
    return true;
  }

  Future<http.Response> insert(T t) async {
    var body = t.toJson();
    var response;
    body.remove('id');
    try {
      print("Insert at " + this.url + "\n With Body: " + body.toString());
      response = await http
          .post(this.url + "/book" , body: json.encode(body),
          headers: {'Content-type': 'Application/json'})
          .timeout(const Duration(seconds: 3));
    } catch (e) {
      return http.Response("error", 404);
    }
    print("Response: " + (response as http.Response).body.toString());
    return response;
  }

  Future<int> update(T t) async {
    print("Update...");
    var response;
    try {
      response = await http
          .put(this.url + "/" + t.id.toString(), body: json.encode(t.toJson()))
          .timeout(const Duration(seconds: 3));
    } catch (e) {
      print("Response Update....");
      return 404;
    }
    return response.statusCode;
  }

  Future<int> delete(String id) async {
    var response;
    try {
      response = await http
          .delete(this.url + "/del/" + id)
          .timeout(const Duration(seconds: 3));
    } catch (e) {
      return 404;
    }
    return response.statusCode;
  }

  Future<List<T>> getItems() async {
    final response = await http.get(this.url + '/books');
    var iObjectMapList = await json.decode(response.body);
    print("Map List as I got: " + iObjectMapList.toString());
    return List.generate(iObjectMapList.length, (i) {
      return data.objectFromDBRes(iObjectMapList[i]);
    });
  }

  Future<List<T>> synchronize() async{
    print("Server active");
    List<T> localObjects = await local.getItems();
    List<T> serverObjects = await this.getItems();
    for(var localObj in localObjects){
      print(localObj.toString() + "=====================================");
      if(localObj.id < 0){
        print("Inserting into server: " + localObj.toString());
        await this.insert(localObj);
      }
      local.delete(localObj.id);
    }

    serverObjects = await this.getItems();
    for(var obj in serverObjects){
      local.insert(obj);
    }

    localObjects = await local.getItems();
    print("sync finished!");

    return Future(() => localObjects);
  }
}
