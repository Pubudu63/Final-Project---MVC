package lk.ijse.vimukthi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static DBconnection dBConnection;
    private Connection connection;


    private DBconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/vimukthi?useSSL=false", "root", "1234");

    }

    public static DBconnection getInstance() throws ClassNotFoundException, SQLException {

        return (null==dBConnection) ? dBConnection=new DBconnection():dBConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}

