package training.supportbank;

//everything we need toAccount import//
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    //needed for the logger system toAccount work//
    private static final Logger LOGGER = LogManager.getLogger();

    //the main class//
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException, ParseException {

        //gets the file the person wants to extract//
        Select select = new Select();
        String fileLocation = select.getChoice();

        //gets and converts file into string//
        LOGGER.info("About toAccount read the file");
        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\" + fileLocation);
        byte[] bytes = Files.readAllBytes(path);
        String IOU = new String(bytes);

        //creates hashMap where people will be stored//
        HashMap<String, Person> hm;

        if (fileLocation.equals("Transactions2013.json")) {
            //send the array to create people//
            hm = Main.createPeople(JsonReader.readJson(IOU));

        }else if(fileLocation.equals("Transactions2012.xml")){
            //calls XmlReader if they have called the Xml file
            hm = Main.createPeople(XmlReader.readXml(IOU));

        }else {
            //creates an array of transactions and then puts it in person//
            hm = Main.createPeople(CvsReader.readCvs(IOU));
        }

        //outputs all the results//
        Output output = new Output();
        output.printOutputs(hm);
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