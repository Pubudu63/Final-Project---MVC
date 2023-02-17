package lk.ijse.vimukthi.dao.custom.impl;

import lk.ijse.vimukthi.dao.custom.EmployeeDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dao.util.DBUtil;
import lk.ijse.vimukthi.dto.EmployeeDTO;
import lk.ijse.vimukthi.entity.Employee;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Employee employee) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("INSERT INTO employee VALUES(?,?,?,?,?);",
                employee.getEmId(),
                employee.getName(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getContact());
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM employee");

        ArrayList<EmployeeDTO> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new EmployeeDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return list;
    }

    @Override
    public Employee search(String emId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE emId = ? ",emId);

        while (resultSet.next()){
            return new Employee(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

        }
        return null;
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("UPDATE employee SET name=?, email=?, address=?, contact=? WHERE emId = ?;",
                employee.getName(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getContact(),
                employee.getEmId()
        );
    }

    @Override
    public boolean delete(String emId) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("DELETE FROM employee WHERE emId=?",emId);
    }

    @Override
        public ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = DBUtil.executeQuery("SELECT emId FROM employee");

            ArrayList<String> idList = new ArrayList<>();

            while (resultSet.next()) {
                idList.add(resultSet.getString(1));
            }
            return idList;
    }
}
