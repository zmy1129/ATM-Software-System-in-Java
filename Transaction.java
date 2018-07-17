
/**
 * class Transaction.
 *
 * @author MuyeZhao
 * @version 5/6/2018
 */
public abstract class Transaction
{
    private int accountNumber;
    private BankDatabase bankDatabase;
    private Screen screen;
    
    public Transaction(int userAccountNumber, BankDatabase bankDatabase, Screen screen)
    {
        accountNumber = userAccountNumber;
        this.bankDatabase = bankDatabase;
        this.screen = screen;
    }
    
    public int getAccountNumber()
    {
        return accountNumber;
    }
    
    public BankDatabase getBankDatabase()
    {
        return bankDatabase;
    }
    
    public Screen getScreen()
    {
        return screen;
    }
    
    public abstract void execute();
}
