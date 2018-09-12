package training.supportbank;
//everything we need to import//
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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

        //iterates through hashMap and outputs people's names and wallet
        for (String i : hm.keySet()) {
            System.out.printf("%s %.2f\n", hm.get(i).getName(), hm.get(i).getWallet());
            hm.get(i);

        }

    }
}
