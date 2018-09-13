package training.supportbank;
//imported what I need//
import java.util.ArrayList;

//created new object that stores their wallet, name and transaction//
public class Person {

    //initialise variables and create an ArrayList to store the transaction//
    private String name;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public double wallet;

    //constructor sets name and starting balance//
    public Person(String newName){
        name = newName;
        wallet = 0;
    }
    //returns their name//
    public String getName() {
        return  name;
    }

    public ArrayList<Transaction> getTransaction() {
        return transactions;
    }

    //returns and calculates how much owed/owe//
    public double getWallet() {

        //for loop goes through all the transactions
        for(int i=0; i < transactions.size(); i++){

            //see's if money should be added or subtracted from the value//
            if( transactions.get(i).getFrom().equals(name)) {
                wallet = wallet + transactions.get(i).getAmount();
            } else {
                wallet = wallet - transactions.get(i).getAmount();
            }
        }
        //returns the value of wallet//
        return wallet;
    }

    public void giveTransaction(Transaction newTrans) {
        transactions.add(newTrans);
    }

}

