import 'package:exam/database/ObjectDbHelper.dart';
import 'package:exam/domain/Request.dart';

class Utils {
  static getDbFromJson() {
    Map config = {
      "className": "fileManager",
      "fields": [
        {"name": "id", "type": "int"},
        {"name": "name", "type": "String"},
        {"name": "status", "type": "String"},
        {"name": "size", "type": "String"},
        {"name": "location", "type": "String"},
        {"name": "usage", "type": "String"}
      ]
    };
    var cols = new Map<String, String>();
    var fields = config['fields'];
    fields.forEach((e) => {
          e['name'] == 'id' ? cols[e['name']] = 'int' : cols[e['name']] = 'text'});
    return ObjectDbHelper(config['className'], cols, Request.noConstr());
  }
}
