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
    
    @IBOutlet weak var oldQuantityLabel: UILabel!
    @IBOutlet weak var itemsLabel: UILabel!
    
    var items = ""
    var quantity: Int? = 0
    var meal: Meal?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        guard let quant = self.meal?.quantity,
            let quantityInt = Int?(Int(quant)) else { return }
        itemsLabel.text = self.meal?.items
        oldQuantityLabel.text = String(quantityInt)
    }
    
    @IBAction func doneTapped(_ sender: Any) {
        guard let quant = quantityTextField.text,
            let quantityInt = Int16(quant) else { return }
        self.dataManager.update(meal: meal!, quantity: quantityInt)
        
        _ = MealsDB.instance.updateMeal(mId: Int64(meal!.quantity), newMeal: Course(id: Int64(meal!.quantity), mealDescription: meal!.description, mealCount: Int64(quantityInt)))
        navigationController?.popViewController(animated: true)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
        super.touchesBegan(touches, with: event)
    }
}
