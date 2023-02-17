package lk.ijse.vimukthi.service.custom;

import lk.ijse.vimukthi.dto.CustomerDTO;
import lk.ijse.vimukthi.service.SuperService;
import lk.ijse.vimukthi.to.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService extends SuperService {
    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadCustomerIDs() throws SQLException, ClassNotFoundException;
}
