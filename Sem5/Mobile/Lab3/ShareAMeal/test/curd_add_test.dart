// Import the test package and Counter class
import 'package:first_laboratory/DBHelper.dart';
import 'package:first_laboratory/domain/Meal.dart';
import 'package:flutter_test/flutter_test.dart';


//  var databaseHelper = DBHelper();

//   var t = Meal(1, "water", 20);

//   await databaseHelper.insert(t);

//   var ts = databaseHelper.getItems();
  
//   ts.asStream().forEach((t) => print(t));

//   t.mealDescription = "soup";

//   await databaseHelper.update(t.id, t);

//   ts = databaseHelper.getItems();

//   ts.asStream().forEach((t) => print(t));

//   await databaseHelper.delete(t.id);

//   ts = databaseHelper.getItems();

//   ts.asStream().forEach((t) => print(t));

void main() {
  test('Counter value should be incremented', () async {
    TestWidgetsFlutterBinding.ensureInitialized();
    var dbhelper = DBHelper();
    var t = Meal(1, "water", 20);

    await dbhelper.insert(t);

    var ts = dbhelper.getItems().asStream();
    print(ts.length);
    expect(ts.length, 1);
  });
}