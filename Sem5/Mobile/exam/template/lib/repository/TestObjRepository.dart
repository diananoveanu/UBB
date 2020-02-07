import 'package:template/database/DBHelper.dart';
import 'package:template/domain/TestObj.dart';
import 'package:template/repository/ObjectRepository.dart';

class TestObjRepository extends ObjectRepository<TestObj>{

  TestObjRepository(DBHelper<TestObj> dbHelper) : super(dbHelper);

}