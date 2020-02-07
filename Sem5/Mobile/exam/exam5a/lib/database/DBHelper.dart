import 'package:exam5a/domain/IObject.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite/sqlite_api.dart';

class DBHelper<T extends IObject> {
  Database _db;
  String _table;
  Map<String, String> _cols;
  T _data;

  DBHelper(String table, Map<String, String> cols, T data) {
    this._table = table;
    this._cols = cols;
    this._data = data;
  }

  Future<Database> get db async {
    if (_db != null) {
      return _db;
    }
    //var p = await getDatabasesPath();
    String path = _table.toLowerCase() + ".db";
    print("DBPATH: " + path);
    _db = await open(path);
    return _db;
  }

  open(String path) async {
    var d = await openDatabase(path, version: 1,
        onCreate: (Database db, int version) async {
      String dbCreateString = "create table $_table( \n";
      _cols.forEach((k, v) => (k == 'id'
          ? dbCreateString += "id integer primary key,\n"
          : dbCreateString += k + " " + v + ",\n"));
      dbCreateString = dbCreateString.substring(0, dbCreateString.length - 3);
      dbCreateString += ")";
      print(dbCreateString + "===============================");
      await db.execute(dbCreateString);
    });
    return d;
  }

  Future<T> insert(T obj) async {
    var dbClient = await db;
    obj.id = await dbClient.insert(_table, obj.toJsonDB());
    return obj;
  }

  Future<List<T>> getItems() async {
    var dbClient = await db;
    var result = await dbClient.rawQuery("SELECT * FROM $_table");

    return List.generate(result.length, (i) {
      return _data.objectFromDBRes(result[i]);
    });
  }

  Future<T> getItem(int id) async {
    var dbClient = await db;
    var result = await dbClient.rawQuery("SELECT * FROM $_table "
        "where id=$id");

    return _data.objectFromDBRes(result[0]);
  }

  Future<int> delete(int id) async {
    var dbClient = await db;
    return await dbClient.delete(_table, where: 'id=?', whereArgs: [id]);
  }

  Future<int> update(int id, IObject obj) async {
    var dbClient = await db;
    return await dbClient
        .update(_table, obj.toJsonDB(), where: 'id=?', whereArgs: [id]);
  }

  Future<int> deleteAll() async {
    var list = this.getItems();
    list.asStream().forEach((obj) => {obj.forEach((t) => this.delete(t.id))});
  }

  Future<int> getCount() async {
    var dbClient = await db;
    List<Map<String, dynamic>> x =
        await dbClient.rawQuery('SELECT COUNT(*) from $_table');
    int result = Sqflite.firstIntValue(x);
    return result;
  }
}
