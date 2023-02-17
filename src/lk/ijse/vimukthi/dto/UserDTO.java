package lk.ijse.vimukthi.dto;

import lk.ijse.vimukthi.to.User;

public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String position;
    private String contact;

    public UserDTO(String string, String resultSetString, String setString, String s, String string1) {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                ", contact='" + contact + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String password;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO(String id, String name, String email, String address, String position, String contact, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.position = position;
        this.contact = contact;
        this.password = password;
    }
}
