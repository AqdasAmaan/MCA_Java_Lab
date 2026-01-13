package library.main;

import java.util.Scanner;
import library.management.Management;
import utils.Input;

public class Main {
    public static void main(String[] args) {
        Scanner sc = Input.getScannerObject();
        int ch;

        String menu = """
                      1.  Add Item
                      2.  Remove Item
                      3.  Register User
                      4.  Remove User
                      5.  Issue Item
                      6.  Return Item
                      7.  Search Items
                      8.  View Transactions
                      9.  View Users
                      10. View Overdue Items
                      0.  Exit""" ;

        do {
            System.out.println(menu);
            ch = sc.nextInt();
            sc.nextLine();

            switch(ch) {
                case 1:
                    Management.addItem();
                    break;
                
                case 2:
                    Management.removeItem();
                    break;

                case 3:
                    Management.registerUser();
                    break;

                case 4:
                    Management.removeUser();
                    break;

                case 5:
                    Management.issueItem();
                    break;
                
                case 6:
                    Management.removeItem();
                    break;
                
                case 7:
                    Management.searchItems();
                    break;

                case 8: 
                    Management.viewAllTransactions();
                    break;
                
                case 9:
                    Management.viewAllUsers();
                    break;

                case 10: 
                    Management.viewOverdueItems();
                    break;
                    
                case 0:
                    System.out.println("Process Terminated...!");
                    break;

                default:
                    System.out.println("Invalid Choice...!!!");
            }
        }while(ch != 0);
    }
}
