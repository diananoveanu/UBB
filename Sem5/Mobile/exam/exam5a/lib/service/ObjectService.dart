import 'package:exam5a/domain/IObject.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/Repository.dart';
import 'package:exam5a/service/Service.dart';

class ObjectService extends Service<Request> {
  ObjectService(Repository<IObject> repo) : super(repo);
}
