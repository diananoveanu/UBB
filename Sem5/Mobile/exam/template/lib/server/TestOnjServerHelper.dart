import 'package:template/database/DBHelper.dart';
import 'package:template/domain/IObject.dart';
import 'package:template/domain/TestObj.dart';
import 'package:template/server/ServerHelper.dart';

class TestObjServerHelper extends ServerHelper<TestObj>{
  TestObjServerHelper(DBHelper<IObject> local, TestObj data, String url) :
        super(local, data, url);

}