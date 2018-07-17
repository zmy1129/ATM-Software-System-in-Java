
/**
 * class Withdrawal.
 *
 * @author MuyeZhao
 * @version 5/6/2018
 */
public class Withdrawal extends Transaction
{
    static final int CANCELED = 6;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    
    public Withdrawal(int userAccountNumber, BankDatabase bankDatabase, Screen screen, Keypad keypad, CashDispenser cashDispenser)
    {
        super(userAccountNumber, bankDatabase, screen);
        this.keypad = keypad;
        this.cashDispenser = cashDispenser;
    }
    
    public void execute()
    {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        boolean cashDispensed = false; // cash was not dispensed yet
        boolean transactionCanceled = false; // transaction was not canceled yet
        
        do 
        {
            // obtain the chosen withdrawal amount from the user
            int amount = input();
            
            if(amount != CANCELED)
            {
                double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                
                if(amount <= availableBalance)
                {
                    if(cashDispenser.isSufficientCashAvailable(amount))
                    {
                        bankDatabase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true;
                        screen.displayMessageLine("\nPlease take your cash from the cash dispenser.");
                    }
                    else
                    {
                        screen.displayMessageLine("\nInsufficient cash available in the ATM."
                                                         + "\n\nPlease choose a smaller amount.");
                    }
                }
                else
                {
                    screen.displayMessageLine("\nInsufficient funds in your account."
                                                     + "\n\nPlease choose a smaller amount.");
                }
            }
            else
            {
                screen.displayMessageLine("\nCanceling transaction...");
                transactionCanceled = true; // user canceled the transaction
            }
        } while (!cashDispensed && !transactionCanceled);
    }
    
    public final int input()
    {
        Screen screen = getScreen();
        
        // array of amounts to correspond to menu numbers
        int amounts[] = {0, 20, 40, 60, 100, 200};
        
        int amount = 0;
        
        while(amount == 0)
        {
            // display the menu
            screen.displayMessageLine("\nWithdrawal options:");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Cancel transaction");
            screen.displayMessage("\nChoose a withdrawal option (1-6): ");
            
            // get user input through keypad
            int input = keypad.getInput();
            
            // determine how to proceed based on the input value
            switch(input)
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    amount = amounts[input];
                    break;
                case 6:
                    amount = CANCELED;
                    break;
                default:
                    screen.displayMessageLine("\nIvalid selection. Try again.");
            }
        }
        
        return amount;
    }
}
