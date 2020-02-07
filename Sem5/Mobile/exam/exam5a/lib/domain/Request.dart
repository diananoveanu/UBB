import 'IObject.dart';

class Request implements IObject {
  @override
  int id;
  String title;
  String status;
  String student;
  String pages;
  String usedCount;

  Request.noConstr();

  Request(int id, String title, String state, String student, String pages,
      String usedCount) {
    this.id = id;
    this.title = title;
    this.status = state;
    this.student = student;
    this.pages = pages;
    this.usedCount = usedCount;
  }

  Request.withoutId(
      String title, String status, String student, String pages, String usedCount) {
    this.title = title;
    this.status = status;
    this.student = student;
    this.pages = pages;
    this.usedCount = usedCount;
  }

  @override
  fromJson(obj) {
    this.id = obj['id'];
    this.title = obj['title'];
    this.status = obj['status'];
    this.student = obj['student'];
    this.pages = obj['pages'];
    this.usedCount = obj['usedCount'];
  }

  @override
  IObject objectFromDBRes(Map<String, dynamic> result) {
    Request newObj = Request(
      result['id'],
      result['title'],
      result['status'],
      result['student'],
      result['pages'].toString(),
      result['usedCount'].toString(),
    );
    this.id = result['id'];
    this.title = result['title'];
    this.status = result['status'];
    this.student = result['student'];
    this.pages = result['pages'].toString();
    this.usedCount = result['usedCount'].toString();
    return newObj;
  }

  @override
  Map<String, dynamic> toJson() {
    var map = new Map<String, dynamic>();
    map['title'] = title;
    map['status'] = status;
    map['student'] = student;
    map['pages'] = pages;
    map['usedCount'] = usedCount;
    if (id != null) {
      map['id'] = id.toString();
    }
    return map;
  }

  @override
  Map<String, dynamic> toJsonDB() {
    var map = new Map<String, dynamic>();
    map['title'] = title;
    map['status'] = status;
    map['student'] = student;
    map['pages'] = pages.toString();
    map['usedCount'] = usedCount.toString();
    if (id != null) {
      map['id'] = id;
    }
    return map;
  }

  @override
  String toString() {
    String idStr = id.toString();
    return '{id: $idStr\n, title: $title\n, status: $status\n, student: $student\n, pages: $pages\n, usedCount: $usedCount\n}';
  }
}
