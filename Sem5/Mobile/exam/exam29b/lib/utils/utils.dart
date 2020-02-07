import 'package:exam29b/database/TestObjDbHelper.dart';
import 'package:exam29b/domain/Request.dart';

class Utils {
  static getDbFromJson() {
    Map config = {
      "className": "Request",
      "fields": [
        {"name": "id", "type": "int"},
        {"name": "name", "type": "String"},
        {"name": "student", "type": "String"},
        {"name": "status", "type": "String"},
        {"name": "eCost", "type": "int"},
        {"name": "cost", "type": "int"}
      ]
    };
    var cols = new Map<String, String>();
    var fields = config['fields'];
    fields.forEach((e) => {
          e['name'] == 'id' ? cols[e['name']] = 'int' : cols[e['name']] = 'text'});
    return RequestDbHelper(config['className'], cols, Request.noConstr());
  }
}
