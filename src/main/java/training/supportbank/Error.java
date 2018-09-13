package training.supportbank;

public class Error {

    //creates boolean for if there is error or not//
    private static boolean newError = false;

    //error given if amount is wrong//
    public static void amountError(String error) {
        System.out.println("There has been an error with an Amount entered.\n" +
                "These numbers must only include digits and a decimal place with no more than two digits after the decimal.\n" +
                "Example input: 123.56 (ddd.dd)\n" +
                "Your input:" + error + "\n");
        newError = true;
    }
    //error given if date is wrong//
    public static void dateError(String error) {
        System.out.println("There has been an error with a Date entered.\n" +
                "There must be two digits representing the day, two digits representing the month and for digits representing the year with forward dash's in between.\n" +
                "Example input: 01/01/2000 (dd/MM/yyyy)\n" +
                "Your input:" + error + "\n");
        newError = true;
    }
    //returns if there were errors or not//
    public static boolean getErrors() {
        return newError;
    }
}
