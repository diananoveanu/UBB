import 'package:exam/domain/IObject.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/Repository.dart';
import 'package:exam/service/Service.dart';

class ObjectService extends Service<Request> {
  ObjectService(Repository<IObject> repo) : super(repo);
}
