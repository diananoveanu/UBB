import 'package:exam5a/database/ObjectDbHelper.dart';
import 'package:exam5a/domain/Request.dart';

class Utils {
  static getDbFromJson() {
    Map config = {
      "className": "book",
      "fields": [
        {"name": "id", "type": "int"},
        {"name": "title", "type": "String"},
        {"name": "student", "type": "String"},
        {"name": "status", "type": "String"},
        {"name": "pages", "type": "String"},
        {"name": "usedCount", "type": "String"}
      ]
    };
    var cols = new Map<String, String>();
    var fields = config['fields'];
    fields.forEach((e) => {
          e['name'] == 'id' ? cols[e['name']] = 'int' : cols[e['name']] = 'text'});
    return ObjectDbHelper(config['className'], cols, Request.noConstr());
  }
}
