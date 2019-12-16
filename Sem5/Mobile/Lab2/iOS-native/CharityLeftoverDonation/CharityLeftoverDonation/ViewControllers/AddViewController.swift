//
//  AddViewController.swift
//  CharityLeftoverDonation
//
//  Created by Diana  on 12/9/19.
//  Copyright © 2019 Diana . All rights reserved.
//

import UIKit

class AddViewController: UIViewController {
    
    @IBOutlet weak var itemsTextField: UITextField!
    @IBOutlet weak var quantityTextField: UITextField!
    
    
    var dataManager: DataManager!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        itemsTextField.textColor = UIColor.darkGray
        quantityTextField.textColor = UIColor.darkGray
    }
    
    
//    @IBAction func saveTapped(_ sender: UIBarButtonItem) {
//        let items = itemsTextField.text
//        let quantity = quantityTextField.text
//        //        _ = MealsDB.instance.addMeal(mId: Int64(ageInt), mealDescr: items, count: Int64(ageInt))
//        
//        dataManager.createMeal(with: items!, quantity: quantity!)
//        navigationController?.popViewController(animated: true)
//    }
//
//    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
//        view.endEditing(true)
//        super.touchesBegan(touches, with: event)
//    }
    
}
