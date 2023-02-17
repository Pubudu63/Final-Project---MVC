package lk.ijse.vimukthi.service.custom.impl;

import lk.ijse.vimukthi.dao.DaoFactory;
import lk.ijse.vimukthi.dao.DaoTypes;
import lk.ijse.vimukthi.dao.custom.CustomerDAO;
import lk.ijse.vimukthi.db.DBconnection;
import lk.ijse.vimukthi.dto.CustomerDTO;
import lk.ijse.vimukthi.service.custom.CustomerService;
import lk.ijse.vimukthi.service.util.Convertor;
import lk.ijse.vimukthi.to.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;
    private Connection connection;
    private Convertor convertor;

    public void initialize() throws SQLException, ClassNotFoundException {
        connection = DBconnection.getInstance().getConnection();
        this.customerDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.CUSTOMER);
        convertor = new Convertor();
    }


    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.add(convertor.toCustomer(customerDTO));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return convertor.fromCustomer(customerDAO.search(id));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(convertor.toCustomer(customerDTO));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public ArrayList<String> loadCustomerIDs() throws SQLException, ClassNotFoundException {
        return customerDAO.loadIDs();
    }
}
