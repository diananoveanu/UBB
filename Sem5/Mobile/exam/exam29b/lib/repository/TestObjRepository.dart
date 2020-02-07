import 'package:exam29b/database/DBHelper.dart';
import 'package:exam29b/domain/Request.dart';
import 'package:exam29b/repository/ObjectRepository.dart';

class RequestRepository extends ObjectRepository<Request> {
  RequestRepository(DBHelper<Request> dbHelper) : super(dbHelper);
}
