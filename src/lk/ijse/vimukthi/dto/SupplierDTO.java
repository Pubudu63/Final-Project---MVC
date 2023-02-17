package lk.ijse.vimukthi.dto;

public class SupplierDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String contact;

    public SupplierDTO() {
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public SupplierDTO(String id, String name, String email, String address, String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }
}
