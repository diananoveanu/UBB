//
//  EditViewController.swift
//  Meals
//
//  Created by Diana  on 11/10/19.
//


import UIKit


class EditViewController: UIViewController {
    
    var listOfMeals = [Course]()
    
    @IBOutlet weak var descriptionTextField: UITextField!
    @IBOutlet weak var quantityTextField: UITextField!
    
    var items = ""
    var quantity: Int? = 0
    var course: Course?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        guard let quant = self.course?.mealCount,
            let quantityInt = Int?(Int(quant)) else { return }
        
        descriptionTextField.text = course?.mealDescription
        quantityTextField.text = String(quantityInt)
    }
    
    @IBAction func doneTapped(_ sender: Any) {
        let quant = quantityTextField.text!
        let description = descriptionTextField.text!
        
        if NetworkState.isConnected() {
            _ = MealsDB.instance.updateMeal(mId: self.course!.id, newMeal: Course(id: Int(self.course!.id), mealDescription: description, mealCount: Int(quant)!))
            
            let mealRequest = MealRequest()
            
            mealRequest.updateMealServer(descr: description, quant: quant, id: String(self.course!.id)){[ weak self ] result in
                switch result{
                case .failure (let error):
                    print(error)
                case .success (let meals):
                    print(meals)
                }
            }
        }
        else{
            let alert = UIAlertController(title: "You are offline.", message: "Editing items is unavailable while you are not connected to the internet", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            self.present(alert, animated: true)
        }
        navigationController?.popViewController(animated: true)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
        super.touchesBegan(touches, with: event)
    }
}
