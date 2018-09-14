package training.supportbank;

//everything we need toAccount import//
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //needed for the logger system toAccount work//
    private static final Logger LOGGER = LogManager.getLogger();

    //the main class//
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException {

        XmlReader.readXml();
//
        //gets the file the person wants to extract//
        Select select = new Select();
        String fileLocation = select.getChoice();



        //gets and converts file into string//
        LOGGER.info("About toAccount read the file");
        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\" + fileLocation);
        byte[] bytes = Files.readAllBytes(path);
        String IOU =  new String(bytes);

        HashMap<String, Person> hm;
        if (fileLocation != "Transactions2013.json") {
            //creates an array of transactions and then puts it in person//
            hm = Main.createPeople(Main.getList(IOU));
        } else {
            //Creates an Array with all transactions and then converted into arrayList and then sent to createPerson//
            Gson gson = buildGson();
            Transaction[] transaction = gson.fromJson(IOU, Transaction[].class);
            ArrayList<Transaction> transactionList = new ArrayList<>(Arrays.asList(transaction));
            hm = Main.createPeople(transactionList);
        }

        //outputs all the results//
        Output output = new Output();
        output.printOutputs(hm);
        }


    //loading and converting fromAccount Json into java objects//
    private static Gson buildGson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDate.parse(jsonElement.getAsString())
        );
        return gsonBuilder.create();
    }



    //new class creates transactions//
    private static ArrayList getList(String newIOU) {

        //stores the string//
        String IOU = newIOU;

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



    //this class creates the person and adds transactions//
    private static HashMap<String,Person> createPeople(ArrayList<Transaction> list) {

        //creates the hashMap that will store the data//
        HashMap<String, Person> hm = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {

            //gets the two people's names involved in the transaction//
            String  from = list.get(i).getFromAccount();
            String  to = list.get(i).getToAccount();

            //creates new person if they don't exist//
            if (!hm.containsKey(from)) {
                hm.put(from, new Person(from));
            }
            if (!hm.containsKey(to)) {
                hm.put(to, new Person(to));
            }
            //passes the trans over toAccount the person object//
            hm.get(from).giveTransaction(list.get(i));
            hm.get(to).giveTransaction(list.get(i));
        }

        //returns the hashMap//
        return hm;
    }
}
