/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2;

import java.util.Random;

/**
 *
 * @author mfch9
 */
public class Deposit implements Runnable {
    
    public int number;
    public BankAccount account;
    public Random rand = new Random();
    
    //Constructor
    public Deposit(BankAccount account, int number){
        this.account = account;
        this.number = number;
    }
    
    @Override
    public void run() {
        
        while(true){
            //Delay
            try {
                Thread.sleep(rand.nextInt(300));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //Deposit the money.
            account.deposit(rand.nextInt(250) + 1, number);
        }
    }
    
}
