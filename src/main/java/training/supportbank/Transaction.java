package training.supportbank;

//import everything
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

//new object keeps track of transactions//
public class Transaction {

    //initialising variables//
    public String fromAccount;
    public String toAccount;
    public LocalDate date;
    public String narrative;
    public double amount;

    //constructor that take all the values that were passed through//
    public Transaction(String newFrom, String newDate, String newTo, String newReason, String newAmount) {

        fromAccount = newFrom;
        toAccount = newTo;
        narrative = newReason;

        //Makes sure date is added in a correct format//
        if (Pattern.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}", newDate)){
            date = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            Error.dateError(newDate);
        }

        //Makes sure amount is number before assigning variable//
        if (Pattern.matches("[0-9]+.?(|[0-9]{1,2})?", newAmount)) {
            amount = Double.parseDouble(newAmount);
        } else {
            Error.amountError(newAmount);
        }
    }
    //returns who the transaction was fromAccount//
    public String getFromAccount() {
        return fromAccount;
    }
    //returns who the transaction was toAccount//
    public String getToAccount() {
        return toAccount;
    }
    //returns the amount the transaction was for//
    public double getAmount() {
        return (amount);
    }
    //returns the narrative for the transaction//
    public String getNarrative() {
        return narrative;
    }
    //returns the date this transaction happened//
    public LocalDate getDate() {
        return date;
    }
}