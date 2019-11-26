import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDao implements Dao {
    private String itemsFilePath, shopsFilePath;

    public FileDao(String itemsFilePath, String shopsFilePath) {
        this.itemsFilePath = itemsFilePath;
        this.shopsFilePath = shopsFilePath;
    }

    @Override
    public void insertItem(Item item) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(itemsFilePath, true));
        writer.newLine();
        writer.write(item.toString());
        writer.close();
    }

    @Override
    public void insertShop(Shop shop) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(shopsFilePath, true));
        writer.newLine();
        writer.write(shop.toString());
        writer.close();
    }

    @Override
    public List<Item> getAllItems() throws IOException {
        return readAllLines(itemsFilePath).stream()
                .map(Item::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shop> getAllShops() throws IOException {
        return readAllLines(shopsFilePath).stream()
                .map(Shop::new)
                .collect(Collectors.toList());
    }

    private List<String> readAllLines(String filePath) throws IOException {
        List<String> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.length() != 0) {
                    result.add(line);
                }
                line = reader.readLine();
            }
        }
        return result;
    }
}
