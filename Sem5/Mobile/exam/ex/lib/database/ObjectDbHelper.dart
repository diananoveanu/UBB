import 'package:exam/domain/Request.dart';
import 'DBHelper.dart';

class ObjectDbHelper extends DBHelper<Request> {
  ObjectDbHelper(String table, Map<String, String> cols, Object data)
      : super(table, cols, data);
}
