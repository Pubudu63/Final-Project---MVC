package lk.ijse.vimukthi.service;

import lk.ijse.vimukthi.dao.custom.impl.*;
import lk.ijse.vimukthi.service.custom.impl.UserServiceImpl;
import lk.ijse.vimukthi.service.custom.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER:
                return (T)new UserServiceImpl();

            default:
                return null;
        }
    }
}
