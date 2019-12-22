package code;

import code.Bills.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String firstName, lastName, address, passport;
    List<Bill> bills;
    boolean isBlocked = false;
    boolean isVerified = false;

    public User(String firstName, String lastName) {
        id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        bills = new ArrayList<>();
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void block() {
        isBlocked = true;
    }

    public void unblock() {
        isBlocked = false;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean isVerified() {
        return address != null && passport != null;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " firstName=" + firstName +
                " lastName=" + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
