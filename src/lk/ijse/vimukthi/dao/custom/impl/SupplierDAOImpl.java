package lk.ijse.vimukthi.dao.custom.impl;

import lk.ijse.vimukthi.dao.custom.SupplierDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dao.util.DBUtil;
import lk.ijse.vimukthi.dto.SupplierDTO;
import lk.ijse.vimukthi.to.Supplier;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    private final Connection connection;

    public SupplierDAOImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public boolean add(lk.ijse.vimukthi.entity.Supplier supplier) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("INSERT INTO supplier VALUES(?,?,?,?,?);",
                supplier.getId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getContact());
    }

    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM supplier");

        ArrayList<SupplierDTO> list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new SupplierDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return list;
    }

    @Override
    public lk.ijse.vimukthi.entity.Supplier search(String sId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM supplier WHERE sId = ? ", sId);

        while (resultSet.next()) {
            return new lk.ijse.vimukthi.entity.Supplier(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

        }
        return null;
    }

    @Override
    public boolean update(lk.ijse.vimukthi.entity.Supplier supplier) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("UPDATE supplier SET name=?, email=?, address=?, contact=? WHERE sId = ?;",
                supplier.getName(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getContact(),
                supplier.getId()
        );
    }

    @Override
    public boolean delete(String sId) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("DELETE FROM supplier WHERE sId=?", sId);
    }

    @Override
    public ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException {
        return null;
    }
}
