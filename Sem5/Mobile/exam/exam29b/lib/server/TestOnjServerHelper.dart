import 'package:exam29b/database/DBHelper.dart';
import 'package:exam29b/domain/IObject.dart';
import 'package:exam29b/domain/Request.dart';
import 'package:exam29b/server/ServerHelper.dart';

class RequestServerHelper extends ServerHelper<Request> {
  RequestServerHelper(DBHelper<IObject> local, Request data, String url)
      : super(local, data, url);
}
