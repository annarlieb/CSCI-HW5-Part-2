import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Class representing the coin mechanics of a vending machine,
 * with the ability to accept coins and dispense change correctly according the
 * coins currently in the machine.
 * 
 * @author Ari Geller
 * @author Anna Lieb
 * @version 2.0
 * 
 */
public class VendingMachineCoinbox {

    private final static char[] acceptable_coin_chars = {'P', 'N', 'D', 'Q', 'H', 'G'};
    private final static int[] acceptable_coin_vals = {1, 5, 10, 25, 50, 100};
    
    private final Scanner scanner;

    private int[] changeSet;

    /**
     * Default constructor. Every instance begins with 10 of each coin
     */
    public VendingMachineCoinbox() {
        scanner = new Scanner(System.in);
        changeSet = new int[6];
        for (int i = 0; i < changeSet.length; i++) {
            changeSet[i] = 10;
        }
    }

    /**
     * Constructor that takes a filename. 
     * Fills changeSet with appropriate values from the file
     */
    public VendingMachineCoinbox(String filename){
        scanner = new Scanner(System.in);
        File coinbox = new File(filename); 
        try{ // need to try otherwise get unhandled file not found exception
            Scanner coinboxScanner = new Scanner(coinbox); 
            String[] line = coinboxScanner.nextLine().split(","); 
            for (int i = 0; i < line.length; i++){
                changeSet[i] = Integer.parseInt(line[i]); 
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error: File not found");
        }
    }

    /**
     * Returns the total value of the change in the machine, expressed in cents
     * @return The amount of money in the machine, expressed in cents
     */
    public int changeValue() {
        int runningTotal = 0;
        for (int i = 0; i < changeSet.length; i++) {
            int coinValue = acceptable_coin_vals[i];
            int valueOfCurrentCoins = changeSet[i] * coinValue;
            runningTotal += valueOfCurrentCoins;
        }

        return runningTotal;
    }

    /**
     * Accepts a new coin into the machine
     * @param coinType The char represengting the type of coin fed into the machine
     */
    public void acceptCoin(char coinType) {
        for (int i = 0; i < acceptable_coin_chars.length; i++) {
            if (coinType == acceptable_coin_chars[i]) {
                changeSet[i]++;
            }
        }
    }

    /**
     * Dispenses change as efficiently as possible,
     * taking into account the actual amount of each coin currently in the machine
     * @param cents The int representing the amount of money (in cents) to make change for
     * @return An array representing the different amounts of each coin that are provided as change
     */
    public int[] dispenseChange(int cents) {

        //all values are 0 by default, which is what we want
        int[] changeToDispense = new int[6];

        int centsRemaining = cents;
        for (int i = 5; i >= 0; i--) {
            //If we don't have any of this coin, or if the coin is larger than the amount left to pay, just move on
            if (changeSet[i] == 0 || acceptable_coin_vals[i] > centsRemaining) {
                continue;
            }
            int valueOfCurrentCoin = acceptable_coin_vals[i];
            int maxCoinsUsed = centsRemaining / valueOfCurrentCoin;

            //We may not have enough of a coin to make "optimal" change
            int actualCoinsUsed = Math.min(maxCoinsUsed, changeSet[i]);

            changeToDispense[i] = actualCoinsUsed;
            changeSet[i] -= changeToDispense[i]; // update how many of that coin in machine
            centsRemaining -= (actualCoinsUsed * valueOfCurrentCoin);
        }


        return changeToDispense;
    }

    /**
     * Prints the current inventory in changeSet to the console
     * (note: this is public instead of private as stated in directions 
     * since syntax error when private)
     */
    public void finalize(){
        String toPrint = ""; 
        for (int i = 0; i < changeSet.length; i++){
            toPrint+=changeSet[i];
            if (i<changeSet.length-1) toPrint += ","; // add comma unless after last value
        }
        System.out.println(toPrint);
    }

    

    
}