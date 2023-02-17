package lk.ijse.vimukthi.service.custom.impl;

import lk.ijse.vimukthi.dao.DaoFactory;
import lk.ijse.vimukthi.dao.DaoTypes;
import lk.ijse.vimukthi.dao.custom.SupplierDAO;
import lk.ijse.vimukthi.db.DBconnection;
import lk.ijse.vimukthi.dto.SupplierDTO;
import lk.ijse.vimukthi.service.custom.SupplierService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.service.util.Convertor;
import lk.ijse.vimukthi.to.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierServiceImpl implements SupplierService {
    private Connection connection;
    private SupplierDAO supplierDAO;
    private Convertor convertor;

    public void initialize() throws SQLException, ClassNotFoundException {
        connection = DBconnection.getInstance().getConnection();
        this.supplierDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.SUPPLIER);
        convertor = new Convertor();
    }

    @Override
    public boolean addSupplier(SupplierDTO supplierDTO) throws DuplicateException, InUseException, SQLException, ClassNotFoundException {
        return supplierDAO.add(convertor.toSupplier(supplierDTO));
    }

    @Override
    public boolean deleteSupplier(String sId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(sId);
    }

    @Override
    public SupplierDTO searchSupplier(String sId) throws SQLException, ClassNotFoundException {
        return convertor.fromSupplier(supplierDAO.search(sId));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(convertor.toSupplier(supplierDTO));
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public ArrayList<String> loadSupplierIDs() throws SQLException, ClassNotFoundException {
        return null;
    }
}
