package training.supportbank;


public class Person {

    private String name;
    public double wallet;

    public Person(String newName){
        name = newName;
        wallet = 0.00;
    }

    public String getName() {
        return  name;
    }

    public double getWallet() {
        System.out.println("RETUNING");
        return  wallet;
    }

    public void updateWallet(double update) {
        System.out.println("UPDATED");
        wallet = wallet + update;
        return;
    }
}

