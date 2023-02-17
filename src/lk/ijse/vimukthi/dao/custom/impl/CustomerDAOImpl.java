package lk.ijse.vimukthi.dao.custom.impl;

import lk.ijse.vimukthi.dao.custom.CustomerDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dao.util.DBUtil;
import lk.ijse.vimukthi.dto.CustomerDTO;
import lk.ijse.vimukthi.entity.Customer;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    private final Connection connection;

    public CustomerDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean add(Customer customer) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?);",
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getContact(),
                customer.getEmail());
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM customer");

        ArrayList<CustomerDTO> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(new CustomerDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return arrayList;
    }

    @Override
    public Customer search(String uId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM customer WHERE cId = ? ",uId);
        while (resultSet.next()) {
            return new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

        }
        return null;
    }


    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("UPDATE customer SET name=?, email=?, address=?, contact=? WHERE cId = ?;",
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getContact(),
                customer.getId()
        );
    }

    @Override
    public boolean delete(String uId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE cId=?", uId);
    }

    @Override
    public ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  DBUtil.executeQuery("SELECT cId FROM customer");

        ArrayList<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
