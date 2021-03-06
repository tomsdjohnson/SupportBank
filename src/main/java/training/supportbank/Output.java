package training.supportbank;
//everything we need toAccount import//
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Output {

    //initialise a HashMap and boolean toAccount be used later//
    public Boolean test = false;
    public HashMap<String, Person> hm = new HashMap<>();

    //constructor displays message once
    public Output() {

        //checks if there have been any errors if so closes program//
        if (Error.getErrors()) {
            System.out.println("*******WARNING ERRORS ABOVE PLEASE READ*******\n" +
                    "****PROGRAM WILL NOW EXIT PLEASE FIX ERRORS***");
            System.exit(0);
        }

        //welcomes the person and gives them their options//
        System.out.println("List of how much everyone is owed/owes [1]\nList an individuals transactions [2]");
    }

    //creating new class that can be called//
    public void printOutputs(HashMap<String, Person> newHm) {

        //creates variable hm//
        hm = newHm;

        //start of the while loop//
        while(!test) {

            //reads what user wrote on console//
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            //iterates through hashMap and outputs people's names and wallet//
            if (choice.equals("1")) {
                for (String i : hm.keySet()) {
                    System.out.printf("%s %.2f\n", hm.get(i).getName(), hm.get(i).getWallet());
                    hm.get(i);
                }
                test = true;

                //checks which person they want toAccount print out and then prints them out//
            } else if (choice.equals("2")) {
                while(!test) {

                    //user enters who they'd like toAccount see//
                    System.out.println("Who's transactions would you like toAccount see?");
                    scanner = new Scanner(System.in);
                    String person = scanner.nextLine();

                    //displays all the transactions for a certain person
                    if (hm.containsKey(person)) {
                        ArrayList<Transaction> trans = hm.get(person).getTransaction();
                        for (int i = 0; i < trans.size(); i++) {
                            System.out.println(trans.get(i).getDate() + " - " + trans.get(i).getToAccount() + " owes " + trans.get(i).getFromAccount()
                                    + " £" + trans.get(i).getAmount() + " - Reason: " + trans.get(i).getNarrative());
                        }
                        test = true;

                        //if user doesn't put valid name lets them try again//
                    } else {
                        System.out.println("That person does not exist!\nPlease Try again");
                    }
                }
                //if option is not valid lets user try again//
            } else {
                System.out.println("Your input is not valid! You can only enter a [1] or [2]\nPlease Try again:");
            }
        }


    }

}

