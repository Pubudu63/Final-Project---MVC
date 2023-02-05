package lk.ijse.vimukthi.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.vimukthi.model.EmployeeModel;
import lk.ijse.vimukthi.model.UserModel;
import lk.ijse.vimukthi.to.Employee;
import lk.ijse.vimukthi.to.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageUserFormController {
    
    public TableView tblUser;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colContact;
    public JFXTextField txtUserName;
    public JFXTextField txtUserEmail;
    public JFXTextField txtUserId;
    public JFXTextField txtUserAddress;
    public JFXTextField txtUserContact;
    public JFXTextField txtUserPassword;
    public JFXTextField txtUserPosition;
    public TableColumn colUId;

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String uId = txtUserId.getText();

        try {
            User user = UserModel.searchUser(uId);
            if (user.equals("null")) {
                new Alert(Alert.AlertType.WARNING, "User Not Found!").show();
            } else {
                txtUserName.setText(user.getName());
                txtUserEmail.setText(user.getEmail());
                txtUserAddress.setText(user.getAddress());
                txtUserPosition.setText(user.getPosition());
                txtUserContact.setText(user.getContact());
                txtUserPassword.setText(user.getPassword());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String uId = txtUserId.getText();
        String name = txtUserName.getText();
        String email = txtUserEmail.getText();
        String address = txtUserAddress.getText();
        String position = txtUserPosition.getText();
        String contact = txtUserContact.getText();
        String password = txtUserPassword.getText();

        User user = new User(uId,name,email,address,position,contact,password);
        try {
            boolean isAdded = UserModel.addNewUser(user);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "User Added Success..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtUserId.clear();
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserAddress.clear();
        txtUserPosition.clear();
        txtUserContact.clear();
        txtUserPassword.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String uId = txtUserId.getText();
        String name = txtUserName.getText();
        String email = txtUserEmail.getText();
        String address = txtUserAddress.getText();
        String position = txtUserPosition.getText();
        String contact = txtUserContact.getText();
        String password = txtUserPassword.getText();

        User user = new User(uId,name,email,address,position,contact,password);

        try {
            boolean isAdded = UserModel.updateUser(user);
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

        txtUserId.clear();
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserAddress.clear();
        txtUserPosition.clear();
        txtUserContact.clear();
        txtUserPassword.clear();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete = UserModel.deleteUser(txtUserId.getText());
            if(isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Success..!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtUserId.clear();
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserAddress.clear();
        txtUserPosition.clear();
        txtUserContact.clear();
        txtUserPassword.clear();

    }
    public ObservableList<User> observableList = FXCollections.observableArrayList();

    public void initialize(){

        colUId.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<User,String>("contact"));


    }
    private void LoadUser() {
        try {
            ArrayList<User> list = UserModel.getAllUser();

            for (User user : list) {
                observableList.add(user);
            }
            tblUser.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnReloadOnAction(ActionEvent actionEvent) {
        observableList.clear();
        LoadUser();
    }

    //========================== REGEX PART ====================================================================

    public void txtContactOnAction(ActionEvent actionEvent) {

    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
    }

    public void txtUserIdOnAction(ActionEvent actionEvent) {
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
    }



    public void txtPasswordOnAction(ActionEvent actionEvent) {
    }

    public void txtPositionOnAction(ActionEvent actionEvent) {
    }
}
