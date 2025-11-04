package BankManagementSystem;

import java.util.*;

import javax.sound.midi.SysexMessage;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // create Account 
        SevingAccount sa = new SevingAccount("Ramprasad", 456, 150);
        CurrentAccount ca = new CurrentAccount("Sukman", 320, 1000);

        System.out.println("======== Welcome to My Java Bank =========");
        int choice;
        do{
            System.out.println("\n1. Deposit in sevings ");
            System.out.println("2. withdraw of seving");
            System.out.println("3. add Interest (seving)");
            System.out.println("4. Deposit in current");
            System.out.println("5. Withdraw from current");
            System.out.println("6. Show Account Details");
            System.out.println("0. exit");

            System.out.print("Enter Your Choice");
            choice = sc.nextInt();
            switch(choice){
                case 1 :
                    System.out.print("Enter amount (seving account) to Deposit : ");
                    sa.deposit(sc.nextDouble());
                    break;
                case 2 :
                    System.out.print("Enter amount to Withdraw : ");
                    sa.withdraw(sc.nextDouble());
                    break;
                case 3 :
                    sa.addInterest();
                    break;
                case 4 :
                    System.out.print("Enter amount to Deposit : ");
                    ca.deposit(sc.nextDouble());
                    break;
                case 5 :
                    System.out.print("Enter amount to Withdraw : ");
                    ca.withdraw(sc.nextDouble());
                    break;
                case 6 : 
                    System.out.print("\n=========== seving account ===========");
                    sa.showDetails();
                    System.out.print("\n=========== current account ===========");
                    ca.showDetails();
                    break;
                case 0 :
                    System.out.println("Thanking for using java Bank");
                    break;
                default :
                    System.out.println("invalid choice !!");
            }
        } while(choice !=0);
            sc.close();
    }
}

// this is Abstract class here
abstract class Account {
    private String accountHolderName;
    private int accountNumber;
    protected double balance;

    // yha per constructor ka use kiya hu
    public Account(){}
    public Account(String name, int accNumber, double balance){
        this.accountHolderName=name;
        this.accountNumber=accNumber;
        this.balance=balance;
    }

    // Getter Methods
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public double getBalance(){
        return balance;
    }

    // common methods 
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposit rs : "+amount);
        System.out.println("new balance rs : "+balance);
    }

    // yha per Abstract Methods banaya hu (Abstraction)
    public abstract void withdraw(double amount);

    // Holder ka info print kar rhe hai 
    public void showDetails(){
        System.out.println("Account Holder Name : "+accountHolderName);
        System.out.println("Account Number : "+accountNumber);
        System.out.println("Current Balance : "+balance);
    }
}
// inheritance + polimorphism 
// yha per maine ek seving account ke liye class banaya hai
class SevingAccount extends Account {
    private double interestRate = 0.05;
    public SevingAccount(String name, int accNumber, double balance){
        super(name, accNumber, balance);
    }

    @Override
    public void withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
            System.out.println("Withdraw rs : "+amount);
        }else{
            System.out.println("Insuficient Balance : ");
        }
        System.out.println("Remaining Balance : "+balance);
    }

    public void addInterest(){
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest added rs : "+interest);
    }
}
// yha per Current Acccount ka class bana rha hu 
// Current Account ka class bana rha hu
class CurrentAccount extends Account {
    private double overDraftLimit = 10000;
    public CurrentAccount(String name, int accNumber, double balance){
        super(name, accNumber, balance);
    }
    public void withdraw(double amount){
        if(amount <= balance + overDraftLimit){
            balance = balance - amount;
            System.out.println("withdraw rs : "+amount);
        }else{
            System.out.println("overdraft limit excuted");
        }
        System.out.println("Remaining Balance : "+balance);
    }
}