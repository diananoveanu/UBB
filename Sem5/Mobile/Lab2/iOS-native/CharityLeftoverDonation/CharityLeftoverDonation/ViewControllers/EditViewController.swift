//
//  EditViewController.swift
//  CharityLeftoverDonation
//
//  Created by Diana  on 12/9/19.
//  Copyright © 2019 Diana . All rights reserved.
//

//import UIKit
//
//class EditViewController: UIViewController {
//    
//    var dataManager: DataManager!
//    
//    @IBOutlet weak var itemsTextField: UITextField!
//    @IBOutlet weak var quantityTextField: UITextField!
//
//    var items = ""
//    var quantity = ""
//    var meal: Meal?
//    
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        quantityTextField.text = meal!.quantity
//        itemsTextField.text = meal!.items
//    }
//    
//    @IBAction func doneTapped(_ sender: Any) {
//        self.dataManager.update(meal: meal!, quantity: quantityTextField.text!, description: itemsTextField.text!)
//        
////        _ = MealsDB.instance.updateMeal(mId: Int64(meal!.quantity), newMeal: Course(id: Int64(meal!.quantity), mealDescription: meal!.description, mealCount: Int64(quantityInt)))
//        navigationController?.popViewController(animated: true)
//    }
//    
//    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
//        view.endEditing(true)
//        super.touchesBegan(touches, with: event)
//    }
//}
