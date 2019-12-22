package code.Bills;

import code.Bank;
import code.Transaction;
import code.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Bill {
    protected UUID id;
    protected double moneyAmount;
    protected User user;
    protected Bank bank;
    protected List<Transaction> transactions;

    public Bill(User user, Bank bank) {
        this.user = user;
        this.bank = bank;
        this.id = UUID.randomUUID();
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getId() {
        return id.toString();
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public User getUser() {
        return user;
    }

    public Bank getBank() {
        return bank;
    }

    public abstract void accruePercentage();

    public abstract void withdraw(double amount) throws BillException;

    public abstract void transfer(double amount, Bill toBill) throws BillException;

    public abstract void deposit(double amount);
}
