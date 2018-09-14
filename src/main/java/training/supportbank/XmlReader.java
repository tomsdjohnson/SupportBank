package training.supportbank;

//everything we need to import//
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;


public class XmlReader {

    public static ArrayList<Transaction> readXml() throws IOException, ParserConfigurationException, SAXException {

        //gets the path to the xml file//
        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\Transactions2012.xml");
        byte[] bytes = Files.readAllBytes(path);
        String IOU =  new String(bytes);

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


        //
        NodeList transactionNodes = root.getElementsByTagName("SupportTransaction");
        //
        for(int i = 0; i < transactionNodes.getLength(); i++) {
            //
            Element transNode = (Element) transactionNodes.item(i);
            //
            String date =transNode.getAttribute("Date");
            System.out.println(date);
            //
            NodeList descriptionNodes = transNode.getElementsByTagName("Description");
            Element descNode = (Element)  descriptionNodes.item(0);
            System.out.println(descNode.getFirstChild().getNodeValue());
            //
            NodeList valueNodes = transNode.getElementsByTagName("Value");
            Element valNode = (Element)  valueNodes.item(0);
            System.out.println(valNode.getFirstChild().getNodeValue());
            //
            NodeList partiesNodes = transNode.getElementsByTagName("Parties");
            Element prtyNode = (Element) partiesNodes.item(0);
            //
            NodeList fromAccountNode = prtyNode.getElementsByTagName("From");
            Element fromNode = (Element)  fromAccountNode.item(0);
            System.out.println(fromNode.getFirstChild().getNodeValue());
            //
            NodeList toAccountNode = prtyNode.getElementsByTagName("To");
            Element toNode = (Element)  toAccountNode.item(0);
            System.out.println(toNode.getFirstChild().getNodeValue());
        }

        return null;

    }
}



