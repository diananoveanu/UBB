////
////  Course.swift
////  Meals
////
////  Created by Diana on 11/26/19.
////  Copyright © 2019 Diana. All rights reserved.
////
//
//
//import Foundation
//
//class NetworkState {
//    class func isConnected() ->Bool {
//        return NetworkReachabilityManager()!.isReachable
//    }
//}
//
//struct Course: Decodable {
//    let id: Int
//    var mealDescription: String
//    var mealCount: Int
//
//    init(id: Int) {
//        self.id = id
//        self.mealDescription = ""
//        self.mealCount = 0
//    }
//
//    init(id: Int, mealDescription: String, mealCount: Int) {
//        self.id = id
//        self.mealDescription = mealDescription
//        self.mealCount = mealCount
//    }
//}
//
//struct Meals:Decodable{
//    var meals: [Course]
//}
//
//struct MealResponse:Decodable {
//    var response: Meals
//}
//
//struct ml: Equatable {
//    var it: String
//    var ct: Int
//}
