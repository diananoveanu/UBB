import 'package:exam/database/DBHelper.dart';
import 'package:exam/domain/IObject.dart';
import 'package:exam/domain/Request.dart';
import 'package:exam/server/ServerHelper.dart';

class ObjectServerHelper extends ServerHelper<Request> {
  ObjectServerHelper(DBHelper<IObject> local, Request data, String url)
      : super(local, data, url);
}
