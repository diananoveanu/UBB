//
//  MenuListViewController.swift
//  ShareYourMeals
//
//  Created by Diana  on 10/28/19.
//  Copyright © 2019 Diana . All rights reserved.
//

import UIKit

class MenuListViewController: UIViewController {

    
    
    @IBOutlet weak var tableView: UITableView!
    
    var dataManager: DataManager!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    

}

extension MenuListViewController: UITableViewDataSource {
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataManager.menuCount()    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let menu = dataManager.menu(at: indexPath)
        let cell = UITableViewCell(style: .default, reuseIdentifier: "Cell")
        cell.textLabel?.text = menu.firstCourse
        return cell
    }
}
