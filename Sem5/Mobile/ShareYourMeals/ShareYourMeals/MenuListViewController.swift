//
//  MenuListViewController.swift
//  ShareYourMeals
//
//  Created by Diana  on 10/28/19.
//  Copyright © 2019 Diana . All rights reserved.
//

import UIKit
import CoreData

class MenuListViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Menus"

        // Do any additional setup after loading the view.
    }
    
    let appDelegateObj : AppDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
    var dataArray = [NSManagedObject]()

    override func viewDidAppear(animated: Bool) {
           super.viewDidAppear(animated)
           fetchData()
       }
    
    func fetchData() {
        let entityDescription = NSEntityDescription.entityForName(kEntityStr, inManagedObjectContext: appDelegateObj.managedObjectContext)
        
        let fetchRequest = NSFetchRequest()
        fetchRequest.entity = entityDescription
        
        do {
            dataArray = try appDelegateObj.managedObjectContext.executeFetchRequest(fetchRequest) as! [NSManagedObject]
            self.tableView.reloadData()
            
        } catch {
            let fetchError = error as NSError
            print(fetchError)
        }
    }
    
     override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
            return self.dataArray.count
        }
        
        override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
            let cell = tableView.dequeueReusableCellWithIdentifier("cell", forIndexPath: indexPath) as! ContactCell
            cell.lblName.text = dataArray[indexPath.row].valueForKey(kNameStr) as? String
            cell.lblContactNo.text = dataArray[indexPath.row].valueForKey(kPhoneStr) as? String
            return cell
        }
        
        override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
            self.performSegueWithIdentifier("SegueAdd", sender: indexPath.row)
        }
        
        override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
            return true
        }
        
        override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
            if editingStyle == .Delete {
                appDelegateObj.managedObjectContext.deleteObject(dataArray[indexPath.row])
                do {
                    try appDelegateObj.managedObjectContext.save()
                    dataArray.removeAtIndex(indexPath.row)
                    tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
                } catch {
                    let saveError = error as NSError
                    print(saveError)
                }
            }
        }
        
        // MARK: - Navigation
        override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
            if segue.identifier == "SegueAdd" {
                let vc : CRUDViewController = segue.destinationViewController as! CRUDViewController
                vc.dataArray = dataArray
                vc.editRecNo = sender as! Int
                
            }
        }
        
        @IBAction func btnAddTapped(sender: AnyObject) {
            self.performSegue(withIdentifier: "SegueAdd", sender: -1)
        }
    }

    class ContactCell : UITableViewCell {
        
        @IBOutlet weak var lblName: UILabel!
        @IBOutlet weak var lblContactNo: UILabel!
        
    }
