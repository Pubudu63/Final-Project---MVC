package lk.ijse.vimukthi.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.ijse.vimukthi.model.EmployeeModel;
import lk.ijse.vimukthi.to.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageEmployeeFormController {

    public JFXTextField txtEmployeeContact;
    public JFXTextField txtEmployeeAddress;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeEmail;

    public TableView tblEmployee;
    public TableColumn colEmId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colContact;

    private Pattern employeeIdPattern;
    private Pattern namePattern;
    private Pattern emailPattern;
    private Pattern addressPattern;
    private Pattern contactPattern;


    public void btnSearchOnAction(ActionEvent actionEvent) {
        String emId = txtEmployeeId.getText();

        try {
            Employee employee = EmployeeModel.searchEmployee(emId);
            if (employee.equals("null")) {
                new Alert(Alert.AlertType.WARNING, "Employee Not Found!").show();
            } else {
                txtEmployeeName.setText(employee.getName());
                txtEmployeeEmail.setText(employee.getEmail());
                txtEmployeeAddress.setText(employee.getAddress());
                txtEmployeeContact.setText(employee.getContact());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String emId = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String email = txtEmployeeEmail.getText();
        String address = txtEmployeeAddress.getText();
        String contact = txtEmployeeContact.getText();

        try {
            boolean isAdded = EmployeeModel.addNewEmployee(emId,name,email,address,contact);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added Sucess..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeEmail.clear();
        txtEmployeeAddress.clear();
        txtEmployeeContact.clear();

    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String emId = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String email = txtEmployeeEmail.getText();
        String address = txtEmployeeAddress.getText();
        String contact = txtEmployeeContact.getText();

        Employee employee = new Employee(emId,name,email,address,contact);

        try {
            boolean isAdded = EmployeeModel.updateEmployee(employee);
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

        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeEmail.clear();
        txtEmployeeAddress.clear();
        txtEmployeeContact.clear();
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            boolean isDelete = EmployeeModel.deleteEmployee(txtEmployeeId.getText());
            if(isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted Success..!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeEmail.clear();
        txtEmployeeAddress.clear();
        txtEmployeeContact.clear();

    }



    public ObservableList<Employee> observableList = FXCollections.observableArrayList();

    public void initialize(){

        colEmId.setCellValueFactory(new PropertyValueFactory<Employee,String>("emId"));
        colName.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<Employee,String>("contact"));

        employeeIdPattern = Pattern.compile("^([E0]{2})([0-9]{2})$");
        namePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        addressPattern = Pattern.compile("^([A-Za-z0-9\\W\\s]{2,})$");
        contactPattern = Pattern.compile("^([0-9]{10})$");

    }

    private void LoadEmployee() {
        try {
            ArrayList<Employee> list = EmployeeModel.getAllEmployees();

            for (Employee employee : list) {
                observableList.add(employee);
            }
            tblEmployee.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        observableList.clear();
        LoadEmployee();
    }

    //=================================== REGRX PART =================================


    public void txtContactOnAction(ActionEvent actionEvent) {
        boolean isContactMatched = contactPattern.matcher(txtEmployeeContact.getText()).matches();
        if (!isContactMatched) {
            txtEmployeeContact.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeContact.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Password.").show();
        } else {
            btnAddOnAction(actionEvent);
        }
    }

    public void txtAddresslOnAction(ActionEvent actionEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtEmployeeAddress.getText()).matches();
        if (!isAddressMatched) {
            txtEmployeeAddress.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtEmployeeContact.requestFocus();
        }
    }

    public void txtEmployeerIdOnAction(ActionEvent actionEvent) {
        boolean isEmployeeIdMatched = employeeIdPattern.matcher(txtEmployeeId.getText()).matches();
        if (!isEmployeeIdMatched) {
            txtEmployeeId.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeId.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Employee ID.").show();
        } else {
            txtEmployeeName.requestFocus();
        }
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        boolean isNameMatched = namePattern.matcher(txtEmployeeName.getText()).matches();
        if (!isNameMatched) {
            txtEmployeeName.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Employee Name.").show();
        } else {
            txtEmployeeEmail.requestFocus();
        }
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        boolean isEmailMatched = emailPattern.matcher(txtEmployeeEmail.getText()).matches();
        if (!isEmailMatched) {
            txtEmployeeEmail.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeEmail.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Email Address.").show();
        } else {
            txtEmployeeAddress.requestFocus();
        }
    }
}
