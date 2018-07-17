
/**
 * class Account.
 *
 * @author MuyeZhao
 * @version 5/1/2018
 */
public class Account
{
    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawal
    private double totalBalance; // funds available + funds waiting to clear
    
    //constructor
    public Account(int accountNumber, int pin, double availableBalance, 
                   double totalBalance)
    {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.availableBalance = availableBalance;
        this.totalBalance = totalBalance;
    }
    
    // is user-specified PIN correct?
    final public boolean valiatePIN(int userPIN)
    {
        if (userPIN == pin)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    // return account number
    final public int getAccountNumber()
    {
        return accountNumber;
    }
    
    // returns available balance
    final public double getAvailableBalance()
    {
        return availableBalance;
    }
    
    // returns total balance
    final public double getTotalBalance()
    {
        return totalBalance;
    }
    
    // adds an amount to the Account balance
    public void credit(double amount)
    {
        totalBalance += amount;
    }
    
    // subtracts an amount from the Account balance
    public void debit(double amount)
    {
        availableBalance -= amount; // subtract from available balance
        totalBalance -= amount; // subtract from total balance
    }
}
