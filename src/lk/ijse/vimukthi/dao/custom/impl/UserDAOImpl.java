package lk.ijse.vimukthi.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.vimukthi.dao.custom.UserDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dao.util.DBUtil;
import lk.ijse.vimukthi.dto.UserDTO;
import lk.ijse.vimukthi.entity.User;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    private final Connection connection;
    public static ResultSet resultSet;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean add(User user) throws ConstraintViolationException {
        try {
            return DBUtil.executeUpdate("INSERT INTO user VALUES (?,?,?,?,?,?,?);",
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getPosition(),
                    user.getContact(),
                    user.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
        }
        return false;
    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM user");

        ArrayList<UserDTO> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new UserDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(6)));
        }
        return list;
    }

    @Override
    public lk.ijse.vimukthi.entity.User search(String uId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM user WHERE uId = ? ",uId);

        while (resultSet.next()){
            return new lk.ijse.vimukthi.entity.User(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7));

        }
        return null;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("UPDATE employee SET name=?, email=?, address=?, contact=?, password=? WHERE emId = ?;",
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getContact(),
                user.getPassword(),
                user.getId()
        );
    }

    @Override
    public boolean delete(String uId) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("DELETE FROM user WHERE uId=?",uId);
    }

    @Override
    public ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean searchEmail(User user) throws SQLException, ClassNotFoundException {
        return false;
    }
}
