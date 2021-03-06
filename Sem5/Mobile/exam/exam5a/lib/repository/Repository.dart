import 'package:exam5a/database/DBHelper.dart';
import 'package:exam5a/domain/IObject.dart';

abstract class Repository<T extends IObject> {
  DBHelper _databaseHelper;

  Repository(DBHelper dbHelper) {
    this._databaseHelper = dbHelper;
  }

  Future<T> add(T t) {
    return _databaseHelper.insert(t);
  }

  Future<List<T>> getObjects() {
    return _databaseHelper.getItems();
  }

  Future<T> getObject(int id) {
    return _databaseHelper.getItem(id);
  }

  Future<int> deleteObject(int id) {
    return _databaseHelper.delete(id);
  }

  Future<int> editObject(int id, T newObject) {
    return _databaseHelper.update(id, newObject);
  }

  Future<int> size() {
    return _databaseHelper.getCount();
  }
}
