
/**
 * class BankDatabase. Represents the bank's database.
 *
 * @author MuyeZhao
 * @version 5/1/2018
 */
import java.util.ArrayList;

public class BankDatabase
{
    private ArrayList<Account> accounts;
    
    public BankDatabase()
    {
        // create two Account objects for testing
        Account account1 = new Account(12345, 54321, 1000.0, 1200.0);
        Account account2 = new Account(98765, 56789, 200.0, 200.0);
        
        // add the Account objects to the accounts list
        accounts = new ArrayList();
        accounts.add(account1);
        accounts.add(account2);
    }
    
    private Account getAccount(int accountNumber)
    {
        for(int i = 0; i < accounts.size(); i++)
        {
            if (accounts.get(i).getAccountNumber()==accountNumber)
            {
                return accounts.get(i);
            }
        }
        
        return null;
    }
    
    public boolean authenticateUser(int userAccountNumber, int userPIN)
    {
        Account userAccount = getAccount(userAccountNumber);
        if(userAccount != null)
        {
            return userAccount.valiatePIN(userPIN);
        }
        else
        {
            return false;
        }
    }
    
    public double getAvailableBalance(int userAccountNumber)
    {
        return getAccount(userAccountNumber).getAvailableBalance();
    }
    
    public double getTotalBalance(int userAccountNumber)
    {
        return getAccount(userAccountNumber).getTotalBalance();
    }
    
    public void credit(int userAccountNumber, double amount)
    {
        getAccount(userAccountNumber).credit(amount);
    }
    
    public void debit(int userAccountNumber, double amount)
    {
        getAccount(userAccountNumber).debit(amount);
    }
}
