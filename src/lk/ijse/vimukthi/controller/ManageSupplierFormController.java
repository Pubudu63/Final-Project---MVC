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
import lk.ijse.vimukthi.model.CustomerModel;
import lk.ijse.vimukthi.model.EmployeeModel;
import lk.ijse.vimukthi.model.SupplierModel;
import lk.ijse.vimukthi.to.Customer;
import lk.ijse.vimukthi.to.Employee;
import lk.ijse.vimukthi.to.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageSupplierFormController {

    public JFXTextField txtSupplierContact;
    public JFXTextField txtSupplierAddress;
    public JFXTextField txtSupplierId;
    public JFXTextField txtSupplierName;
    public JFXTextField txtSupplierEmail;

    public TableView tblSupplier;
    public TableColumn colSupId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colContact;

    private Pattern supplierIdPattern;
    private Pattern namePattern;
    private Pattern emailPattern;
    private Pattern addressPattern;
    private Pattern contactPattern;

    public void initialize(){

        colSupId.setCellValueFactory(new PropertyValueFactory<Supplier,String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Supplier,String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Supplier,String>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Supplier,String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<Supplier,String>("contact"));

        supplierIdPattern = Pattern.compile("^([S0]{2})([0-9]{2})$");
        namePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        addressPattern = Pattern.compile("^([A-Za-z0-9\\W\\s]{2,})$");
        contactPattern = Pattern.compile("^([0-9]{10})$");

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

        String sId = txtSupplierId.getText();

        try {
            Supplier supplier = SupplierModel.searchSupplier(sId);
            if (supplier.equals("null")) {
                new Alert(Alert.AlertType.WARNING, "Supplier Not Found!").show();
            } else {
                txtSupplierName.setText(supplier.getName());
                txtSupplierEmail.setText(supplier.getEmail());
                txtSupplierAddress.setText(supplier.getAddress());
                txtSupplierContact.setText(supplier.getContact());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String sId = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String email = txtSupplierEmail.getText();
        String address = txtSupplierAddress.getText();
        String contact = txtSupplierContact.getText();

        try {
            boolean isAdded = SupplierModel.addNewSupplier(sId,name,email,address,contact);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added Success..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierEmail.clear();
        txtSupplierAddress.clear();
        txtSupplierContact.clear();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String email = txtSupplierEmail.getText();
        String address = txtSupplierAddress.getText();
        String contact = txtSupplierContact.getText();

        Supplier supplier = new Supplier(id,name,email,address,contact);

        try {
            boolean isAdded = SupplierModel.updateSupplier(supplier);
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

        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierEmail.clear();
        txtSupplierAddress.clear();
        txtSupplierContact.clear();
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            boolean isDelete = SupplierModel.deleteSupplier(txtSupplierId.getText());
            if(isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted Success..!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Supplier> observableList = FXCollections.observableArrayList();



    private void LoadSupplier() {
        try {
            ArrayList<Supplier> list = SupplierModel.getAllSuppliers();

            for (Supplier supplier : list) {
                observableList.add(supplier);
            }
            tblSupplier.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        observableList.clear();
        LoadSupplier();
    }

    public void txtContactOnaction(ActionEvent actionEvent) {
        boolean isContactMatched = contactPattern.matcher(txtSupplierContact.getText()).matches();
        if (!isContactMatched) {
            txtSupplierContact.setFocusColor(Paint.valueOf("Red"));
            txtSupplierContact.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Password.").show();
        } else {
            btnAddOnAction(actionEvent);
        }
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtSupplierAddress.getText()).matches();
        if (!isAddressMatched) {
            txtSupplierAddress.setFocusColor(Paint.valueOf("Red"));
            txtSupplierAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtSupplierContact.requestFocus();
        }
    }

    public void txtSupplierIdOnaction(ActionEvent actionEvent) {
        boolean isSupplierIdMatched = supplierIdPattern.matcher(txtSupplierId.getText()).matches();
        if (!isSupplierIdMatched) {
            txtSupplierId.setFocusColor(Paint.valueOf("Red"));
            txtSupplierId.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Supplier ID.").show();
        } else {
            txtSupplierName.requestFocus();
        }
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        boolean isNameMatched = namePattern.matcher(txtSupplierName.getText()).matches();
        if (!isNameMatched) {
            txtSupplierName.setFocusColor(Paint.valueOf("Red"));
            txtSupplierName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Supplier Name.").show();
        } else {
            txtSupplierEmail.requestFocus();
        }
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        boolean isEmailMatched = emailPattern.matcher(txtSupplierEmail.getText()).matches();
        if (!isEmailMatched) {
            txtSupplierEmail.setFocusColor(Paint.valueOf("Red"));
            txtSupplierEmail.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Email Address.").show();
        } else {
            txtSupplierAddress.requestFocus();
        }
    }
}
