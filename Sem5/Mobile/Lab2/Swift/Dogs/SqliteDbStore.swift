//
//  SqliteDbStore.swift
//  Dogs
//
//  Created by Diana  on 11/24/19.
//  Copyright © 2019 Diana. All rights reserved.
//

import Foundation
import os.log
import SQLite3

class SqliteDbStore {
    // Get the URL to db store file
    let dbURL: URL
    // The database pointer.
    var db: OpaquePointer?
    
    var insertEntryStmt: OpaquePointer?
    var readEntryStmt: OpaquePointer?
    var updateEntryStmt: OpaquePointer?
    var deleteEntryStmt: OpaquePointer?
    
    let oslog = OSLog(subsystem: "codewithayush", category: "sqliteintegration")
    
    init() {
        do {
            do {
                dbURL = try FileManager.default
                    .url(for: .cachesDirectory, in: .userDomainMask, appropriateFor: nil, create: false)
                    .appendingPathComponent("meals.sqlite")
                os_log("URL: %s", dbURL.absoluteString)
            } catch {
                os_log("Some error occurred. Returning empty path.")
                dbURL = URL(fileURLWithPath: "")
                return
            }
            
            try openDB()
//            try createTables()
        } catch {
            os_log("Some error occurred. Returning.")
            return
        }
    }
    
    // Command: sqlite3_open(dbURL.path, &db)
    // Open the DB at the given path. If file does not exists, it will create one for you
    func openDB() throws {
        if sqlite3_open(dbURL.path, &db) != SQLITE_OK { // error mostly because of corrupt database
            os_log("error opening database at %s", log: oslog, type: .error, dbURL.absoluteString)
            //            deleteDB(dbURL: dbURL)
            throw SqliteError(message: "error opening database \(dbURL.absoluteString)")
        }
    }
    
    // Code to delete a db file. Useful to invoke in case of a corrupt DB and re-create another
    func deleteDB(dbURL: URL) {
        os_log("removing db", log: oslog)
        do {
            try FileManager.default.removeItem(at: dbURL)
        } catch {
            os_log("exception while removing db %s", log: oslog, error.localizedDescription)
        }
    }
    
//    func createTables() throws {
//        // create the tables if they dont exist.
//
//        // create the table to store the entries.
//        // ID | Name | Employee Id | Designation
//        let ret =  sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS Meals (id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, EmployeeID TEXT UNIQUE NOT NULL, Designation TEXT NOT NULL)", nil, nil, nil)
//        if (ret != SQLITE_OK) { // corrupt database.
//            logDbErr("Error creating db table - Records")
//            throw SqliteError(message: "unable to create table Records")
//        }
//
//    }
    
    func logDbErr(_ msg: String) {
        let errmsg = String(cString: sqlite3_errmsg(db)!)
        os_log("ERROR %s : %s", log: oslog, type: .error, msg, errmsg)
    }
}

// Indicates an exception during a SQLite Operation.
class SqliteError : Error {
    var message = ""
    var error = SQLITE_ERROR
    init(message: String = "") {
        self.message = message
    }
    init(error: Int32) {
        self.error = error
    }
}
