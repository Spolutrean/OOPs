import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(JUnit4.class)
public class ServiceTest {
    private String commonFilePath = "/home/spolutrean/IdeaProjects/HW5/src/test/java/fileDB/";
    private String itemsFilePath = commonFilePath + "itemsV1.txt";
    private String shopsFilePath = commonFilePath + "shopsV1.txt";
    private String dbFilePath = "/home/spolutrean/IdeaProjects/HW5/src/test/java/sqliteDB/testDatabase.db";
    private String initedDbFilePath = "/home/spolutrean/IdeaProjects/HW5/src/test/java/sqliteDB/initedDatabase.db";

    private FileDao fileDao;
    private SqliteDao sqliteDao;
    private Service fileService, dbService;

    @Before
    public void setUp() throws Exception {
        clearFile(itemsFilePath);
        clearFile(shopsFilePath);
        fileDao = new FileDao(itemsFilePath, shopsFilePath);
        fileService = new Service(fileDao);

        deleteFile(dbFilePath);
        copyFile(initedDbFilePath, dbFilePath);
        sqliteDao = new SqliteDao("jdbc:sqlite:" + dbFilePath);
        dbService = new Service(sqliteDao);
    }

    @Test
    public void checkShopAdding() throws Exception {
        Shop shop = new Shop("Shop name", "Address", "Code");
        fileService.addShop(shop);
        Assert.assertEquals(1, fileDao.getAllShops().size());

        dbService.addShop(shop);
        Assert.assertEquals(1, sqliteDao.getAllShops().size());
    }

    @Test
    public void checkItemAdding() throws Exception {
        Item item = new Item("Name", "ShopCode", 1, 1);
        fileService.addItem(item);
        Assert.assertEquals(1, fileDao.getAllItems().size());

        dbService.addItem(item);
        Assert.assertEquals(1, sqliteDao.getAllItems().size());
    }

    @Test
    public void checkFindCheaperShopByItems() throws Exception {
        Item item1 = new Item("xbox", "#1", 1, 1);
        Item item2 = new Item("xbox", "#2", 1, 4);
        Shop shop1 = new Shop("#1", "adr #1", "#1");
        Shop shop2 = new Shop("#2", "adr #2", "#2");

        fileService.addItems(Arrays.asList(item1, item2));
        fileService.addShop(shop1);
        fileService.addShop(shop2);
        Assert.assertEquals(shop1, fileService.findCheaperShopByItem("xbox"));

        dbService.addItems(Arrays.asList(item1, item2));
        dbService.addShop(shop1);
        dbService.addShop(shop2);
        Assert.assertEquals(shop1, dbService.findCheaperShopByItem("xbox"));
    }

    @Test
    public void checkTryBuyByMoney() throws Exception {
        Item item1 = new Item("xbox", "#1", 10, 4);
        Item item2 = new Item("playstation", "#1", 1, 2);
        Item item3 = new Item("plane", "#1", 1, 100500);
        Shop shop1 = new Shop("#1", "adr #1", "#1");

        Item ansItem1 = new Item("xbox", "#1", 1, 4);
        Item ansItem2 = new Item("playstation", "#1", 1, 2);
        Item ansItem3 = new Item("plane", "#1", 0, 100500);

        fileService.addItems(Arrays.asList(item1, item2, item3));
        fileService.addShop(shop1);
        Assert.assertEquals(Arrays.asList(ansItem1, ansItem2, ansItem3), fileService.tryBuyByMoney("#1", 7));

        dbService.addItems(Arrays.asList(item1, item2, item3));
        dbService.addShop(shop1);
        Assert.assertEquals(Arrays.asList(ansItem1, ansItem2, ansItem3), dbService.tryBuyByMoney("#1", 7));
    }

    @Test
    public void findCheckFindBestShop() throws Exception{
        Item item1 = new Item("xbox", "#1", 10, 1);
        Item item2 = new Item("playstation", "#1", 2, 4);
        Item item3 = new Item("plane", "#1", 1, 1000);
        Shop shop1 = new Shop("#1", "adr #1", "#1");

        Item item21 = new Item("xbox", "#2", 10, 4);
        Item item22 = new Item("playstation", "#2", 1, 2);
        Item item23 = new Item("plane", "#2", 1, 500);
        Shop shop2 = new Shop("#2", "adr #2", "#2");


        List<Pair<String, Integer>> query0 = Arrays.asList(new Pair<String, Integer>("plane", 2));
        List<Pair<String, Integer>> query1 = Arrays.asList(new Pair<String, Integer>("playstation", 2));
        List<Pair<String, Integer>> query3 = Arrays.asList(new Pair<String, Integer>("playstation", 1), new Pair<String, Integer>("plane", 1));

        fileService.addItems(Arrays.asList(item1, item2, item3, item21, item22, item23));
        fileService.addShop(shop1);
        fileService.addShop(shop2);
        Assert.assertEquals(null, fileService.findCheaperShop(query0));
        Assert.assertEquals(shop1, fileService.findCheaperShop(query1));
        Assert.assertEquals(shop2, fileService.findCheaperShop(query3));

        dbService.addItems(Arrays.asList(item1, item2, item3, item21, item22, item23));
        dbService.addShop(shop1);
        dbService.addShop(shop2);
        Assert.assertEquals(null, dbService.findCheaperShop(query0));
        Assert.assertEquals(shop1, dbService.findCheaperShop(query1));
        Assert.assertEquals(shop2, dbService.findCheaperShop(query3));

    }


    private void clearFile(String filePath) throws IOException {
        PrintWriter writer = new PrintWriter(filePath);
        writer.print("");
        writer.close();
    }

    private void copyFile(String sourceFilePath, String destFilePath) throws IOException {
        Path source = Paths.get(sourceFilePath);
        Path dest = Paths.get(destFilePath);

        try (InputStream fis = Files.newInputStream(source);
             OutputStream fos = Files.newOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

    private void deleteFile(String filePath) {
        File f = new File(filePath);
        f.delete();
    }
}