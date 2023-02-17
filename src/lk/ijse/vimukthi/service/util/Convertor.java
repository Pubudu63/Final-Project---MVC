package lk.ijse.vimukthi.service.util;

import lk.ijse.vimukthi.dto.*;
import lk.ijse.vimukthi.to.*;

public class Convertor {
    public UserDTO fromUser(lk.ijse.vimukthi.entity.User user){
        return new UserDTO(user.getId(),user.getName(), user.getEmail(), user.getAddress(), user.getPassword());
    }

    public lk.ijse.vimukthi.entity.User toUser(UserDTO userDTO){
        return new lk.ijse.vimukthi.entity.User(userDTO.getId(),userDTO.getName(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPassword(), userDTO.getContact());
}

    public lk.ijse.vimukthi.entity.Customer toCustomer(CustomerDTO customer){
        return new lk.ijse.vimukthi.entity.Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    public CustomerDTO fromCustomer(lk.ijse.vimukthi.entity.Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    public EmployeeDTO fromEmployee(lk.ijse.vimukthi.entity.Employee employee){
        return new EmployeeDTO(employee.getEmId(), employee.getName(), employee.getEmail(), employee.getContact());
    }

    public lk.ijse.vimukthi.entity.Employee toEmployee(EmployeeDTO employee){
        return new lk.ijse.vimukthi.entity.Employee(employee.getEmId(), employee.getName(), employee.getEmail(), employee.getContact());
    }

    public Item fromItem(lk.ijse.vimukthi.entity.Item item){
        return new Item (item.getItemId(), item.getItemName(),item.getItemQty(), item.getItemPrice());
    }

    public lk.ijse.vimukthi.entity.Item  toItem(ItemDTO item){
        return new lk.ijse.vimukthi.entity.Item (item.getItemId(), item.getItemName(),item.getItemQty(), item.getItemPrice());
    }

    public SupplierDTO fromSupplier(lk.ijse.vimukthi.entity.Supplier supplier){
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getEmail(), supplier.getAddress(), supplier.getContact());
    }

    public lk.ijse.vimukthi.entity.Supplier toSupplier(SupplierDTO supplier){
        return new lk.ijse.vimukthi.entity.Supplier(supplier.getId(), supplier.getName(), supplier.getEmail(), supplier.getAddress(), supplier.getContact());
    }

}
