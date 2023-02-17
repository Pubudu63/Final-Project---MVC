package lk.ijse.vimukthi.dao.custom.impl;

import lk.ijse.vimukthi.dao.custom.PaymentDAO;

import java.sql.Connection;

public class PaymentDAOImpl implements PaymentDAO {
    private final Connection connection;

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
    }
}
