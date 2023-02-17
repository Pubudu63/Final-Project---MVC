package lk.ijse.vimukthi.service.custom;

import lk.ijse.vimukthi.dto.EmployeeDTO;
import lk.ijse.vimukthi.service.SuperService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeService extends SuperService {
    boolean addEmployee(EmployeeDTO employeeDTO) throws DuplicateException, InUseException, SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadEmployeeIDs() throws SQLException, ClassNotFoundException;
}
