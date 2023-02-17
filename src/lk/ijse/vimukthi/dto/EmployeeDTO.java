package lk.ijse.vimukthi.dto;

public class EmployeeDTO {
    private String emId;
    private String name;
    private String email;
    private String address;
    private String contact;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String emId, String name, String email, String contact) {
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "emId='" + emId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getEmId() {
        return emId;
    }

    public void setEmId(String emId) {
        this.emId = emId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public EmployeeDTO(String emId, String name, String email, String address, String contact) {
        this.emId = emId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }
}
