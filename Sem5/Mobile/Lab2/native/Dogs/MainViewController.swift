import UIKit

class MainViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    var mls: [Course] = []
    var mealsDB = MealsDB.instance
    
    var listOfMeals = [Course](){
        didSet {
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.reloadData()
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.navigationBar.barTintColor = #colorLiteral(red: 0.859064579, green: 0.8289134502, blue: 0.9530699849, alpha: 1)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        self.tableView.backgroundColor = #colorLiteral(red: 0.859064579, green: 0.8289134502, blue: 0.9530699849, alpha: 1)
        
        func tableView(tableView: UITableView, willDisplayCell cell: UITableViewCell, forRowAtIndexPath indexPath: NSIndexPath) {
            cell.backgroundColor = UIColor.clear
        }
        
        
        if NetworkState.isConnected() {
            self.syncMeals()
        }
        else {
            self.listOfMeals = MealsDB.instance.getMeals()
            print("LIST OF MEALS IN VIEW WILL APEAR OFFLINE ")
            print(self.listOfMeals)
        }
        
        self.tableView.reloadData()
    }
    
    
    // MARK: - DELETE
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == UITableViewCell.EditingStyle.delete {
            let meal:Course = self.listOfMeals[indexPath.row]
            
            if NetworkState.isConnected() {
                let mealRequest = MealRequest()
                mealRequest.deleteMealServer(id: String(meal.id)){[ weak self ] result in
                    switch result{
                    case .failure (let error):
                        print(error)
                    case .success (let meals):
                        print("")
                    }
                }
                self.listOfMeals.remove(at: indexPath.row)
                _ = MealsDB.instance.deleteMeal(mId: meal.id)
                self.tableView.reloadData()
            }
            else{
                let alert = UIAlertController(title: "You are offline.", message: "Deleting items is unavailable while you are not connected to the internet", preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
                self.present(alert, animated: true)
            }
        }
    }
    
    
    // MARK: - EDIT
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if NetworkState.isConnected() {
            performSegue(withIdentifier: "edt", sender: self)
        }
        else{
            let alert = UIAlertController(title: "You are offline.", message: "Editing items is unavailable while you are not connected to the internet", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            self.present(alert, animated: true)
        }
        self.tableView.reloadData()
    }
    
    
    // MARK: - SYNC
    
    func syncMeals(){
        var serverMeals = [Course]()
        
        let localMeals = self.mealsDB.getMeals()
        
        let mealRequest = MealRequest()
        
        mealRequest.getMealsServer{[ weak self ] result in
            switch result{
            case .failure (let error):
                print(error)
            case .success (let meals):
                var serverInst = [ml]()
                var localInst = [ml]()
                serverMeals = meals
                
                for serverMeal in serverMeals {
                    let m = ml(it: serverMeal.mealDescription, ct: Int(serverMeal.mealCount))
                    serverInst.append(m)
                }
                
                for localMeal in localMeals {
                    let m = ml(it: localMeal.mealDescription, ct: Int(localMeal.mealCount))
                    localInst.append(m)
                }
                //                   print(localInst)
                //                   print(serverInst)
                
                for localInsta in localInst {
                    if !serverInst.contains(localInsta){
                        let mealRequest = MealRequest()
                        
                        mealRequest.addMealServer(descr: localInsta.it, quant: String(localInsta.ct)){[ weak self ] result in
                            switch result{
                            case .failure (let error):
                                print(error)
                            case .success (let meals):
                                print("")
                            }
                        }
                    }
                }
                
                for serverInsta in serverInst {
                    if !localInst.contains(serverInsta){
                        self!.mealsDB.addMeal(mealDescr: serverInsta.it, count: serverInsta.ct)
                    }
                }
            }
        }
        self.listOfMeals = self.mealsDB.getMeals()
    }
}


extension MainViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int  {
        
        let mdb = MealsDB.instance.getMeals()
        let mealRequest = MealRequest()
        mealRequest.getMealsServer{[ weak self ] result in
            switch result{
            case .failure (let error):
                print(error)
            case .success (let meals):
                self!.mls = meals
            }
        }
        
        return listOfMeals.count
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat
    {
        return 100
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .subtitle, reuseIdentifier: "Cell")
        let meal = listOfMeals[indexPath.row]

        cell.contentView.backgroundColor = #colorLiteral(red: 0.859064579, green: 0.8289134502, blue: 0.9530699849, alpha: 1)
        cell.textLabel?.text = meal.mealDescription
        cell.detailTextLabel?.text = String(meal.mealCount)
        cell.detailTextLabel?.textColor = UIColor.gray

        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "edt", let destination = segue.destination as? EditViewController,
            let blogIndex = tableView.indexPathForSelectedRow, let meal:Course = self.listOfMeals[blogIndex.row]
        {
            destination.listOfMeals = listOfMeals
            destination.course = meal
        }
        
        if segue.identifier == "gotoAdd" {
            let destination = segue.destination as! AddViewController
            destination.listOfMeals = listOfMeals
        }
        
    }
}
