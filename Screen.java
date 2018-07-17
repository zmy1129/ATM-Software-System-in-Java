
/**
 * class Screen. Represents the screen of the ATM.
 *
 * @author MuyeZhao
 * @version 4/24/2018
 */
public class Screen
{
    // output a message without a newline
    public final void displayMessage(String message)
    {
        System.out.print(message);
    }
    
    // output a message with a newline
    public final void displayMessageLine(String message)
    {
        System.out.println(message);
    }
    
    // output a dollar amount
    public final void displayDollarAmount(double amount)
    {
        System.out.print("$" + String.format("%.2f", amount));
    }
}
