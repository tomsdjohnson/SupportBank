package training.supportbank;

import java.util.ArrayList;

public class Person {

    private String name;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public double wallet;

    public Person(String newName){
        name = newName;
        wallet = 0.00;
    }

    public String getName() {
        return  name;
    }

    public double getWallet() {
        System.out.println("RETUNING");
        return wallet;
    }

    public void giveTransaction(Transaction newTrans) {
        transactions.add(newTrans);
    }

}

