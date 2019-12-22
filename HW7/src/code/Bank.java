package code;

import code.Bills.*;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {
    private String name;
    private List<Bill> bills;
    private List<Transaction> transactions;
    private List<Pair<Integer, Double>> depositCondition;
    public Double unverifiedAccountMaxTransaction,
            unverifiedAccountMaxWithdraw,
            dayPercentage,
            minusLimit,
            minusCommission;

    public Bank(String name,
                Double unverifiedAccountMaxTransaction,
                Double unverifiedAccountMaxWithdraw,
                Double dayPercentage,
                Double minusLimit,
                Double minusCommission,
                List<Pair<Integer, Double>> depositCondition) {
        bills = new ArrayList<>();
        transactions = new ArrayList<>();
        this.name = name;
        this.unverifiedAccountMaxTransaction = unverifiedAccountMaxTransaction;
        this.unverifiedAccountMaxWithdraw = unverifiedAccountMaxWithdraw;
        this.dayPercentage = dayPercentage;
        this.minusLimit = minusLimit;
        this.minusCommission = minusCommission;
        this.depositCondition = depositCondition;
    }

    public void registerDebitBill(User user) {
        DebitBill debitBill = new DebitBill(user, this);
        user.addBill(debitBill);
        bills.add(debitBill);
    }

    public void registerCreditBill(User user) {
        CreditBill creditBill = new CreditBill(user, this);
        user.addBill(creditBill);
        bills.add(creditBill);
    }

    public void registerDepositBill(User user, int daysCount, Double moneyAmount) throws BillException {
        Double percentage = null;
        for (int i = 0; i < depositCondition.size(); ++i) {
            if (depositCondition.get(i).getKey() >= moneyAmount) {
                percentage = depositCondition.get(i).getValue();
                break;
            }
        }
        if (percentage == null) {
            throw new BillException("Wrong deposit condition in bank");
        }

        DepositBill depositBill = new DepositBill(user, this, LocalDate.now().plusDays(daysCount), moneyAmount, percentage);
        user.addBill(depositBill);
        bills.add(depositBill);
    }

    public void makeTransfer(Bill fromBill, Bill toBill, Double moneyAmount) throws BillException {
        if (moneyAmount < 0) {
            throw new BillException("You can not transfer negative money amount");
        }
        User fromUser = fromBill != null ? fromBill.getUser() : null;
        User toUser = toBill != null ? toBill.getUser() : null;
        if (fromUser != null && fromUser.isBlocked || toUser != null && toUser.isBlocked) {
            String blockedUser = (fromUser != null && fromUser.isBlocked)
                    ? fromBill.getUser().toString()
                    : toBill.getUser().toString();
            throw new BillException("Can not handle transaction, user {" + blockedUser + "} is blocked.");
        }

        Transaction t = new Transaction(fromBill, toBill, moneyAmount);
        if (fromBill != null) {
            fromBill.getBank().transactions.add(t);
        }
        if (toBill != null) {
            toBill.getBank().transactions.add(t);
        }
        t.execute();
    }

    public void makeDeposit(Bill depositBill, Double moneyAmount) throws BillException {
        if (moneyAmount < 0) {
            throw new BillException("You should use withdraw for increase your balance");
        }
        makeTransfer(null, depositBill, moneyAmount);
    }

    public void makeWithdraw(Bill withdrawBill, Double moneyAmount) throws BillException {
        if (moneyAmount < 0) {
            throw new BillException("You should use deposit for decrease your balance");
        }
        makeTransfer(withdrawBill, null, moneyAmount);
    }

    public void accruePercentage() {
        for (Bill bill : bills) {
            bill.accruePercentage();
        }
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
