//
//  LogInViewController.swift
//  ShareAMeal
//
//  Created by Diana  on 10/26/19.
//  Copyright © 2019 Diana . All rights reserved.
//
import FirebaseAuth
import UIKit

class LogInViewController: UIViewController {

    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    @IBAction func loginButtonTapped(_ sender: Any) {
        let email = emailTextField.text!
        
        let password = passwordTextField.text!
        
            
            // Signing in the user
            Auth.auth().signIn(withEmail: email, password: password) { (result, error) in
                
                if error != nil {
                    print("info is invalid")
                }
                else {
                    
                    let homeViewController = self.storyboard?.instantiateViewController(identifier: Constants.Storyboard.homeViewController) as? HomeViewController
                    
                    self.view.window?.rootViewController = homeViewController
                    self.view.window?.makeKeyAndVisible()
                }
            }
        let homeViewController = self.storyboard?.instantiateViewController(identifier: Constants.Storyboard.homeViewController) as? HomeViewController
                           
                           self.view.window?.rootViewController = homeViewController
                           self.view.window?.makeKeyAndVisible()
    }
    
}
