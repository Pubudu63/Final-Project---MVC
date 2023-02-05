package lk.ijse.vimukthi.to;

public class CartDetails {
    String code;
    String qty;

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

    public CartDetails() {
    }

    public CartDetails(String code, String qty) {
        this.code = code;
        this.qty = qty;
    }
}
