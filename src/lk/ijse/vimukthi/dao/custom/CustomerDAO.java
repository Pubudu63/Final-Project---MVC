package lk.ijse.vimukthi.dao.custom;

import lk.ijse.vimukthi.dao.CrudDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dto.CustomerDTO;
import lk.ijse.vimukthi.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    boolean add(Customer customer) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    Customer search(String uId) throws SQLException, ClassNotFoundException;

    boolean update(Customer customer) throws SQLException, ClassNotFoundException;

    boolean delete(String uId) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException;
}
