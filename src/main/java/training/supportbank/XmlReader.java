package training.supportbank;

//everything we need to import//
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class XmlReader {

    public static ArrayList<Transaction> readXml(String IOU) throws IOException, ParserConfigurationException, SAXException, ParseException {

        //gets the string of the xml file//

        //creates a document builder//
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //builds the document from the file//
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(IOU);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);

        //making root equal to the whole of the list//
        Element root = doc.getDocumentElement();

        //creates the array list//
        ArrayList<Transaction> transactions = new ArrayList<>();

        //singles out the element "SupportTransaction"//
        NodeList transactionNodes = root.getElementsByTagName("SupportTransaction");

        //for loop that iterates through all the elements//
        for(int i = 0; i < transactionNodes.getLength(); i++) {
            //looks at the SupportTransaction element and takes the date value//
            Element transNode = (Element) transactionNodes.item(i);
            String date =transNode.getAttribute("Date");
            String dates = XmlReader.convertDates(date);

            //gets the description and value but looking at the child of the last class//
            NodeList descriptionNodes = transNode.getElementsByTagName("Description");
            Element descNode = (Element)  descriptionNodes.item(0);
            String reason = descNode.getFirstChild().getNodeValue();

            NodeList valueNodes = transNode.getElementsByTagName("Value");
            Element valNode = (Element)  valueNodes.item(0);
            String amount = valNode.getFirstChild().getNodeValue();

            //sets the node froward one to parties so child classes can be read//
            NodeList partiesNodes = transNode.getElementsByTagName("Parties");
            Element prtyNode = (Element) partiesNodes.item(0);

            //reads both the child classes of party//
            NodeList fromAccountNode = prtyNode.getElementsByTagName("From");
            Element fromNode = (Element)  fromAccountNode.item(0);
            String from = fromNode.getFirstChild().getNodeValue();

            NodeList toAccountNode = prtyNode.getElementsByTagName("To");
            Element toNode = (Element)  toAccountNode.item(0);
            String to = toNode.getFirstChild().getNodeValue();

            //creates the transaction and adds it to the arrayList//
            Transaction trans = new Transaction(from, dates, to, reason, amount);
            transactions.add(trans);

        }

        //returns the ArrayList//
        return transactions;

    }

    //converts the time from OLE to standard time//
    public static String convertDates(String date) throws ParseException {

        double num = Double.parseDouble(date);
        double  mantissa = num - (long) num;
        double hour = mantissa*24;
        double min =(hour - (long)hour) * 60;
        double sec=(min- (long)min) * 60;


        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date baseDate = myFormat.parse("30/12/1899");
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        c.add(Calendar.DATE,(int)num);
        c.add(Calendar.HOUR,(int)hour);
        c.add(Calendar.MINUTE,(int)min);
        c.add(Calendar.SECOND,(int)sec);

        String time = myFormat.format(c.getTime());


        return time;
    }
}



