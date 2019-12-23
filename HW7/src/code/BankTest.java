package code;

import code.Bills.BillException;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class BankTest {
    private int userCount;

    @Before
    public void setUp() {
        userCount = 0;
    }

    @Test
    public void checkDepositBillDeposit() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerDepositBill(user, 10, 10d);
        bank.makeDeposit(user.bills.get(0), 100d);
        Assert.assertEquals(110d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }

    @Test(expected = BillException.class)
    public void checkDepositBillWithdraw() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerDepositBill(user, 10, 10d);
        bank.makeWithdraw(user.bills.get(0), 1d);
    }

    @Test(expected = BillException.class)
    public void checkDepositBillTransfer() throws BillException {
        Bank bank = createBank();
        User user1 = createVerifiedUser();
        User user2 = createVerifiedUser();

        bank.registerDepositBill(user1, 10, 10d);
        bank.registerDepositBill(user2, 10, 10d);
        bank.makeTransfer(user1.bills.get(0), user2.bills.get(0), 1d);
    }

    @Test
    public void checkDepositBillAccruePercentage() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerDepositBill(user, 10, 100d);
        bank.accruePercentage();
        Assert.assertEquals(105d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }


    @Test
    public void checkDebitBillDeposit() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerDebitBill(user);
        bank.makeDeposit(user.bills.get(0), 100d);
        Assert.assertEquals(100d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }

    @Test(expected = BillException.class)
    public void checkDebitBillWithdraw() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerDebitBill(user);
        bank.makeWithdraw(user.bills.get(0), 100d);
    }

    @Test(expected = BillException.class)
    public void checkDebitBillTransfer() throws BillException {
        Bank bank = createBank();
        User user1 = createVerifiedUser();
        User user2 = createVerifiedUser();

        bank.registerDebitBill(user1);
        bank.registerDebitBill(user2);
        bank.makeTransfer(user1.bills.get(0), user2.bills.get(0), 1d);
    }

    @Test
    public void checkDebitBillAccruePercentage() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerDebitBill(user);
        bank.makeDeposit(user.bills.get(0), 100d);
        bank.accruePercentage();
        Assert.assertEquals(110d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }


    @Test
    public void checkCreditBillDeposit() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerCreditBill(user);
        bank.makeDeposit(user.bills.get(0), 100d);
        Assert.assertEquals(100d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }

    @Test
    public void checkCreditBillWithdraw() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerCreditBill(user);
        bank.makeWithdraw(user.bills.get(0), 100d);
    }

    @Test
    public void checkCreditBillTransfer() throws BillException {
        Bank bank = createBank();
        User user1 = createVerifiedUser();
        User user2 = createVerifiedUser();

        bank.registerCreditBill(user1);
        bank.registerCreditBill(user2);
        bank.makeTransfer(user1.bills.get(0), user2.bills.get(0), 100d);
    }

    @Test
    public void checkCreditBillAccruePercentage() throws BillException {
        Bank bank = createBank();
        User user = createVerifiedUser();
        bank.registerCreditBill(user);
        bank.makeDeposit(user.bills.get(0), 100d);
        bank.accruePercentage();
        Assert.assertEquals(100d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }


    @Test(expected = BillException.class)
    public void checkUnverifiedUser() throws BillException {
        Bank bank = createBank();
        User user = createUnverifiedUser();
        bank.registerDebitBill(user);
        bank.makeDeposit(user.bills.get(0), 50d);
        bank.makeWithdraw(user.bills.get(0), 50d);
    }

    @Test
    public void checkTransactionUndo() throws BillException {
        User user = createVerifiedUser();
        Bank bank = createBank();
        bank.registerDebitBill(user);
        bank.makeDeposit(user.bills.get(0), 10d);
        bank.getTransactions().get(0).undo();
        Assert.assertEquals(0d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }

    @Test
    public void checkIrrevocableTransactionUndo() throws BillException {
        User user = createVerifiedUser();
        Bank bank = createBank();
        bank.registerDebitBill(user);
        bank.makeDeposit(user.bills.get(0), 100d);
        bank.getTransactions().get(0).undo();
        Assert.assertEquals(0d, user.bills.get(0).getMoneyAmount(), 1e-5);
    }


    private User createVerifiedUser() {
        return new UserBuilder("Good", "Boy #" + userCount++)
                .setAddress("address")
                .setPassport("passport")
                .build();
    }

    private User createUnverifiedUser() {
        return new UserBuilder("Bad", "Guy #" + userCount++)
                .build();
    }

    private Bank createBank() {
        List<Pair<Integer, Double>> depositCondition = new ArrayList<>();
        depositCondition.add(new Pair<>(150, 0.05));
        depositCondition.add(new Pair<>(1000, 0.055));
        depositCondition.add(new Pair<>(10000, 0.06));
        return new Bank(
                "Alpha",
                10d,
                10d,
                0.1,
                200d,
                10d,
                depositCondition);
    }
}