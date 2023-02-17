package lk.ijse.vimukthi.to;

public class CartDetails {
    String code;
    String qty;

    @Override
    public String toString() {
        return "CartDetails{" +
                "code='" + code + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    public CartDetails() {
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

    public CartDetails(String code, String qty) {
        this.code = code;
        this.qty = qty;
    }
}
