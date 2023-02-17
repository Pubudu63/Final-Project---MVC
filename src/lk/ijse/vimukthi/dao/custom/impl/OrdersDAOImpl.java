package lk.ijse.vimukthi.dao.custom.impl;

import lk.ijse.vimukthi.dao.custom.OrdersDAO;

import java.sql.Connection;

public class OrdersDAOImpl implements OrdersDAO {
    private final Connection connection;

    public OrdersDAOImpl(Connection connection) {
        this.connection = connection;
    }
}
