
import UIKit

class AddViewController: UIViewController {
    @IBOutlet weak var descriptionTextField: UITextField!
    @IBOutlet weak var quantityTextField: UITextField!
    
    var listOfMeals = [Course]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        descriptionTextField.textColor = UIColor.darkGray
        quantityTextField.textColor = UIColor.darkGray
    }
    
    
    
    @IBAction func saveTapped(_ sender: UIBarButtonItem) {
        let items = descriptionTextField.text
        let quantity = quantityTextField.text!
        
        if NetworkState.isConnected() {
            let mealRequest = MealRequest()
            mealRequest.addMealServer(descr: items!, quant: quantity){[ weak self ] result in
                switch result{
                case .failure (let error):
                    print(error)
                case .success (let meals):
                    print(meals.mealDescription)
                }
            }
            
            _ = MealsDB.instance.addMeal(mealDescr: items!, count: Int(quantity)!)
        }
        else{
            _ = MealsDB.instance.addMeal(mealDescr: items!, count: Int(quantity)!)
        }
       self.listOfMeals = MealsDB.instance.getMeals()
       navigationController?.popViewController(animated: true)
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
        super.touchesBegan(touches, with: event)
    }
}

