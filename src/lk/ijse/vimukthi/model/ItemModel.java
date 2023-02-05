package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.Item;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public static boolean addNewItem(String itemId, String itemName, String itemQty, String itemPrice) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES(?,?,?,?);";
        return CrudUtil.execute(sql,itemId,itemName,itemQty,itemPrice);
    }

    public static boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE iCode=?",itemId);
    }

    public static Item searchItem(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE iCode = ? ",itemId);

        while (resultSet.next()){
            return new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));

        }
        return null;
    }

    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET description=?, qty=?, price=? WHERE iCode = ?;",
                item.getItemName(),
                item.getItemQty(),
                item.getItemPrice(),
                item.getItemId()
        );

    }

    public static ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");

        ArrayList<Item> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        return list;
    }
}
