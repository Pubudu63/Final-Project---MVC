package lk.ijse.vimukthi.service.custom;

import lk.ijse.vimukthi.dto.ItemDTO;
import lk.ijse.vimukthi.service.SuperService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.to.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService extends SuperService {
    boolean addItem(ItemDTO itemDTO) throws DuplicateException, InUseException, SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    Item searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException;
}
