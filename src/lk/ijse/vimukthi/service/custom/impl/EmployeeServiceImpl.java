package lk.ijse.vimukthi.service.custom.impl;

import lk.ijse.vimukthi.dao.DaoFactory;
import lk.ijse.vimukthi.dao.DaoTypes;
import lk.ijse.vimukthi.dao.custom.EmployeeDAO;
import lk.ijse.vimukthi.db.DBconnection;
import lk.ijse.vimukthi.dto.EmployeeDTO;
import lk.ijse.vimukthi.service.custom.EmployeeService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeServiceImpl implements EmployeeService {
    private Connection connection;
    private EmployeeDAO employeeDAO;
    private Convertor convertor;

    public void initialize() throws SQLException, ClassNotFoundException {
        connection = DBconnection.getInstance().getConnection();
        this.employeeDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYEE);
        convertor = new Convertor();
    }


    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) throws DuplicateException, InUseException, SQLException, ClassNotFoundException {
        return employeeDAO.add(convertor.toEmployee(employeeDTO));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return convertor.fromEmployee(employeeDAO.search(id));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(convertor.toEmployee(employeeDTO));
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public ArrayList<String> loadEmployeeIDs() throws SQLException, ClassNotFoundException {
        return employeeDAO.loadIDs();
    }
}
