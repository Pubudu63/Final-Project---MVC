package lk.ijse.vimukthi.dao.custom;

import lk.ijse.vimukthi.dao.CrudDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dto.EmployeeDTO;
import lk.ijse.vimukthi.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    boolean add(Employee employee) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

    Employee search(String uId) throws SQLException, ClassNotFoundException;

    boolean update(Employee employee) throws SQLException, ClassNotFoundException;

    boolean delete(String uId) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException;
}
