
/**
 * class Deposit.
 *
 * @author MuyeZhao
 * @version 5/6/2018
 */
public class Deposit extends Transaction
{
    static final int CANCELED = 0;
    private Keypad keypad;
    private DepositSlot depositSlot;
    
    public Deposit(int userAccountNumber, BankDatabase bankDatabase, Screen screen, Keypad keypad, DepositSlot depositSlot)
    {
        super(userAccountNumber, bankDatabase, screen);
        this.keypad = keypad;
        this.depositSlot = depositSlot;
    }
    
    public void execute()
    {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        double amount = input();
        
        if(amount != CANCELED)
        {
            // request deposit envelope containing specified amount
            screen.displayMessage("\nPlease insert a deposit envelope containing ");
            screen.displayDollarAmount(amount);
            screen.displayMessageLine(" in the deposit slot.");
            
            // receive deposit envelope
            boolean envelopeReceived = depositSlot.isEnvelopeReceived();
            
            if(envelopeReceived)
            {
                screen.displayMessageLine("\nYour envelope has been received." 
                                          + "\nNOTE: The money deposited will not be available until we"
                                          + "\nverify the amount of any enclosed cash, and any enclosed "
                                          + "checks clear.");
                   
                // credit account to reflect the deposit
                bankDatabase.credit(getAccountNumber(), amount);
            }
            else
            {
                screen.displayMessageLine("\nYou did not insert an envelope, so the ATM has canceled your transaction.");
            }
        }
        else
        {
            screen.displayMessageLine("\nCanceling transaction...");
        }
    }
    
    public final double input()
    {
        Screen screen = getScreen();
        
        // display the prompt and receive input
        screen.displayMessage("\nPlease enter a deposit amount in \"CENTS\" (or 0 to cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount
        
        if(input == CANCELED)
        {
            return CANCELED;
        }
        else
        {
            return input/100; // return dollar amount
        }
    }
}
