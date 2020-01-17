class Meal{

  int id;
  String mealDescription;
  int mealCount;

  Meal(int id, String mealDescription, int mealCount){
    this.id = id;
    this.mealDescription = mealDescription;
    this.mealCount = mealCount;
  }

  Meal.withoutID (String mealDescription, int mealCount){
    this.mealDescription = mealDescription;
    this.mealCount = mealCount;
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["mealCount"] = mealCount;
    map["mealDescription"] = mealDescription;
    
    if (id != null) {
      map["id"] = id.toString();
    }
    return map;
  }

  Map<String, dynamic> toMapBD() {
    var map = new Map<String, dynamic>();
    map["mealDescription"] = mealDescription;
    map["mealCount"] = mealCount;
    if (id != null) {
      map["id"] = id;
    }
    return map;
  }

  Meal.map(dynamic obj) {
    this.id = obj["id"];
    this.mealCount = obj["mealCount"];
    this.mealDescription = obj["mealDescription"];
  }

  @override
  String toString() {
    return this.mealDescription + '\n' + this.mealCount.toString();
  }
}