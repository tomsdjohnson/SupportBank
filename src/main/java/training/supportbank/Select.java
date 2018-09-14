package training.supportbank;
//imports everything we need//
import java.util.Scanner;

public class Select {
    //creates string
    public String fileLocation = " ";

    public Select() {

        //option menu for choosing what transactions to see//
        Boolean test = false;
        System.out.println("Which files would you like to extract?\n" +
                "2013 Transactions [1]\n2014 Transactions [2]\n2015 Transactions [3]");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        //checks what user input//
        while (!test) {
            if (choice.equals("1")) {
                fileLocation = "Transactions2013.json";
                test = true;
            } else if (choice.equals("2")) {
                fileLocation = "Transactions2014.csv";
                test = true;
            } else if (choice.equals("3")) {
                fileLocation = "DodgyTransactions2015.csv";
                test = true;
            } else {
                System.out.println("Your input is not valid! You can only enter a [1], [2] and [3]\nPlease Try again:");
                scanner = new Scanner(System.in);
                choice = scanner.nextLine();
            }
        }
    }

    //returns the file location//
    public String getChoice(){
        return fileLocation;
    }

}
