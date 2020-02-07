import 'package:exam/database/DBHelper.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/repository/Repository.dart';

class ObjectRepository extends Repository<Request> {
  ObjectRepository(DBHelper<Request> dbHelper) : super(dbHelper);
}
