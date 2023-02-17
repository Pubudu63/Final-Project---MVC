package lk.ijse.vimukthi.dao.custom.impl;

import lk.ijse.vimukthi.dao.custom.ItemDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dao.util.DBUtil;
import lk.ijse.vimukthi.dto.ItemDTO;
import lk.ijse.vimukthi.entity.Item;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    private final Connection connection;

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Item item) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return  DBUtil.executeUpdate( "INSERT INTO item VALUES(?,?,?,?);",
                item.getItemId(),
                item.getItemName(),
                item.getItemPrice(),
                item.getItemQty());

    }

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM item");

        ArrayList<ItemDTO> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new ItemDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return list;
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM item WHERE iCode = ? ",code);

        while (resultSet.next()){
            return new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));

        }
        return null;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("UPDATE item SET description=?, qty=?, price=? WHERE iCode = ?;",
                item.getItemName(),
                item.getItemQty(),
                item.getItemPrice(),
                item.getItemId()
        );
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("DELETE FROM item WHERE iCode=?",code);
    }

    @Override
    public ArrayList<String> loadCodes() throws SQLException, ClassNotFoundException {
        return null;
    }
}
