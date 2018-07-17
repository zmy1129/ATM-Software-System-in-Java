
/**
 * class CashDispenser. Represents the cash dispenser of the ATM.
 *
 * @author MuyeZhao
 * @version 4/24/2018
 */
public class CashDispenser
{
    private static final int COUNT = 500;
    private int count;
    // constructor initializes bill count to 500
    public CashDispenser()
    {
        count = COUNT;
    }
    
    // simulates dispensing of specified amount of cash
    public void dispenseCash(int amount)
    {
        int billsRequired = amount / 20; // number of $20 bills required
        count -= billsRequired; // update the count of bills
    }
    
    // indicates whether cash dispenser can dispense desired amount
    public final boolean isSufficientCashAvailable(int amount)
    {
        int billsRequired = amount / 20; // number of $20 bills required

        if (count >= billsRequired) // enough bills are available
        {
            return true; 
        } 
        else // not enough bills are available
        {
            return false;
        }
    }
}
