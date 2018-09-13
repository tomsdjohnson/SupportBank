package training.supportbank;
//everything we need to import//
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //needed for the logger system to work//
    private static final Logger LOGGER = LogManager.getLogger();

    //the main class//
    public static void main(String args[]) throws IOException {

        //gets and converts file into string//
        LOGGER.info("About to read the file");
        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\Transactions2014.csv" );
        byte[] bytes = Files.readAllBytes(path);
        String IOU =  new String(bytes);


        //creates search algorithm that gets individual details//
        Pattern p = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");
        Matcher m = p.matcher(IOU);

        //create a hashMap to store the people//
        HashMap<String, Person> hm = new HashMap<>();

        //this finds the first line of the file so it ignores later on
        m.find();

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

        //prints all the outputs and give the users their choice//
        Output output = new Output();
        output.printOutputs(hm);
    }
}
