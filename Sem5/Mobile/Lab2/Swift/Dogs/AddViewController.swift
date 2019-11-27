
import UIKit

class AddViewController: UIViewController {
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var ageTextField: UITextField!

    var dataManager: DataManager!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        nameTextField.textColor = UIColor.darkGray
        ageTextField.textColor = UIColor.darkGray
    }
    
    @IBAction func saveTapped(_ sender: UIBarButtonItem) {
        guard let items = nameTextField.text,
            let age = ageTextField.text,
            let ageInt = Int16(age) else { return }
        
        _ = MealsDB.instance.addMeal(mId: Int64(ageInt), mealDescr: items, count: Int64(ageInt))
        
        dataManager.createMeal(with: items, quantity: ageInt)
        navigationController?.popViewController(animated: true)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
        super.touchesBegan(touches, with: event)
    }
}
