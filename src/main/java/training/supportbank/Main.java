package training.supportbank;
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

        Path path = Paths.get("C:\\Work\\Training\\SupportBank-Java-Template\\Transactions2014.csv" );
        byte[] bytes = Files.readAllBytes(path);
        String IOU =  new String(bytes);

        Pattern p = Pattern.compile("(\\d*\\/\\d*\\/\\d*),(\\w+(\\s\\w*)?),(\\w+(\\s\\w*)?),(\\w+(\\s\\w*)?),(\\d*(.\\d\\d?)?)");
        Matcher m = p.matcher(IOU);


        ArrayList<Transaction> list = new ArrayList<>();
        HashMap<String, Person> hm = new HashMap<>();

        while(m.find()) {

            String date = m.group(1);
            String from = m.group(2);
            String to = m.group(4);
            String amount = m.group(8);
            String reason = m.group(6);

            System.out.println("Adding transactions...");
            list.add(new Transaction(from, date, to, reason, amount));

            if (hm.containsKey(from)) {

            }else {
                hm.put(from, new Person(from));
            }

            if (hm.containsKey(to)) {

            }else {
                hm.put(to, new Person(to));
            }
        }

        System.out.println(list);



        //Person employee = new Person("from");
        //System.out.println(employee.getWallet());
        //System.out.println(employee.getName());
        //employee.updateWallet(10.00);
        //System.out.println(employee.getWallet());



    }
}
