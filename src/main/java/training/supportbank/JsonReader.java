package training.supportbank;

//imports everything we need//
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonReader {

    //Creates an Array with all transactions and then converted into arrayList//
    public static ArrayList<Transaction> readJson(String IOU){

        //creates and creates an array list called transactionList//
        Gson gson = buildGson();
        Transaction[] transaction = gson.fromJson(IOU, Transaction[].class);
        ArrayList<Transaction> transactionList = new ArrayList<>(Arrays.asList(transaction));

        //returns the array list//
        return transactionList;

    }

    private static Gson buildGson() {

        //loading and converting fromAccount Json into java objects//
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDate.parse(jsonElement.getAsString())
        );
        return gsonBuilder.create();
    }

}
