package training.supportbank;

//everything we need to import//
import java.util.ArrayList;
import java.util.HashMap;

public class PersonCreator {

    //this class creates the person and adds transactions//
    public static HashMap<String,Person> createPerson(ArrayList<Transaction> list) {

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
