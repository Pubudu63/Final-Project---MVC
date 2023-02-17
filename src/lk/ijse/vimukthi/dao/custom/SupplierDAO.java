package lk.ijse.vimukthi.dao.custom;

import lk.ijse.vimukthi.dao.CrudDAO;
import lk.ijse.vimukthi.dao.exception.ConstraintViolationException;
import lk.ijse.vimukthi.dto.SupplierDTO;
import lk.ijse.vimukthi.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier,String> {

    boolean add(Supplier supplier) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException;

    Supplier search(String sId) throws SQLException, ClassNotFoundException;

    boolean update(Supplier supplier) throws SQLException, ClassNotFoundException;

    boolean delete(String sId) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadIDs() throws SQLException, ClassNotFoundException;
}
