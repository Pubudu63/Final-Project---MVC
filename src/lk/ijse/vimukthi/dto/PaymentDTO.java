package lk.ijse.vimukthi.dto;

public class PaymentDTO {
    private String pId;
    private String description;
    private Double value;
    private String emId;
    private String sId;

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "pId='" + pId + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", emId='" + emId + '\'' +
                ", sId='" + sId + '\'' +
                '}';
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getEmId() {
        return emId;
    }

    public void setEmId(String emId) {
        this.emId = emId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public PaymentDTO(String pId, String description, Double value, String emId, String sId) {
        this.pId = pId;
        this.description = description;
        this.value = value;
        this.emId = emId;
        this.sId = sId;
    }

    public PaymentDTO() {
    }
}
