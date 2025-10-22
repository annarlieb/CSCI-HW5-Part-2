import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Class representing the what is available in a vending machine.
 * 
 * @author Ari Geller
 * @author Anna Lieb
 * @version 2.0
 * 
 */

public class VendingMachineInventory {

    private final static int INV_AMT = 0;
    private final static int INV_COST = 1;

    private String itemNames[];
    private int numcosts[][];

    /**
     * Default constructor. Instatiates 5 default items
     */
    public VendingMachineInventory(){
       itemNames = new String[] {"KitKats","Hershey Bars","Crunch Bars", "Skittles", "Nerds"}; 
       numcosts = new int[5][2]; 
       numcosts[0] = new int[] {5,99}; 
       numcosts[1] = new int[] {10,199}; 
       numcosts[2] = new int[] {7,299}; 
       numcosts[3] = new int[] {1,59}; 
       numcosts[4] = new int[] {6,99}; 
    }

    /**
     * Constructor that takes a filename. 
     * Reads items from file and loads data into appropriate arrays
     */
    public VendingMachineInventory(String filename){
        File inventory = new File(filename); 
        try{ // need to try otherwise get unhandled file not found exception
            Scanner inventoryScannerCount = new Scanner(inventory); 
            
            // first need to count how many items we are adding
            // NOTE: NOT SURE IS THERE IS A BETTER WAY TO DO THIS
            int numItems = 0; 
            while (inventoryScannerCount.hasNextLine()){
               numItems++;
               inventoryScannerCount.nextLine(); 
            }

            Scanner inventoryScanner = new Scanner(inventory); 
            itemNames = new String[numItems]; 
            numcosts = new int[numItems][2]; 
            for(int i = 0; i < numItems; i++ ){
                String[] line = inventoryScanner.nextLine().split(","); 
                itemNames[i] = line[2]; 
                numcosts[i][INV_AMT] = Integer.parseInt(line[INV_AMT]); 
                numcosts[i][INV_COST] = Integer.parseInt(line[INV_COST]); 
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error: File not found");
        }
    }

    /**
     * Returns boolean representing if machine is empty
     * @return true if no items are for sale, false if items are for sale
     */
    public boolean isEmpty(){
        for (int i = 0; i < itemNames.length; i++){
            if (numcosts[i][INV_AMT] > 0){
                return false; 
            }
        }
        // exited for loop meaning there were no items in which the amount was > 0
        return true; 
    }

    /**
     * Attemts to vend a specified item. Updates amount if successful. 
     * @param itemNum the int representing the item number
     * @return boolean representing sucessful (true) or insuccessful (false) vending
     */
    public boolean vendItem(int itemNum){

        // cannot vend if no items of that type exist or no more available
        if (itemNum > itemNames.length){
            System.out.println("CANNOT VEND");
            return false; 
        }
        if (numcosts[itemNum][INV_AMT] < 1){
            System.out.println("CANNOT VEND");
            return false; 
        }

        // can successfully vend so update amount
        numcosts[itemNum][INV_AMT]--; 
        System.out.println(itemNames[itemNum] + "(item num " + itemNum + ") was successfully vended");
        return true; 
    }

    /**
     * returns cost of an item     
     * @param itemNum the int representing the item number
     * @return int representing the price of the item in cents
     */
    public int getCostItem(int itemNum){
        return numcosts[itemNum][INV_COST]; 
    }



    /**
     * toString that returns string with how many of each item is available and the cost of each. 
     */
    public String toString(){
        String toReturn = "";
        for (int i = 0; i < itemNames.length; i ++){
            toReturn += numcosts[i][0]; 
            toReturn += " " + itemNames[i] + ", "; 
            toReturn += numcosts[i][1] + " cents each"; 
            if (i < itemNames.length - 1) toReturn += "\n"; 
        }
        return toReturn; 
    }

    
}
