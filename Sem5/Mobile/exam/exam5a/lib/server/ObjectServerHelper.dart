import 'package:exam5a/database/DBHelper.dart';
import 'package:exam5a/domain/IObject.dart';
import 'package:exam5a/domain/Request.dart';
import 'package:exam5a/server/ServerHelper.dart';

class ObjectServerHelper extends ServerHelper<Request> {
  ObjectServerHelper(DBHelper<IObject> local, Request data, String url)
      : super(local, data, url);
}
