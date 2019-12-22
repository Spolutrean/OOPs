package code;

public class UserBuilder {
    private User user;

    UserBuilder(String firstName, String lastName) {
        user = new User(firstName, lastName);
    }

    UserBuilder setAddress(String address) {
        user.setAddress(address);
        return this;
    }

    UserBuilder setPassport(String passport) {
        user.setPassport(passport);
        return this;
    }

    User build() {
        return user;
    }
}
