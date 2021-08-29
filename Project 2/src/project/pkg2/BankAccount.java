package project.pkg2;

import java.nio.Buffer;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccount {
    
    //Instantiate the lock, no fairness lock
    private Lock accessLock = new ReentrantLock(false);   
    
    //Instantiate the condition. This condition for when not enough to 
    //withdrawal
    private Condition canWithdrawal = accessLock.newCondition();
    
    public int accountBalance = 0;
//    private boolean occupied = false;
    
    public void deposit (int value, int number){
        
        //Acquire lock
        accessLock.lock();
        
        //if occupied/not occupied doesnt matter
        //Add deposit to account
        accountBalance += value;
        System.out.println("Thread D" + number + " deposits $" + value 
                + "\t\t\t\t\t\t(+) Balance is $" + accountBalance);
        //let withdrawal know that they can procede
        canWithdrawal.signal();
        //Release lock
        accessLock.unlock();
    }
    
    public void withdrawal (int value, int number){
        
        //obtain lock
        accessLock.lock();
        
        //Ensure enough money to withdrawal
        if (accountBalance >= value){
            accountBalance -= value;
            System.out.println("\t\t\t\tThread W" + number + " withdraws $" + value 
                    + "\t\t(-) Balance is $" + accountBalance);
        }
        else {
            System.out.println("\t\t\t\tThread W" + number + " withdraws $" + value 
                    + "\t\t(***) WITHDRAWAL BLOCKED - INSUFFFICIENT FUNDS");
            try {
                //wait to be able to withdrawal
                canWithdrawal.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }   
        }
        //release lock
        accessLock.unlock();
    }
    
    public int get(){
        return accountBalance;
    }
    
}
