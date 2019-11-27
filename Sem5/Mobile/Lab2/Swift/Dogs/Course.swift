//
//  Course.swift
//  Dogs
//
//  Created by Diana  on 11/26/19.
//  Copyright © 2019 steve. All rights reserved.
//

import Foundation

class Course {
    let id: Int64?
    var mealDescription: String
    var mealCount: Int64
    
    init(id: Int64) {
        self.id = id
        self.mealDescription = ""
        self.mealCount = 0
    }
    
    init(id: Int64, mealDescription: String, mealCount: Int64) {
        self.id = id
        self.mealDescription = mealDescription
        self.mealCount = mealCount
    }
}
