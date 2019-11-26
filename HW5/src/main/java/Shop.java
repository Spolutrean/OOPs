import java.util.Objects;

public class Shop {
    public String name, address, code;

    public Shop(String name, String address, String code) {
        this.name = name;
        this.address = address;
        this.code = code;
    }

    public Shop(String stringRepresentation) {
        String[] parts = stringRepresentation.split(",");
        code = parts[0];
        name = parts[1];
        address = parts[2];
    }

    @Override
    public String toString() {
        return code + ',' + name + ',' + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) &&
                Objects.equals(address, shop.address) &&
                Objects.equals(code, shop.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, code);
    }
}
