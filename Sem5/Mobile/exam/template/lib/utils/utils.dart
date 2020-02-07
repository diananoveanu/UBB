import 'package:template/database/TestObjDbHelper.dart';
import 'package:template/domain/TestObj.dart';

class Utils {
  static getDbFromJson() {
    Map config = {
      "className": "TestObj1",
      "fields": [
        {"name": "id", "type": "int"},
        {"name": "title", "type": "String"},
        {"name": "date", "type": "String"}
      ]
    };
    var cols = new Map<String, String>();
    var fields = config['fields'];
    fields.forEach((e) => {
          e['name'] == 'id' ? cols[e['name']] = 'int' : cols[e['name']] = 'text'
        });
    return TestObjDbHelper(config['className'], cols, TestObj.noConstr());
  }
}
