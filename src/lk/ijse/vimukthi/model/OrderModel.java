package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.PlaceOrder;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderModel {

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT oId FROM orders ORDER BY oId DESC LIMIT 1");
        if (resultSet.next()) {
            return generateNextOrderId(resultSet.getString(1));
        }
        return generateNextOrderId(resultSet.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "O0" + id;
        } else {
            return "O0001";
        }
    }

    public static boolean placeOrderMethod(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        boolean isOrders = CrudUtil.execute("INSERT INTO orders VALUES(?,?,?)", placeOrder.getOderId(), LocalDate.now(), placeOrder.getCustomerId());

        if (isOrders){
            System.out.println("One Successful!");
            boolean orderDetail = PlaceOrderModel.orderDetail(placeOrder.getOderId(), placeOrder.getList());
            if (orderDetail){
                return true;
            }
        }
        return false;
    }
}
