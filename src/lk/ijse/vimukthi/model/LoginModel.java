package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.User;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    public static ResultSet resultSet;

    public static boolean searchEmail(User user) throws SQLException, ClassNotFoundException {
        resultSet = CrudUtil.execute("SELECT * FROM user WHERE email = ?", user.getEmail());
        while (resultSet.next()) {
            if (resultSet.getString(3).equals(user.getEmail()) &&
                    resultSet.getString(7).equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
