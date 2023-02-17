package lk.ijse.vimukthi.dto;public class CartDetailsDTO {
    String code;
    String qty;

    @Override
    public String toString() {
        return "CartDetailsDTO{" +
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

    public CartDetailsDTO() {
    }

    public CartDetailsDTO(String code, String qty) {
        this.code = code;
        this.qty = qty;
    }
}
