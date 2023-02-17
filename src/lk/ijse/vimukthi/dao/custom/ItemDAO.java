package lk.ijse.vimukthi.dao.custom;

import lk.ijse.vimukthi.dao.CrudDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dto.ItemDTO;
import lk.ijse.vimukthi.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
    boolean add(Item item) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException;

    Item search(String code) throws SQLException, ClassNotFoundException;

    boolean update(Item item) throws SQLException, ClassNotFoundException;

    boolean delete(String code) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadCodes() throws SQLException, ClassNotFoundException;
}
