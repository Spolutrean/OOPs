import java.util.List;

public interface Dao {
    public void insertItem(Item item) throws Exception;
    public void insertShop(Shop shop) throws Exception;
    public List<Item> getAllItems() throws Exception;
    public List<Shop> getAllShops() throws Exception;
}
