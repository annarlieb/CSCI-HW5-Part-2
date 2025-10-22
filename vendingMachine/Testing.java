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
        // testingPart1(); 
        testingPart2(); 

    }

    public static void testingPart1(){
        /* Inventory test */
        VendingMachineInventory vmi = new VendingMachineInventory(); 
        System.out.println(vmi.toString());
        /* Coinbox tests */
        VendingMachineCoinbox vmc = new VendingMachineCoinbox(); 
        System.out.println("Amount in Machine: " + vmc.changeValue());
        // System.out.println("Coins in Machine: " + Arrays.toString(vmc.changeSet)); // commented out since private
        System.out.println("Dispensing 96 cents: " + Arrays.toString(vmc.dispenseChange(96)));
        // System.out.println("Coins in Machine: " + Arrays.toString(vmc.changeSet)); // commented out since private
        System.out.println("Amount in Machine: " + vmc.changeValue());
        vmc.acceptCoin('H');
        System.out.println("Amount in machine after vmc.acceptCoin('H'): " + vmc.changeValue());
        vmc.acceptCoin('P');
        System.out.println("Amount in machine after vmc.acceptCoin('P'): " + vmc.changeValue());
    }

    public static void testingPart2(){
        VendingMachineInventory vmi = new VendingMachineInventory("inventory.csv"); 
        System.out.println(vmi.toString());
    }
}
