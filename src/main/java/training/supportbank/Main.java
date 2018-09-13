package training.supportbank;
//everything we need to import//
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String args[]) throws IOException {

        //gets and converts file into string//
        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\Transactions2014.csv" );
        byte[] bytes = Files.readAllBytes(path);
        String IOU =  new String(bytes);

        //creates search algorithm that gets individual details//
        Pattern p = Pattern.compile("(\\d\\d\\/\\d\\d\\/\\d{4}),([\\w\\s]+),([\\w\\s]+),([\\w\\s-]+),([0-9.]+)");
        Matcher m = p.matcher(IOU);

        //create array and hashMap to store the transactions and people//
        ArrayList<Transaction> list = new ArrayList<>();
        HashMap<String, Person> hm = new HashMap<>();

        //while loop until all details have been found//
        while(m.find()) {

            //creating variables for all the details//
            String date = m.group(1);
            String from = m.group(2);
            String to = m.group(3);
            String amount = m.group(5);
            String reason = m.group(4);

            //creating a variable for the transaction//
            Transaction trans = new Transaction(from, date, to, reason, amount);

            //ads trans to array list//
            list.add(trans);

            //creates new person if they don't exist//
            if (!hm.containsKey(from)) {
                hm.put(from, new Person(from));
            }
            if (!hm.containsKey(to)) {
                hm.put(to, new Person(to));
            }
            //passes the trans over to the person object//
            hm.get(from).giveTransaction(trans);
            hm.get(to).giveTransaction(trans);
        }
        //Welcomes the person and gives them their options//
        System.out.println("WELCOME!\nPlease enter the number of your choice!\n\n" +
                "List of how much everyone is owed/owes [1]\nList an individuals transactions [2]");

        //create test which tells weather to stay in the while loop or not//
        Boolean test = false;

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

            //checks which person they want to print out and then prints them out//
            } else if (choice.equals("2")) {
                while(!test) {

                    //user enters who they'd like to see//
                    System.out.println("Who's transactions would you like to see?");
                    scanner = new Scanner(System.in);
                    String person = scanner.nextLine();

                    //displays all the transactions for a certain person
                    if (hm.containsKey(person)) {
                        ArrayList<Transaction> trans = hm.get(person).getTransaction();
                        for (int i = 0; i < trans.size(); i++) {
                            System.out.println(trans.get(i).getTo() + " - " + trans.get(i).getDate() + " owes " + trans.get(i).getFrom()
                                    + " £" + trans.get(i).getAmount() + " - Reason: " + trans.get(i).getReason());
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
