package lk.ijse.vimukthi.entity;

public class OrderDetails implements SuperEntity{
    String code;
    String qty;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "code='" + code + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public OrderDetails(String code, String qty) {
        this.code = code;
        this.qty = qty;
    }
}
