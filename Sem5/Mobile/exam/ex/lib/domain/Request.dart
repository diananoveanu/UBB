import 'IObject.dart';

class Request implements IObject {
  @override
  int id;
  String name;
  String status;
  String size;
  String location;
  String usage;

  Request.noConstr();

  Request(int id, String name, String state, String size, String location,
      String usage) {
    this.id = id;
    this.name = name;
    this.status = state;
    this.size = size;
    this.location = location;
    this.usage = usage;
  }

  Request.withoutId(
      String name, String status, String size, String location, String usage) {
    this.name = name;
    this.status = status;
    this.size = size;
    this.location = location;
    this.usage = usage;
  }

  @override
  fromJson(obj) {
    this.id = obj['id'];
    this.name = obj['name'];
    this.status = obj['status'];
    this.size = obj['size'];
    this.location = obj['location'];
    this.usage = obj['usage'];
  }

  @override
  IObject objectFromDBRes(Map<String, dynamic> result) {
    Request newObj = Request(
      result['id'],
      result['name'],
      result['status'],
      result['size'].toString(),
      result['location'],
      result['usage'].toString(),
    );
    this.id = result['id'];
    this.name = result['name'];
    this.status = result['status'];
    this.size = result['size'].toString();
    this.location = result['location'];
    this.usage = result['usage'].toString();
    return newObj;
  }

  @override
  Map<String, dynamic> toJson() {
    var map = new Map<String, dynamic>();
    map['name'] = name;
    map['status'] = status;
    map['size'] = size;
    map['location'] = location;
    map['usage'] = usage;
    if (id != null) {
      map['id'] = id.toString();
    }
    return map;
  }

  @override
  Map<String, dynamic> toJsonDB() {
    var map = new Map<String, dynamic>();
    map['name'] = name;
    map['status'] = status;
    map['size'] = size.toString();
    map['location'] = location;
    map['usage'] = usage.toString();
    if (id != null) {
      map['id'] = id;
    }
    return map;
  }

  @override
  String toString() {
    String idStr = id.toString();
    return '{id: $idStr\n, name: $name\n, status: $status\n, size: $size\n, location: $location\n, usage: $usage\n}';
  }
}
