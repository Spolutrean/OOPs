package code;

import code.Bills.Bill;
import code.Bills.BillException;

public class Transaction {
    private Bill fromBill = null, toBill = null;
    private Double moneyAmount;
    private Boolean isUndone = false, isDone = false;

    public Transaction(Bill fromBill, Bill toBill, Double moneyAmount) {
        this.fromBill = fromBill;
        this.toBill = toBill;
        this.moneyAmount = moneyAmount;
    }

    public void execute() throws BillException {
        if (fromBill != null && toBill != null) {
            fromBill.transfer(moneyAmount, toBill);
        } else if (fromBill == null) {
            toBill.deposit(moneyAmount);
        } else if (toBill == null) {
            fromBill.withdraw(moneyAmount);
        }

        if (fromBill != null) {
            fromBill.addTransaction(this);
        }

        if (toBill != null) {
            toBill.addTransaction(this);
        }
        isDone = true;
    }

    public void undo() throws BillException {
        try {
            if (fromBill != null && toBill != null) {
                toBill.transfer(moneyAmount, fromBill);
            } else if (fromBill == null) {
                toBill.withdraw(moneyAmount);
            } else if (toBill == null) {
                fromBill.deposit(moneyAmount);
            }
        } catch (BillException e) {
            throw new BillException("Can not undo this transaction, you should visit your bank");
        }
        isUndone = true;
    }
}
