import java.util.Scanner;
/*
 * Class that acts as a Vending Machine
 * 
 * @author Anna Lieb
 * @author Ari Geller
 * @version 1.0
 * 
 */

public class VendingMachine {

    private final static char[] validInputs = {'P', 'N', 'D', 'Q', 'H', 'G', 'v','q'};
    private final static int[] acceptable_coin_vals = {1, 5, 10, 25, 50, 100};

    /**
     * Main method that starts the execution. 
     * also creates instances of VendingMachineInventory and VendingMachineCoinbox
     */
    public static void main(String[] args) {
        VendingMachineInventory vmInventory; 
        VendingMachineCoinbox vmCoinbox; 
        if (args.length > 0){
            String invString = args[0]; 
            String coinsString = args[1]; 
            vmInventory = new VendingMachineInventory(invString);
            vmCoinbox = new VendingMachineCoinbox(coinsString);
        }
        else{
            vmInventory = new VendingMachineInventory();
            vmCoinbox = new VendingMachineCoinbox(); 
        }
        execute(vmInventory, vmCoinbox, 0); // user originally has no change in machine
    }

    /**
     * Main execution block. Displays inventory and how much user has placed in machine. 
     * Calls function that recieves and validates user input that determines if user is
     * depositing coins, vending or quitting. handles each case of user input. 
     * 
     */
    public static void execute(VendingMachineInventory vmInventory, VendingMachineCoinbox vmCoinbox, int userChange){
        System.out.println("Inventory:");
        System.out.println(vmInventory.toString()); 
        System.out.println("You have placed " + userChange + " cents in the machine.");
        
        if (vmCoinbox.changeValue() == 0) System.out.println("THIS MACHINE CANNOT CURRENTLY DISPENSE ANY CHANGE.");
        if (vmInventory.isEmpty()) System.out.println("THIS MACHINE CANNOT CURRENTLY SELL ANY ITEMS");

        boolean afterVend = false; 
        boolean quit = false; 

        while (!quit){
            char userInput = getValidInput(); 
            
            if (userInput == 'v'){ // vend
                userChange = vend(vmInventory, vmCoinbox, userChange); 
                quit = true; // to stop loop
                afterVend = true; // to return to step 1
            }

            else if (userInput == 'q'){ // quit
                quit = true; 
            }
            
            else { // user entered a coin type
                int amount = getCoinAmount(userInput);
                userChange += amount; 
                vmCoinbox.acceptCoin(userInput);   
            }
        }
        if (afterVend) execute(vmInventory, vmCoinbox, userChange); // repeate from step 1 if after vend

    }

    /**
     * Asks for an item to vend and attempts to vend the requested item
     * @return the updated userChange
     */
    private static int vend(VendingMachineInventory vmInventory, VendingMachineCoinbox vmCoinbox, int userChange){
        System.out.println("Enter the item number for the item you'd like to vend: ");
        Scanner in = new Scanner(System.in);
        int itemNum = in.nextInt(); 

        if (vmInventory.getCostItem(itemNum) > userChange){
            System.out.println("You did not put in enough money to vend this item.");
            return userChange; 
        }
        boolean vended = vmInventory.vendItem(itemNum); 
        if (vended){
            userChange -= vmInventory.getCostItem(itemNum); 
        }
        return userChange; 

    }

    /**
     * Asks for user input until user inputs a valid char. 
     * @return the valid character
     */
    private static char getValidInput(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a valid char to input a coin, \"v\" to vend or \"q\" to quit.");
        boolean validInput = false; 
        char charInput = ' '; // will be overridden
        while (!validInput){
            String input = in.nextLine().trim(); 
            if (input.length() != 1){
                System.out.println("Invalid Input. Not a character. Enter a valid char to input a coin, \"v\" to vend or \"q\" to quit.");
            }
            else{
                charInput = input.charAt(0); // convert to char
                for (char valid : validInputs){
                    if (valid == charInput) validInput = true; 
                }
                if (!validInput) System.out.println("not a valid input. Enter a valid char to input a coin, \"v\" to vend or \"q\" to quit.");
            }
        }
        return charInput; 
    }

    /**
     * returns the cents amount for a coin
     * @param c the character that is the lable for the coin
     * @return the amount that coin is worth in cents
     */
    private static int getCoinAmount(char c){
        for (int i = 0; i < acceptable_coin_vals.length; i++){
            if (c == validInputs[i]){
                return acceptable_coin_vals[i];
            }
        }
        return -1; 
    }



   
}
