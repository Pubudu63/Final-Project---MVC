package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.Customer;
import lk.ijse.vimukthi.to.Employee;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean addNewCustomer(String cId, String name, String email, String address, String contact) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?);";
        return CrudUtil.execute(sql, cId, name, email, address, contact);
    }

    public static boolean deleteCustomer(String cId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE cId=?", cId);
    }

    public static Customer searchCustomer(String cId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE cId = ? ", cId);

        while (resultSet.next()) {
            return new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

        }
        return null;
    }

    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET name=?, email=?, address=?, contact=? WHERE cId = ?;",
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getContact(),
                customer.getId()
        );
    }


    public static ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return list;
    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT cId FROM customer");

        ArrayList<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static ArrayList<String> loadItemCode() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT iCode FROM item");

        ArrayList<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
}
