//
//  MealsDb.swift
//  SqliteTest
//
//  Created by Diana  on 11/26/19.
//  Copyright © 2019 Diana . All rights reserved.
//


import Foundation
import SQLite

class MealsDB{
    
    static let instance = MealsDB()
    private let db : Connection?
    
    private let meals = Table("meal")
    private let id = Expression<Int64>("id")
    private let mealDescription = Expression<String>("mealDescription")
    private let mealCount = Expression<Int64>("mealCount")

    private init() {
        let path = NSSearchPathForDirectoriesInDomains(
            .documentDirectory, .userDomainMask, true
            ).first!

        do {
            db = try Connection("\(path)/meal.sqlite3")
            print("Database opened!")
        } catch {
            db = nil
            print ("Unable to open database")
        }

        createTable()
    }
    
    func createTable() {
        do {
            try db!.run(meals.create(ifNotExists: true) { table in
            table.column(id, primaryKey: true)
            table.column(mealDescription)
            table.column(mealCount)
            })
            print("Table created!")
        } catch {
            print("Unable to create table")
        }
    }
    
    func addMeal(mId: Int64, mealDescr: String, count: Int64) -> Int64? {
        do {
            let insert = meals.insert(id <- mId, mealDescription <- mealDescr, mealCount <- count)
            let id = try db!.run(insert)
            
            return id
        } catch {
            print("Insert failed")
            return -1
        }
    }
    
    
    func getMeals() -> [Course] {
        var meals = [Course]()

        do {
            for meal in try db!.prepare(self.meals) {
                meals.append(Course(
                id: meal[id],
                mealDescription: meal[mealDescription],
                mealCount: meal[mealCount]))
            }
        } catch {
            print("Select failed")
        }

        return meals
    }
    
    
    func deleteMeal(mId: Int64) -> Bool {
        do {
            let meal = meals.filter(id == mId)
            try db!.run(meal.delete())
            return true
        } catch {
            print("Delete failed")
        }
        return false
    }
    
    func updateMeal(mId: Int64, newMeal: Course) -> Bool {
        let meal = meals.filter(id == mId)
           do {
               let update = meal.update([
                mealDescription <- newMeal.mealDescription,
                mealCount <- newMeal.mealCount
                   ])
               if try db!.run(update) > 0 {
                   return true
               }
           } catch {
               print("Update failed: \(error)")
           }

           return false
    }
}





