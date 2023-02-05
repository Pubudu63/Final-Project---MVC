package lk.ijse.vimukthi.model;

import lk.ijse.vimukthi.to.CartDetails;
import lk.ijse.vimukthi.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderModel {

    public static boolean orderDetail(String oderId, ArrayList<CartDetails> list) throws SQLException, ClassNotFoundException {
        for(CartDetails cartDetails: list){
            CartDetails cartDetails1 = new CartDetails(cartDetails.getCode(), cartDetails.getQty());
            if (!save(oderId,cartDetails1)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oderId, CartDetails cartDetails1) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO order_details VALUES(?,?,?)",oderId,cartDetails1.getCode(),Integer.parseInt(cartDetails1.getQty()));
    }
}
