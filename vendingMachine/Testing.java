/** Class with main method that tests VendingMachineInventory and 
 * VendingMachineCoinbox
 * 
 * @author Anna Lieb
 * @author Ari Geller
 * @version 1.0
 * 
 */
import java.util.Arrays;

public class Testing {

    public static void main(String[] args) {
        testingPart1(); 
        testingPart2(); 

    }
    /**
     * Tests for P3: Continuing the Vending Machine Experience (part 1) of Homework 5
     */
    public static void testingPart1(){
        System.out.println("** Part 1 Tests **");
        /* Inventory test */
        VendingMachineInventory vmi = new VendingMachineInventory(); 
        System.out.println(vmi.toString());
        /* Coinbox tests */
        VendingMachineCoinbox vmc = new VendingMachineCoinbox(); 
        System.out.println("Amount in Machine: " + vmc.changeValue());
        System.out.print("Coins in Machine: ");
        vmc.finalize(); 
        System.out.println("Dispensing 96 cents: " + Arrays.toString(vmc.dispenseChange(96)));
        System.out.print("Coins in Machine: ");
        vmc.finalize(); 
        System.out.println("Amount in Machine: " + vmc.changeValue());
        vmc.acceptCoin('H');
        System.out.println("Amount in machine after vmc.acceptCoin('H'): " + vmc.changeValue());
        vmc.acceptCoin('P');
        System.out.println("Amount in machine after vmc.acceptCoin('P'): " + vmc.changeValue());
    }

    /**
     * Tests for P4: Continuing the Vending Machine Experience (part 2) of Homework 5
     */
    public static void testingPart2(){
        System.out.println("** Part 2 Tests **");
        VendingMachineInventory vmi = new VendingMachineInventory("inventory.csv"); 
        System.out.println(vmi.toString());
        vmi.vendItem(1); // should be sucessful
        vmi.vendItem(1); // should be unsuccessful (none left)
        vmi.vendItem(3); // should be unsuccessful (not a valid item num)
        System.out.println(vmi.isEmpty()); // should be false
        for (int i = 0; i < 10; i++){
            vmi.vendItem(0);
        }
        System.out.println(vmi.isEmpty()); // should be true
        System.out.println("Inventory after 1 kitkat vended and 10 Potato Chips Vended");
        System.out.println(vmi.toString());

    }
}
