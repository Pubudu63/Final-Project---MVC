package lk.ijse.vimukthi.dao.custom;

import lk.ijse.vimukthi.dao.CrudDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dto.UserDTO;
import lk.ijse.vimukthi.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User,String> {
    boolean add(User user) throws ConstraintViolationException;

    ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException;

    lk.ijse.vimukthi.entity.User search(String uId) throws SQLException, ClassNotFoundException;

    boolean update(User user) throws SQLException, ClassNotFoundException;

    boolean delete(String uId) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException;

    boolean searchEmail(User user) throws SQLException, ClassNotFoundException;
}
