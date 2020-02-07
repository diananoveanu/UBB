import 'package:exam29b/database/DBHelper.dart';
import 'package:exam29b/domain/Request.dart';

class RequestDbHelper extends DBHelper<Request> {
  RequestDbHelper(String table, Map<String, String> cols, Request data)
      : super(table, cols, data);
}
