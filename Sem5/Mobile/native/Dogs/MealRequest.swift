//
//  MealRequest.swift
//  Dogs
//
//  Created by Diana  on 12/15/19.
//  Copyright © 2019 Diana. All rights reserved.
//

import Foundation

enum MealError: Error{
    case noDataAvailable
    case cannotProcessData
}

struct MealRequest:Decodable {
    let resourceURL = URL(string: "http://127.0.0.1:5000/api/item")
    
    func getMealsServer(completion: @escaping(Result<[Course], MealError>)-> Void){
        let dataTask = URLSession.shared.dataTask(with: resourceURL!) {data, response, error in
            guard let jsonData = data else {
                completion(.failure(.noDataAvailable))
                return
            }
            do {
                let decoder = JSONDecoder()
                let mealResponse = try decoder.decode([Course].self, from: jsonData)
                let mealDetails = mealResponse
                completion(.success(mealDetails))
            } catch {
                completion(.failure(.cannotProcessData))
            }
        }
        dataTask.resume()
    }
    func deleteMealServer(id: String, completion: @escaping(Result<[Course], MealError>)-> Void){
        let session = URLSession.shared
        let url = URL(string: resourceURL!.absoluteString + "/del/" + id)!
        print(url)
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        let json = ["":""]
        let jsonData = try! JSONSerialization.data(withJSONObject: json, options: [])
        
        let task = session.uploadTask(with: request, from: jsonData) { data, response, error in
            guard let jsonData = data else {
                completion(.failure(.noDataAvailable))
                return
            }
            do {
                let decoder = JSONDecoder()
                let mealResponse = try decoder.decode([Course].self, from: jsonData)
                let mealDetails = mealResponse
                completion(.success(mealDetails))
            } catch {
                completion(.failure(.cannotProcessData))
            }
        }
        task.resume()
    }
    
    func addMealServer(descr:String, quant: String, completion: @escaping(Result<Course, MealError>)-> Void){
        let session = URLSession.shared
        let url = resourceURL!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        let json = [
            "mealDescription": descr,
            "mealCount": quant
        ]
        let jsonData = try! JSONSerialization.data(withJSONObject: json, options: [])
        
        let task = session.uploadTask(with: request, from: jsonData) { data, response, error in
            guard let jsonData = data else {
                completion(.failure(.noDataAvailable))
                return
            }
            do {
                let decoder = JSONDecoder()
                let mealResponse = try decoder.decode(Course.self, from: jsonData)
                let mealDetails = mealResponse
                completion(.success(mealDetails))
            } catch {
                completion(.failure(.cannotProcessData))
            }
        }
        task.resume()
    }
    
    func updateMealServer(descr:String, quant: String, id: String, completion: @escaping(Result<[Course], MealError>)-> Void){
        let session = URLSession.shared
        let url = URL(string: resourceURL!.absoluteString + "/" + id)!
        print(url)
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        let json = [
            "mealDescription": descr,
            "mealCount": quant
        ]
        let jsonData = try! JSONSerialization.data(withJSONObject: json, options: [])
        
        let task = session.uploadTask(with: request, from: jsonData) { data, response, error in
            guard let jsonData = data else {
                completion(.failure(.noDataAvailable))
                return
            }
            do {
                let decoder = JSONDecoder()
                let mealResponse = try decoder.decode([Course].self, from: jsonData)
                let mealDetails = mealResponse
                completion(.success(mealDetails))
            } catch {
                completion(.failure(.cannotProcessData))
            }
        }
        task.resume()
    }
}


