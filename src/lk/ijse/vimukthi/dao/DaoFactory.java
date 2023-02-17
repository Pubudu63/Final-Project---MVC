package lk.ijse.vimukthi.dao;

import lk.ijse.vimukthi.dao.custom.impl.*;


import java.sql.Connection;

public class DaoFactory {

    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection,DaoTypes daoType) {
        switch (daoType){
            case USER:
                return (T)new UserDAOImpl(connection);

            case CUSTOMER:
                return (T)new CustomerDAOImpl(connection);

            case EMPLOYEE:
                return (T)new EmployeeDAOImpl(connection);

            case ITEM:
                return (T)new ItemDAOImpl(connection);

            case ORDERS:
                return (T)new OrdersDAOImpl(connection);

            case PAYMENT:
                return (T)new PaymentDAOImpl(connection);

            case SUPPLIER:
                return (T)new SupplierDAOImpl(connection);

            default:
                return null;

        }

    }
}
