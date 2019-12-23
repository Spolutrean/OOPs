package code.Bills;

import code.Bank;
import code.User;

public class DebitBill extends Bill {
    public DebitBill(User user, Bank bank) {
        super(user, bank);
    }

    @Override
    public void accruePercentage() {
        moneyAmount *= 1 + bank.dayPercentage;
    }

    @Override
    public void withdraw(double amount) throws BillException {
        if (!user.isVerified() && amount > bank.unverifiedAccountMaxWithdraw) {
            throw new BillException("You have to verified account before withdraw more than " + bank.unverifiedAccountMaxWithdraw);
        }
        if (amount > this.moneyAmount) {
            throw new BillException("You can not withdraw more money, than you have");
        }
        moneyAmount -= amount;
    }

    @Override
    public void transfer(double amount, Bill toBill) throws BillException {
        if (!user.isVerified() && amount > bank.unverifiedAccountMaxTransaction) {
            throw new BillException("You have to verified account before transfer more than " + bank.unverifiedAccountMaxTransaction);
        }
        if (amount > this.moneyAmount) {
            throw new BillException("You can not transfer more money, than you have");
        }

        moneyAmount -= amount;
        toBill.moneyAmount += amount;
    }

    @Override
    public void deposit(double amount) {
        moneyAmount += amount;
    }
}
