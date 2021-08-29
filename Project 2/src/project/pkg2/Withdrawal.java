/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfch9
 */
public class Withdrawal implements Runnable {
    
//    public int withdrawalAmount;
    public BankAccount account;
    public Random rand = new Random();
    public int number;
    
    //Constructor
    public Withdrawal(BankAccount account, int number){
//        this.number = number;
        this.account = account;
        this.number = number;
//        this.withdrawalAmount = 0;
    }
    
    //Run override
    @Override
    public void run() {
        
        while(true){
            
            //Delay
            try {
                Thread.sleep(rand.nextInt(50));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //Deposit the money.
            account.withdrawal(rand.nextInt(50) + 1, number);
        }
    }
    
}
