package code.Bills;

import code.Bank;
import code.User;

public class CreditBill extends Bill {
    public CreditBill(User user,
                      Bank bank) {
        super(user, bank);
    }

    @Override
    public void accruePercentage() {
    }

    @Override
    public void withdraw(double amount) throws BillException {
        if (!user.isVerified() && amount > bank.unverifiedAccountMaxWithdraw) {
            throw new BillException("You have to verified account before withdraw more than " + bank.unverifiedAccountMaxWithdraw);
        }
        if (moneyAmount - amount < -bank.minusLimit) {
            throw new BillException("You can not withdraw over you credit.");
        }

        moneyAmount -= amount;
    }

    @Override
    public void transfer(double amount, Bill toBill) throws BillException {
        if (!user.isVerified() && amount > bank.unverifiedAccountMaxTransaction) {
            throw new BillException("You have to verified account before transfer more than " + bank.unverifiedAccountMaxTransaction);
        }
        if (moneyAmount - amount < -bank.minusLimit) {
            throw new BillException("You can not transfer over you credit.");
        }

        moneyAmount -= amount + bank.minusCommission;
        toBill.moneyAmount += amount;
    }

    @Override
    public void deposit(double amount) {
        moneyAmount += amount;
    }
}
