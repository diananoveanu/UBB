import 'package:template/domain/IObject.dart';
import 'package:template/domain/TestObj.dart';
import 'package:template/repository/ObjectRepository.dart';
import 'package:template/service/service.dart';

class TestObjService extends ObjectService<TestObj>{
  TestObjService(ObjectRepository<IObject> repo) : super(repo);
}