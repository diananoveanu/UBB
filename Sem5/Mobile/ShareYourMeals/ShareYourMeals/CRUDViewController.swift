//
//  CRUDViewController.swift
//  ShareYourMeals
//
//  Created by Diana  on 10/28/19.
//  Copyright © 2019 Diana . All rights reserved.
//

import UIKit

class CRUDViewController: UIViewController {

    @IBOutlet weak var firstCourse: UITextField!
    @IBOutlet weak var secondCourse: UITextField!
    @IBOutlet weak var quantity: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func saveButtonTapped(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
