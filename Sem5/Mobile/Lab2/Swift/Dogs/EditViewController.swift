//
//  EditViewController.swift
//  Dogs
//
//  Created by Diana  on 11/10/19.
//

import UIKit

class EditViewController: UIViewController {
    
    var dataManager: DataManager!
    
    @IBOutlet weak var quantityTextField: UITextField!
    
    @IBOutlet weak var descriptionTextField: UITextField!
    
    var items = ""
    var quantity = ""
    var meal: Meal?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        quantityTextField.text = meal!.quantity
        descriptionTextField.text = meal!.items
    }
    
    @IBAction func doneTapped(_ sender: Any) {
        self.dataManager.update(meal: meal!, quantity: quantityTextField.text!, description: descriptionTextField.text!)
        
//        _ = MealsDB.instance.updateMeal(mId: Int64(meal!.quantity), newMeal: Course(id: Int64(meal!.quantity), mealDescription: meal!.description, mealCount: Int64(quantityInt)))
        navigationController?.popViewController(animated: true)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
        super.touchesBegan(touches, with: event)
    }
}
