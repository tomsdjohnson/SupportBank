package training.supportbank;

import java.time.LocalDate;

public class Transaction {

    private String from;
    private String to;
    private String date;
    private String reason;
    private double amount;

    public Transaction(String newFrom, String newTo, String newDate, String newReason, String newAmount) {
        from = newFrom;
        to = newTo;
        date = newDate;
        reason = newReason;
        amount = Double.parseDouble(newAmount);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return (amount);
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }
}