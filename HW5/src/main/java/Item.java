import java.util.Objects;

public class Item {
    public String name, shopCode;
    public Integer count, cost;

    public Item(String name, String shopCode, Integer count, Integer cost) {
        this.name = name;
        this.shopCode = shopCode;
        this.count = count;
        this.cost = cost;
    }

    public Item(String stringRepresentation) {
        String[] parts = stringRepresentation.split(",");
        name = parts[0];
        shopCode = parts[1];
        count = Integer.valueOf(parts[2]);
        cost = Integer.valueOf(parts[3]);
    }

    @Override
    public String toString() {
        return name + ',' + shopCode + ',' + count + ',' + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(shopCode, item.shopCode) &&
                Objects.equals(count, item.count) &&
                Objects.equals(cost, item.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shopCode, count, cost);
    }
}
