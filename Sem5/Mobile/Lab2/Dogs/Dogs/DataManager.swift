
import UIKit
import CoreData

class DataManager: NSObject {
  
  var context: NSManagedObjectContext!
  
  override init() {}
  
  convenience init(context: NSManagedObjectContext) {
    self.init()
    self.context = context
  }
  
  struct Key {
    static let meal = "Meal"
  }
  
  internal func mealCount()-> Int {
    let request = NSFetchRequest<Meal>(entityName: Key.meal)
    let result = try! context.count(for: request)
    return result
  }
  
  private func fetchWithSort()-> [Meal] {
    
    let fetchRequest = NSFetchRequest<Meal>(entityName: Key.meal)
    let sortDescriptor = NSSortDescriptor(key: "quantity", ascending: true)
    fetchRequest.sortDescriptors = [sortDescriptor]
    let meals = try! context.fetch(fetchRequest)
//    for meal in meals {
//      print(#line, meal.items!, meal.color!, meal.quantity)
//    }
    return meals
  }
  
  internal func meal(at indexPath: IndexPath)-> Meal {
    let meals = fetchWithSort()
    return meals[indexPath.row]
  }
  
  // called from AddViewController
  internal func createMeal(with items: String, quantity: Int16) {
    let meal = Meal(context: context)
    meal.quantity = quantity
    meal.items = items
    saveContext()
  }
  
  // MARK - Old Methods
  
  private func createMeals() {
    let meal1 = Meal(context: context)
    meal1.quantity = 1
    meal1.items = "Spot"
    
    let meal2 = Meal(context: context)
    meal2.quantity = 5
    meal2.items = "Walter"
    
    let meal3 = Meal(context: context)
    meal3.quantity = 10
    meal3.items = "Zeke"
    
    saveContext()
    
  }
  
  
  
  private func simpleFetch() {
    
    let fetchRequest = NSFetchRequest<Meal>(entityName: Key.meal)
    let meals = try! context.fetch(fetchRequest)
    for meal in meals {
      print(#line, meal.items!, meal.quantity)
    }
    
  }
  
  
  
  private func fetchWithPredicate()-> Meal? {
    
    let fetchRequest = NSFetchRequest<Meal>(entityName: Key.meal)
    let predicate = NSPredicate(format: "quantity > 5")
    fetchRequest.predicate = predicate
    let meals = try! context.fetch(fetchRequest)
    print(#line, meals.count)
    guard let firstMeal = meals.first else { return nil}
    print(#line, firstMeal.items!, firstMeal.quantity)
    return firstMeal
  }
  
  internal func update(meal: Meal) {
    meal.quantity += 1
    saveContext()
  }
  
  internal func delete(meal: Meal) {
    context.delete(meal)
    saveContext()
  }
  
  private func saveContext () {
    if context.hasChanges {
      do {
        try context.save()
      } catch {
        let nserror = error as NSError
        fatalError("Unresolved error \(nserror), \(nserror.userInfo)")
      }
    }
  }
}
