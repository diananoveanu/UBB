import 'package:exam29b/domain/IObject.dart';
import 'package:exam29b/domain/Request.dart';
import 'package:exam29b/repository/ObjectRepository.dart';
import 'package:exam29b/service/service.dart';

class RequestService extends ObjectService<Request> {
  RequestService(ObjectRepository<IObject> repo) : super(repo);
}
