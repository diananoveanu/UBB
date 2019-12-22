import 'package:first_laboratory/DBHelper.dart';
import 'package:first_laboratory/domain/Meal.dart';

class MealRepository{

  DBHelper _databaseHelper;

  static final _repository = MealRepository._internal();

  MealRepository._internal(){
    this._databaseHelper = new DBHelper();

//    _Meals.add(Meal(-1, "Metallica", "Black", "M", 150, "Ron"));
//    _Meals.add(Meal(-2, "Dirty Shirt", "Green", "L", 60, "Ron"));
//    _Meals.add(Meal(-3, "Nirvana", "White", "M", 10, "Euro"));
//    _Meals.add(Meal(-4, "Guns'n'Roses", "Grey", "M", 100, "Ron"));
//    _Meals.add(Meal(-5, "Aerosmith", "Black", "M", 20, "Dollar"));
//    _Meals.add(Meal(-6, "AC/DC", "Grey", "M", 80, "Ron"));
//    _Meals.add(Meal(-7, "Green Day", "Black", "M", 40, "Euro"));
  }

  factory MealRepository(){
    return _repository;
  }

  Future<Meal> addMeal(Meal t){
    return _databaseHelper.insert(t);
  }

  Future<List<Meal>> getMeals(){
    return _databaseHelper.getItems();
  }

  Future<Meal> getMeal(int id){
    return _databaseHelper.getItem(id);
  }

  Future<int> deleteMeal(int id){
    return _databaseHelper.delete(id);
  }

  Future<int> editMeal(int id, Meal newMeal){
    return _databaseHelper.update(id, newMeal);
  }

  Future<int> getCount(){
    return _databaseHelper.getCount();
  }
}