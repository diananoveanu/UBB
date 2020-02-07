import 'IObject.dart';

class Request implements IObject {
  @override
  int id;
  String name;
  String student;
  String status;
  int eCost;
  int cost;

  Request.noConstr();

  Request(
      int id, String name, String student, String state, String eCost, String cost) {
    this.id = id;
    this.name = name;
    this.student = student;
    this.status = state;
    this.eCost = int.parse(eCost);
    this.cost = int.parse(cost);
  }

  Request.withoutId(
      String name, String student, String state, int eCost, int cost) {
    this.name = name;
    this.student = student;
    this.status = state;
    this.eCost = eCost;
    this.cost = cost;
  }

  @override
  fromJson(obj) {
    this.id = obj['id'];
    this.name = obj['name'];
    this.student = obj['student'];
    this.status = obj['status'];
    this.eCost = obj['eCost'];
    this.cost = obj['cost'];
  }

  @override
  IObject objectFromDBRes(Map<String, dynamic> result) {
    Request newObj = Request(result['id'], result['name'], result['student'],
        result['status'], result['eCost'].toString(), result['cost'].toString());
    this.id = result['id'];
    this.name = result['name'];
    this.student = result['student'];
    this.status = result['status'];
    this.eCost = newObj.eCost;
    this.cost = newObj.cost;
    return newObj;
  }

  @override
  Map<String, dynamic> toJson() {
    var map = new Map<String, dynamic>();
    map['name'] = name;
    map['student'] = student;
    map['status'] = status;
    map['eCost'] = eCost;
    map['cost'] = cost;
    if (id != null) {
      map['id'] = id.toString();
    }
    return map;
  }

  @override
  Map<String, dynamic> toJsonDB() {
    var map = new Map<String, dynamic>();
    map['name'] = name;
    map['student'] = student;
    map['status'] = status;
    map['eCost'] = eCost.toString();
    map['cost'] = cost.toString();
    if (id != null) {
      map['id'] = id;
    }
    return map;
  }

  @override
  String toString() {
    String idStr = id.toString();
    return '{id: $idStr\n, name: $name\n, student: $student\n, status: $status\n, eCost: $eCost\n, cost: $cost\n}';
  }
}
