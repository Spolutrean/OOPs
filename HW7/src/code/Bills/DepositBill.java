package code.Bills;

import code.Bank;
import code.User;

import java.time.LocalDate;

public class DepositBill extends Bill {
    private LocalDate endDate;
    private double percentage;

    public DepositBill(User user, Bank bank, LocalDate endDate, Double moneyAmount, double percentage) {
        super(user, bank);
        this.endDate = endDate;
        this.moneyAmount = moneyAmount;
        this.percentage = percentage;
    }

    @Override
    public void accruePercentage() {
        moneyAmount *= 1 + percentage;
    }

    @Override
    public void withdraw(double amount) throws BillException {
        if (!user.isVerified() && amount > bank.unverifiedAccountMaxWithdraw) {
            throw new BillException("You have to verified account before withdraw more than " + bank.unverifiedAccountMaxWithdraw);
        }
        if (LocalDate.now().isBefore(endDate)) {
            throw new BillException("You can not withdraw money before " + endDate);
        }

        moneyAmount -= amount;
    }

    @Override
    public void transfer(double amount, Bill toBill) throws BillException {
        if (!user.isVerified() && amount > bank.unverifiedAccountMaxTransaction) {
            throw new BillException("You have to verified account before transfer more than " + bank.unverifiedAccountMaxTransaction);
        }
        if (LocalDate.now().isBefore(endDate)) {
            throw new BillException("You can not transfer money before " + endDate);
        }

        moneyAmount -= amount;
        toBill.moneyAmount += amount;
    }

    @Override
    public void deposit(double amount) {
        moneyAmount += amount;
    }
}
