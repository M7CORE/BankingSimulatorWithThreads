/* Name: Maria Corella
Course: CNT 4714 Spring 2021
Assignment Title: Project 2 - Synchronized, Cooperating Threads under Locking
Due Date: February 17, 2021
*/
package project.pkg2;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Project2 {

    int bankAccount = 0;
    
    public static void main(String[] args) {
        
        ExecutorService application = Executors.newFixedThreadPool(14);

        BankAccount account = new BankAccount();
        
        System.out.println("Deposit Threads\t\t\tWithdrawal Threads\t\tBalance");
        System.out.println("---------------\t\t\t------------------\t\t-------");
        
        //Start withdrawal threads
        for (int i = 0; i < 9; i++){
            try {
                application.execute(new Withdrawal (account, i+1));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        //Start deposit threads
        for (int j = 0; j < 5; j++){
            try {
                application.execute(new Deposit(account, j+1));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        application.shutdown();   
    }
    
}
