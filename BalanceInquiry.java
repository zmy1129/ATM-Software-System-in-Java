
/**
 * class BalanceInquiry.
 *
 * @author MuyeZhao
 * @version 5/6/2018
 */
public class BalanceInquiry extends Transaction
{
    public BalanceInquiry(int userAccountNumber, BankDatabase bankDatabase, Screen screen)
    {
        super(userAccountNumber, bankDatabase, screen);
    }
    
    public void execute()
    {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
        
        // display the balance information on the screen
        screen.displayMessageLine("\nBalance Information:");
        screen.displayMessage(" - Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("\n - Total balance: ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
