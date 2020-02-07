import 'package:exam29b/database/DBHelper.dart';
import 'package:exam29b/domain/IObject.dart';

abstract class ObjectRepository<T extends IObject> {
  DBHelper _databaseHelper;

  ObjectRepository(DBHelper dbHelper) {
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
