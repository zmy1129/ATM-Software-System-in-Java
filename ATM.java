
/**
 * class ATM.
 *
 * @author MuyeZhao
 * @version 5/6/2018
 */
public class ATM
{
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;
    private boolean userAuthenticated;
    private int currentAccountNumber;
    
    public ATM()
    {
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
        userAuthenticated = false;
        currentAccountNumber = 0;
    }
    
    public void run()
    {
        while(true)
        {
            screen.displayMessageLine("\nWelcome!");
            
            while (!userAuthenticated)
            {
                authenticateUser(); // authenticate user
            }
            
            performTransactions(); // user is now authenticated
            screen.displayMessageLine("\nThank you! Goodbye!");
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
        }
    }
    
    private void authenticateUser()
    {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("\nEnter your PIN: ");
        int pin = keypad.getInput();
        
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        
        if(userAuthenticated)
        {
            currentAccountNumber = accountNumber;
        }
        else
        {
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }
    
    private void performTransactions()
    {
        boolean userExited = false;
        
        while(!userExited)
        {
            int mainMenuSelection = displayMainMenu();
            
            switch(mainMenuSelection)
            {
                case 1:
                case 2:
                case 3:
                    createTransaction(mainMenuSelection).execute();
                    break;
                case 4:
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true;
                    break;
                default:
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                    break;
            }
        }
    }
    
    private final int displayMainMenu()
    {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }
    
    private Transaction createTransaction(int Selection)
    {
        Transaction transaction = null;
        
        switch(Selection)
        {
           case 1:
               transaction = new BalanceInquiry(currentAccountNumber, bankDatabase, screen);
               break;
           case 2:
               transaction = new Withdrawal(currentAccountNumber, bankDatabase, screen, keypad, cashDispenser);
               break;
           case 3:
               transaction = new Deposit(currentAccountNumber, bankDatabase, screen, keypad, depositSlot);
               break;
        }
        
        return transaction;
    }
}
