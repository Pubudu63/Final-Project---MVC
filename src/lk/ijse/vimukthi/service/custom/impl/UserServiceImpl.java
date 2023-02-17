package lk.ijse.vimukthi.service.custom.impl;

import lk.ijse.vimukthi.dao.DaoFactory;
import lk.ijse.vimukthi.dao.DaoTypes;
import lk.ijse.vimukthi.dao.custom.UserDAO;
import lk.ijse.vimukthi.db.DBconnection;
import lk.ijse.vimukthi.dto.UserDTO;
import lk.ijse.vimukthi.service.custom.UserService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.service.util.Convertor;
import lk.ijse.vimukthi.to.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Connection connection;
    private Convertor convertor;

    public void initialize() throws SQLException, ClassNotFoundException {
        connection =DBconnection.getInstance().getConnection();
        this.userDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.USER);
        convertor = new Convertor();
    }


    @Override
    public boolean addUser(UserDTO userDTO) throws DuplicateException {
        return userDAO.add(convertor.toUser(userDTO));
    }

    @Override
    public boolean deleteUser(String id) throws InUseException, DuplicateException, SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public UserDTO searchUser(String id) throws SQLException, ClassNotFoundException {
        return convertor.fromUser(userDAO.search(id));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(convertor.toUser(userDTO));
    }

    @Override
    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadUserIDs() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean searchAdminEmail(UserDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }
}
