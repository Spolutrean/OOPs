import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;

public class SqliteDao implements Dao {
    private Statement statement;

    public SqliteDao(String dbFilePath) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        statement = DriverManager.getConnection(dbFilePath).createStatement();

    }

    @Override
    public void insertItem(Item item) throws SQLException {
        String sql = "INSERT INTO main.items (name, shopCode, count, cost) VALUES ('" +
                item.name + "', '" +
                item.shopCode + "', " +
                item.count.toString() + ", " +
                item.cost.toString() + ");";
        statement.execute(sql);
    }

    @Override
    public void insertShop(Shop shop) throws SQLException {
        String sql = "INSERT INTO main.shops (name, address, code) VALUES ('" +
                shop.name + "', '" +
                shop.address + "', '" +
                shop.code + "');";
        statement.execute(sql);
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        List<Item> result = new ArrayList<>();
        String sql = "SELECT * FROM main.items";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            result.add(new Item(
                    rs.getString("name"),
                    rs.getString("shopCode"),
                    rs.getInt("count"),
                    rs.getInt("cost")
            ));
        }

        return result;
    }

    @Override
    public List<Shop> getAllShops() throws SQLException {
        List<Shop> result = new ArrayList<>();
        String sql = "SELECT * FROM main.shops";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            result.add(new Shop(
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("code")
            ));
        }

        return result;
    }
}
