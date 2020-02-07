import 'package:template/database/DBHelper.dart';
import 'package:template/domain/TestObj.dart';

class TestObjDbHelper extends DBHelper<TestObj>{
  TestObjDbHelper(String table, Map<String, String> cols, TestObj data) : super(table, cols, data);

}