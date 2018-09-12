package training.supportbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String args[]) throws IOException {
        // Your code here!
        System.out.println("Test!");


        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\Transactions2014.csv" );
        byte[] bytes = Files.readAllBytes(path);
        String IOU =  new String(bytes);



        Pattern p = Pattern.compile("(\\d*\\/\\d*\\/\\d*),(\\w+(\\s\\w*)?),(\\w+(\\s\\w*)?),(\\w+(\\s\\w*)?),(\\d*(.\\d\\d?)?)");
        Matcher m = p.matcher(IOU);

        while(m.find()) {

            String date = m.group(1);
            String from = m.group(2);
            String to = m.group(4);
            String amount = m.group(8);
            String reason = m.group(6);

            System.out.println(from + date + to + reason + amount);

            Transaction tr = new Transaction(from, date, to, reason, amount);

        }


        Person emoloyee = new Person("from");
        System.out.println(emoloyee.getWallet());
        System.out.println(emoloyee.getName());
        emoloyee.updateWallet(10.00);
        System.out.println(emoloyee.getWallet());



    }
}
