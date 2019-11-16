
import UIKit

class MainViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    var dataManager: DataManager!
    internal func onUserAction(data: Int)
    {
        print("Data received:")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.navigationBar.barTintColor = #colorLiteral(red: 0.859064579, green: 0.8289134502, blue: 0.9530699849, alpha: 1)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        self.tableView.backgroundColor = #colorLiteral(red: 0.859064579, green: 0.8289134502, blue: 0.9530699849, alpha: 1)
        
        func tableView(tableView: UITableView, willDisplayCell cell: UITableViewCell, forRowAtIndexPath indexPath: NSIndexPath) {
            cell.backgroundColor = UIColor.clear
        }
        self.tableView.reloadData()
    }
    
    
    
    // MARK: - Navigation
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let addVC = segue.destination as? AddViewController {
            addVC.dataManager = dataManager
        }
        
        if segue.identifier == "edt", let destination = segue.destination as? EditViewController,
            let blogIndex = tableView.indexPathForSelectedRow, let meal:Meal = self.dataManager.meal(at: blogIndex)
        {
            destination.dataManager = dataManager
            destination.meal = meal
        }
    }
}


extension MainViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int  {
        return dataManager.mealCount()
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: "Cell")
        // fetch data
        let meal = dataManager.meal(at: indexPath)
        // display data
        cell.contentView.backgroundColor = #colorLiteral(red: 0.859064579, green: 0.8289134502, blue: 0.9530699849, alpha: 1)
        cell.textLabel?.text = meal.items
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == UITableViewCell.EditingStyle.delete {
            let meal = self.dataManager.meal(at: indexPath)
            self.dataManager.delete(meal: meal)
            tableView.deleteRows(at: [indexPath], with: UITableView.RowAnimation.automatic)
        }
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "edt", sender: self)
    }
}
