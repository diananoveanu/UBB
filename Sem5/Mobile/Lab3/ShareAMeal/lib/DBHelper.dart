import 'package:first_laboratory/domain/Meal.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite/sqlite_api.dart';

final String tableMeals = 'Meals';
final String columnId = 'id';
final String columnMealDescription = 'mealDescription';
final String columnMealCount = 'mealCount';


class DBHelper{
  Database _db;

  Future<Database> get db async {
    if (_db != null) return _db;
    var p = await getDatabasesPath();
    String path = p + "/meals.db";
    _db = await open(path);
    return _db;
  }

  open(String path) async {
    var d = await openDatabase(path, version: 3,
        onCreate: (Database db, int version) async {
          await db.execute('''
          create table $tableMeals ( 
            $columnId integer primary key, 
            $columnMealDescription text,
            $columnMealCount integer
          )
          ''');
        });
    return d;
  }

  Future<Meal> insert(Meal t) async {
    var dbClient = await db;
    t.id = await dbClient.insert(tableMeals, t.toMapBD());
    return t;
  }

  Future<List<Meal>> getItems() async {
    var dbClient = await db;
    var result = await dbClient.rawQuery("SELECT * FROM $tableMeals");

    return List.generate(result.length, (i) {
      return Meal(
        result[i]['id'],
        result[i]['mealDescription'],
        result[i]['mealCount']
      );
    });
  }

  Future<Meal> getItem(int id) async {
    var dbClient = await db;
    var result = await dbClient.rawQuery("SELECT * FROM $tableMeals where $columnId = $id");
    return Meal(
        result[0]['id'],
        result[0]['mealDescription'],
        result[0]['mealCount'],
    );
  }

  Future<int> delete(int id) async {
    var dbClient = await db;
    return await dbClient.delete(tableMeals,
        where: '$columnId = ?', whereArgs: [id]);
  }

  Future<int> update(int id, Meal t) async {
    var dbClient = await db;
    return await dbClient.update(tableMeals, t.toMapBD(),
        where: '$columnId = ?', whereArgs: [id]);
  }

  Future<int> getCount() async {
    var dbClient = await db;
    List<Map<String, dynamic>> x = await dbClient.rawQuery('SELECT COUNT (*) from $tableMeals');
    int result = Sqflite.firstIntValue(x);
    return result;
  }
}