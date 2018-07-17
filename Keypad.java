/**
 * class Keypad. Represents the keypad of the ATM.
 *
 * @author MuyeZhao
 * @version 4/24/2018
 */

import java.util.Scanner;

public class Keypad
{
    Scanner kb = new Scanner(System.in);
    
    // return an integer value entered by user
    public final int getInput() 
    {
        int input;
        input = kb.nextInt();
        return input;
    }
}
