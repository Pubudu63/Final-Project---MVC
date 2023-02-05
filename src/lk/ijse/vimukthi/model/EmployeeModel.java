package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.Customer;
import lk.ijse.vimukthi.to.Employee;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static boolean addNewEmployee(String emId, String name, String email, String address, String contact) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?);";
        return CrudUtil.execute(sql,emId,name,email,address,contact);
    }

    public static boolean deleteEmployee(String emId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM employee WHERE emId=?",emId);
    }

    public static Employee searchEmployee(String emId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE emId = ? ",emId);

        while (resultSet.next()){
            return new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));

        }
        return null;
    }

    public static boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE employee SET name=?, email=?, address=?, contact=? WHERE emId = ?;",
                employee.getName(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getContact(),
                employee.getEmId()
        );

    }

    public static ArrayList<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return list;
    }


}
