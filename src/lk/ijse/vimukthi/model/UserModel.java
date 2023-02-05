package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.Employee;
import lk.ijse.vimukthi.to.User;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public static boolean addNewUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES (?,?,?,?,?,?,?);";
        return CrudUtil.execute(sql,user.getId(),user.getName(),user.getEmail(),user.getAddress(),user.getPosition(),user.getContact(),user.getPassword());
    }

    public static User searchUser(String uId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE uId = ? ",uId);

        while (resultSet.next()){
            return new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));

        }
        return null;
    }

    public static boolean deleteUser(String uId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM user WHERE uId=?",uId);
    }

    public static boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE employee SET name=?, email=?, address=?, contact=?, password=? WHERE emId = ?;",
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getContact(),
                user.getPassword(),
                user.getId()
        );

    }

    public static ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM user");

        ArrayList<User> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(6)));
        }
        return list;
    }
}
