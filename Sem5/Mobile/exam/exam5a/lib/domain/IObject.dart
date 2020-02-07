abstract class IObject {
  int id;

  Map<String, dynamic> toJson();

  Map<String, dynamic> toJsonDB();

  fromJson(dynamic obj);

  IObject objectFromDBRes(Map<String, dynamic> result);
}
