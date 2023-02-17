package lk.ijse.vimukthi.service.custom.impl;

import lk.ijse.vimukthi.dao.DaoFactory;
import lk.ijse.vimukthi.dao.DaoTypes;
import lk.ijse.vimukthi.dao.custom.ItemDAO;
import lk.ijse.vimukthi.db.DBconnection;
import lk.ijse.vimukthi.dto.ItemDTO;
import lk.ijse.vimukthi.service.custom.ItemService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.service.util.Convertor;
import lk.ijse.vimukthi.to.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemServiceImpl implements ItemService {
    private Connection connection;
    private ItemDAO itemDAO;
    private Convertor convertor;

    public void initialize() throws SQLException, ClassNotFoundException {
        connection =DBconnection.getInstance().getConnection();
        this.itemDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.ITEM);
        convertor = new Convertor();
    }


    @Override
    public boolean addItem(ItemDTO itemDTO) throws DuplicateException, InUseException, SQLException, ClassNotFoundException {
        return itemDAO.add(convertor.toItem(itemDTO));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return convertor.fromItem(itemDAO.search(code));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(convertor.toItem(itemDTO));
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        return null;
    }
}
