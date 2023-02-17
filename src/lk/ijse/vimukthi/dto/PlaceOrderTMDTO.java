package lk.ijse.vimukthi.dto;

import javafx.scene.control.Button;

public class PlaceOrderTMDTO {
    String code;
    String name;
    int qty;
    double unitPrice;
    double total;
    Button button;

    public PlaceOrderTMDTO() {
    }

    @Override
    public String toString() {
        return "PlaceOrderTMDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                ", button=" + button +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public PlaceOrderTMDTO(String code, String name, int qty, double unitPrice, double total, Button button) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.button = button;
    }
}
