//
//  DataManager.swift
//  ShareYourMeals
//
//  Created by Diana  on 10/29/19.
//  Copyright © 2019 Diana . All rights reserved.
//

import UIKit
import CoreData

class DataManager: NSObject {
    var context: NSManagedObjectContext!
    
    override init(){
    }
    
    convenience init(context: NSManagedObjectContext) {
        self.init()
        self.context = context
    }
    
    struct Key {
        static let menu = "Menu"
    }
    
    internal func menuCount() -> Int {
        let request = NSFetchRequest<Menu>(entityName: Key.menu)
        let result = try! context.count(for: request)
        return result
    }
    
    internal func menu(at indexPath: IndexPath) -> Menu {
        let menus = fetchWithSort()
        return menus[indexPath.row]
    }
    
    private func fetchWithSort() -> [Menu] {
        let fetchRequest = NSFetchRequest<Menu>(entityName: Key.menu)
        
        let sortDescriptor = NSSortDescriptor(key: "quantity", ascending: true)
        fetchRequest.sortDescriptors = [sortDescriptor]
        let menus = try! context.fetch(fetchRequest)
       
        return menus
        
    }
    
    
    
    
    
    ///////
    private func createMenus(){
            let menu1 = Menu(context: context)
            menu1.firstCourse = "Supa de varza"
            menu1.secondCourse = "Piure de cartofi si piept de pui la gratar"
            menu1.quantity = 10
            
            let menu2 = Menu(context: context)
            menu1.firstCourse = "Supa de cartofi"
            menu1.secondCourse = "Cartofi prajiti si snitel de pui"
            menu1.quantity = 30
            
            saveContext()
    }
        
        
        private func fetchMenus(){
            let fetchRequest = NSFetchRequest<Menu>(entityName: Key.menu)
            let menus = try! context.fetch(fetchRequest)
    //        for menu in menus
        }
        
        private func updateMenu(menu: Menu){
            menu.quantity += 1
            saveContext()
        }
        
        private func deleteMenu(menu: Menu){
            context.delete(menu)
            saveContext()
        }
    func saveContext () {
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
