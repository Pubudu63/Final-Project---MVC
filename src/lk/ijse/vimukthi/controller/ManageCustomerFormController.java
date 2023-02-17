package lk.ijse.vimukthi.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.ijse.vimukthi.dto.CustomerDTO;
import lk.ijse.vimukthi.model.CustomerModel;
import lk.ijse.vimukthi.model.EmployeeModel;
import lk.ijse.vimukthi.service.ServiceFactory;
import lk.ijse.vimukthi.service.ServiceTypes;
import lk.ijse.vimukthi.service.custom.CustomerService;
import lk.ijse.vimukthi.to.Customer;
import lk.ijse.vimukthi.to.Employee;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageCustomerFormController {

    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerEmail;

    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colId;
    public TableView tblMain;
    public Label lblId;

    private Pattern customerIdPattern;
    private Pattern namePattern;
    private Pattern emailPattern;
    private Pattern addressPattern;
    private Pattern contactPattern;

    public CustomerService customerService;

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Customer,String>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<Customer,String>("contact"));

        customerIdPattern = Pattern.compile("^([C0]{2})([0-9]{2})$");
        namePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        addressPattern = Pattern.compile("^([A-Za-z0-9\\W\\s]{2,})$");
        contactPattern = Pattern.compile("^([0-9]{10})$");

        LoadCustomerID();
        customerService = ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);

    }
    /*public void initialize(){
        customerIdPattern = Pattern.compile("^([C0]{2})([0-9]{2})$");
        namePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        addressPattern = Pattern.compile("^([A-Za-z0-9\\W\\s]{2,})$");
        phonePattern = Pattern.compile("^([0-9]{10})$");
    }*/


    public void btnAddOnAction(ActionEvent actionEvent) {
        String cId = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String email = txtCustomerEmail.getText();
        String address = txtCustomerAddress.getText();
        String contact = txtCustomerContact.getText();


        CustomerDTO customer = new CustomerDTO(cId,name,email,address,contact);
        try{
             if  (customerService.addCustomer(customer)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Sucess..!").show();
            }else{
                 new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
             }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Customer Already Added..!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*try {
            boolean isAdded = CustomerModel.addNewCustomer(cId,name,email,address,contact);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Sucess..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Customer Already Added..!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerEmail.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String cId = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String email = txtCustomerEmail.getText();
        String address = txtCustomerAddress.getText();
        String contact = txtCustomerContact.getText();

        CustomerDTO customerDTO = new CustomerDTO(cId,name,email,address,contact);

        try {
            boolean isAdded = customerService.updateCustomer(customerDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Update Successful").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something going wrong").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerEmail.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            boolean isDelete = CustomerModel.deleteCustomer(txtCustomerId.getText());
            if(isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted Success..!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerEmail.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
    }



    public void btnSearchOnAction(ActionEvent actionEvent) {

        String cId = txtCustomerId.getText();

        try {
            Customer customer = CustomerModel.searchCustomer(cId);
            if (customer.equals("null")) {
                new Alert(Alert.AlertType.WARNING, "Customer Not Found!").show();
            } else {
                txtCustomerName.setText(customer.getName());
                txtCustomerEmail.setText(customer.getEmail());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerContact.setText(customer.getContact());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

//        txtCustomerId.clear();
//        txtCustomerName.clear();
//        txtCustomerEmail.clear();
//        txtCustomerAddress.clear();
//        txtCustomerContact.clear();
    }



    public ObservableList<String> observableList = FXCollections.observableArrayList();



    private void LoadCustomerID() {
        try {


            ArrayList<String> list = customerService.loadCustomerIDs();


            for (String id : list) {
                observableList.add(id);
            }
            tblMain.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        observableList.clear();
        LoadCustomerID();
    }

    

    public void txtAddresslOnAction(ActionEvent actionEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtCustomerAddress.getText()).matches();
        if (!isAddressMatched) {
            txtCustomerAddress.setFocusColor(Paint.valueOf("Red"));
            txtCustomerAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtCustomerContact.requestFocus();
        }
    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
        boolean isCustomerIdMatched = customerIdPattern.matcher(txtCustomerId.getText()).matches();
        if (!isCustomerIdMatched) {
            txtCustomerId.setFocusColor(Paint.valueOf("Red"));
            txtCustomerId.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Customer ID.").show();
        } else {
            txtCustomerName.requestFocus();
        }
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        boolean isNameMatched = namePattern.matcher(txtCustomerName.getText()).matches();
        if (!isNameMatched) {
            txtCustomerName.setFocusColor(Paint.valueOf("Red"));
            txtCustomerName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Customer Name.").show();
        } else {
            txtCustomerEmail.requestFocus();
        }
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        boolean isEmailMatched = emailPattern.matcher(txtCustomerEmail.getText()).matches();
        if (!isEmailMatched) {
            txtCustomerEmail.setFocusColor(Paint.valueOf("Red"));
            txtCustomerEmail.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Email Address.").show();
        } else {
            txtCustomerAddress.requestFocus();
        }
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
        boolean isPhonedMatched = contactPattern.matcher(txtCustomerContact.getText()).matches();
        if (!isPhonedMatched) {
            txtCustomerContact.setFocusColor(Paint.valueOf("Red"));
            txtCustomerContact.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Password.").show();
        } else {
            btnAddOnAction(actionEvent);
        }
    }

    //============== Mouse Click =======================================


    public void contactMouseOnClick(MouseEvent mouseEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtCustomerAddress.getText()).matches();
        if (!isAddressMatched) {
            txtCustomerAddress.setFocusColor(Paint.valueOf("Red"));
            txtCustomerAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtCustomerContact.requestFocus();
        }
    }


    public void addressMouseOnClick(MouseEvent mouseEvent) {
        boolean isEmailMatched = emailPattern.matcher(txtCustomerEmail.getText()).matches();
        if (!isEmailMatched) {
            txtCustomerEmail.setFocusColor(Paint.valueOf("Red"));
            txtCustomerEmail.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Email Address.").show();
        } else {
            txtCustomerAddress.requestFocus();
        }
    }

    public void IdMouseOnClick(MouseEvent mouseEvent) {

    }

    public void nameMouseOnClick(MouseEvent mouseEvent) {
        boolean isUserIdMatched = customerIdPattern.matcher(txtCustomerId.getText()).matches();
        if (!isUserIdMatched) {
            txtCustomerId.setFocusColor(Paint.valueOf("Red"));
            txtCustomerId.requestFocus();
            lblId.setText("Error");
            lblId.setTextFill(Paint.valueOf("Red"));
        } else {
            txtCustomerName.requestFocus();
            lblId.setText("valid!");
            lblId.setTextFill(Paint.valueOf("Green"));
        }
    }

    public void emailMouseOnClick(MouseEvent mouseEvent) {
        boolean isNameMatched = namePattern.matcher(txtCustomerName.getText()).matches();
        if (!isNameMatched) {
            txtCustomerName.setFocusColor(Paint.valueOf("Red"));
            txtCustomerName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid User Name.").show();
        } else {
            txtCustomerEmail.requestFocus();
            lblId.setText("");
        }
    }
}
