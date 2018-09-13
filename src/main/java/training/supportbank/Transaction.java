package training.supportbank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

//new object keeps track of transactions//
public class Transaction {

    //initialising variables//
    private String from;
    private String to;
    private LocalDate date;
    private String reason;
    private double amount;

    //constructor that take all the values that were passed through//
    public Transaction(String newFrom, String newDate, String newTo, String newReason, String newAmount) {

        from = newFrom;
        to = newTo;
        reason = newReason;

        //Makes sure date is added in a correct format//
        if (Pattern.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}", newDate)){
            date = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            System.out.println(newDate);
        }

        //Makes sure amount is number before assigning variable//
        if (Pattern.matches("[0-9]+.?(|[0-9]{1,2})?", newAmount)) {
            amount = Double.parseDouble(newAmount);
        } else {
            System.out.println("AMOUNT ERROR!!!!!!!!!!");
        }
    }
    //returns who the transaction was from//
    public String getFrom() {
        return from;
    }
    //returns who the transaction was to//
    public String getTo() {
        return to;
    }
    //returns the amount the transaction was for//
    public double getAmount() {
        return (amount);
    }
    //returns the reason for the transaction//
    public String getReason() {
        return reason;
    }
    //returns the date this transaction happened//
    public LocalDate getDate() {
        return date;
    }
}