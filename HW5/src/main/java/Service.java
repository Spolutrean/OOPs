import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {
    private Dao dao;

    public Service(Dao dao) {
        this.dao = dao;
    }

    public void addShop(Shop shop) throws Exception {
        dao.insertShop(shop);
    }

    public void addItem(Item item)throws Exception  {
        dao.insertItem(item);
    }

    public void addItems(List<Item> items) throws Exception {
        for (Item item:items) {
            addItem(item);
        }
    }

    public Shop findCheaperShopByItem(String itemName) throws Exception {
        List<Shop> allShops = dao.getAllShops();
        List<Item> allItems = dao.getAllItems();
        Optional<Item> foundItem = allItems.stream()
                .filter(item -> item.name.equals(itemName))
                .min((item1, item2) -> item1.cost.compareTo(item2.cost));
        if(foundItem.isEmpty()) {
            return null;
        }
        Optional<Shop> foundShop = allShops.stream().
                filter(shop -> shop.code.equals(foundItem.get().shopCode))
                .findFirst();
        if(foundShop.isEmpty()) {
            return null;
        }
        return foundShop.get();
    }

    public List<Item> tryBuyByMoney(String shopCode, Integer moneyAmount) throws Exception {
        List<Item> shopItems = dao.getAllItems().stream()
                .filter(item -> item.shopCode.equals(shopCode))
                .collect(Collectors.toList());
        for(Item item:shopItems) {
            item.count = Math.min(item.count, Math.floorDiv(moneyAmount, item.cost));
        }

        return shopItems;
    }

    public Integer buyItems(String shopCode, List<Pair<String, Integer>> items) throws Exception {
        List<Item> shopItems = dao.getAllItems().stream()
                .filter(item -> item.shopCode.equals(shopCode))
                .collect(Collectors.toList());
        int summaryMoneyAmount = 0;
        for (Pair<String, Integer> item : items) {
            Optional<Item> shopItem = shopItems.stream()
                    .filter(i -> i.name.equals(item.getKey()))
                    .findFirst();

            if(shopItem.isEmpty() || shopItem.get().count < item.getValue()) {
                return -1;
            }

            summaryMoneyAmount += item.getValue() * shopItem.get().cost;
        }

        return summaryMoneyAmount;
    }

    public Shop findCheaperShop(List<Pair<String, Integer>> items) throws Exception{
        List<Shop> allShops = dao.getAllShops();
        Shop bestShop = null;
        int minSum = 0;
        for(Shop shop : allShops) {
            int tryBuyItemsSum = buyItems(shop.code, items);
            if(tryBuyItemsSum == -1) {
                continue;
            }
            if(bestShop == null || minSum > tryBuyItemsSum) {
                minSum = tryBuyItemsSum;
                bestShop = shop;
            }
        }

        return bestShop;
    }
}
