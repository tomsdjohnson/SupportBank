package training.supportbank;

//new object keeps track of transactions//
public class Transaction {

    //initialising variables//
    private String from;
    private String to;
    private String date;
    private String reason;
    private double amount;

    //constructor that take all the values that were passed through//
    public Transaction(String newFrom, String newTo, String newDate, String newReason, String newAmount) {
        from = newFrom;
        to = newTo;
        date = newDate;
        reason = newReason;
        amount = Double.parseDouble(newAmount);
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
    public String getDate() {
        return date;
    }
}