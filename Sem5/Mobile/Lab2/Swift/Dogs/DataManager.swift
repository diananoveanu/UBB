
import UIKit
import CoreData
import SQLite3

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
    
    private func fetchMeals()-> [Course]{
        let courses = MealsDB.instance.getMeals()
        return courses
    }
    
    private func fetchWithSort()-> [Meal] {

        let fetchRequest = NSFetchRequest<Meal>(entityName: Key.meal)
        let sortDescriptor = NSSortDescriptor(key: "quantity", ascending: true)
        fetchRequest.sortDescriptors = [sortDescriptor]
        let meals = try! context.fetch(fetchRequest)
        
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
    
    internal func update(meal: Meal, quantity: Int16) {
        meal.quantity = quantity
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
