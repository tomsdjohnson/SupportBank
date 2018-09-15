package training.supportbank;

//import classes//
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CvsReader {

    public static ArrayList<Transaction> readCvs(String IOU) {

        //creates search algorithm that gets individual details//
        Pattern p = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");
        Matcher m = p.matcher(IOU);

        //create an Array list to store the Transactions//
        ArrayList<Transaction> list = new ArrayList<>();

        //this finds the first line of the file so it ignores later on//
        m.find();

        //while loop until all details have been found//
        while(m.find()) {

            //creating variables for all the details//
            String date = m.group(1);
            String from = m.group(2);
            String to = m.group(3);
            String amount = m.group(5);
            String reason = m.group(4);

            //creating a variable for the transaction and adds it to list//
            Transaction trans = new Transaction(from, date, to, reason, amount);
            list.add(trans);
        }

        //returns the array full of transactions//
        return list;

    }

}
