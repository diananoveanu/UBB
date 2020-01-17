import 'package:first_laboratory/domain/Meal.dart';
import 'package:first_laboratory/repository/MealRepository.dart';

class MealService{

  static final MealService _service = MealService._internal();

  MealRepository repository;

  factory MealService(){
    return _service;
  }

  MealService._internal(){
    repository = MealRepository();
  }


  Future<Meal> addMeal(Meal t){
    return repository.addMeal(t);
  }


  Future<int> deleteMeal(int id){
    return repository.deleteMeal(id);
  }

  Future<int> editMeal(int id, Meal t){
    return repository.editMeal(id, t);
  }

  Future<Meal> getMeal(int id){
    return repository.getMeal(id);
  }

  Future<List<Meal>> getMeals(){
    return repository.getMeals();
  }

  Future<int> getCount(){
    return repository.getCount();
  }
}