package lk.ijse.vimukthi.service.custom;


import lk.ijse.vimukthi.dto.SupplierDTO;
import lk.ijse.vimukthi.service.SuperService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.to.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierService extends SuperService {
    boolean addSupplier(SupplierDTO supplierDAO) throws DuplicateException, InUseException, SQLException, ClassNotFoundException;

    boolean deleteSupplier(String sId) throws SQLException, ClassNotFoundException;

    SupplierDTO searchSupplier(String sId) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadSupplierIDs() throws SQLException, ClassNotFoundException;

}
