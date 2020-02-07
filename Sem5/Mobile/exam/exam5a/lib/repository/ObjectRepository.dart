import 'package:exam5a/database/DBHelper.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/repository/Repository.dart';

class ObjectRepository extends Repository<Request> {
  ObjectRepository(DBHelper<Request> dbHelper) : super(dbHelper);
}
