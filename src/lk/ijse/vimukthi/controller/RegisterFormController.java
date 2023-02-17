package lk.ijse.vimukthi.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.vimukthi.dto.UserDTO;
import lk.ijse.vimukthi.model.UserModel;
import lk.ijse.vimukthi.service.ServiceFactory;
import lk.ijse.vimukthi.service.ServiceTypes;
import lk.ijse.vimukthi.service.custom.UserService;
import lk.ijse.vimukthi.to.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterFormController {
    public AnchorPane pane;

    public JFXTextField txtUserId;
    public JFXTextField txtUserName;
    public JFXTextField txtUserEmail;
    public JFXTextField txtUserAddress;
    public JFXTextField txtUserContact;
    public JFXTextField txtUserPassword;
    public ToggleGroup rbtnGroup;
    public Label lblId;
    public Label lblpassword;

    private Pattern userIdPattern;
    private Pattern userNamePattern;
    private Pattern emailPattern;
    private Pattern addressPattern;
    private Pattern passwordPattern;
    private Pattern contactPattern;

    public UserService userService;


    public void initialize(){
        userIdPattern = Pattern.compile("^([U0]{2})([0-9]{2})$");
        userNamePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        addressPattern = Pattern.compile("^([A-Za-z0-9\\W\\s]{2,})$");
        passwordPattern = Pattern.compile("^([0-9]{4})$");
        contactPattern = Pattern.compile("^([0-9]{10})$");

        this.userService = ServiceFactory.getInstance().getService(ServiceTypes.USER);
    }


    public void btnAddUserOnAction(ActionEvent actionEvent) throws IOException {

        boolean isPasswordMatched = passwordPattern.matcher(txtUserPassword.getText()).matches();
        if (!isPasswordMatched) {
            txtUserPassword.setFocusColor(Paint.valueOf("Red"));
            txtUserPassword.requestFocus();
            new Alert(Alert.AlertType.ERROR, "invalid Password.").show();
            lblpassword.setText("Error");
            lblpassword.setTextFill(Paint.valueOf("Red"));
            return;
        }
        lblpassword.setText("valid!");
        lblpassword.setTextFill(Paint.valueOf("Green"));

        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        String userEmail = txtUserEmail.getText();
        String userAddress = txtUserAddress.getText();
        String userContact = txtUserContact.getText();
        String userPassword = txtUserPassword.getText();

        RadioButton selectedToggle = (RadioButton) rbtnGroup.getSelectedToggle();
        String position = selectedToggle.getText();


        UserDTO userDTO =new UserDTO(userId, userName, userEmail, userAddress, position, userContact, userPassword);
        if (userService.addUser(userDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();
        }


       /* try {
            boolean isAdded = UserModel.addNewUser(user);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Driver not found!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }




    public void btnClearOnAction(ActionEvent actionEvent) {

        txtUserId.clear();
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserAddress.clear();
        txtUserContact.clear();
        txtUserPassword.clear();
    }


    public void txtUserIDOnAction(ActionEvent actionEvent) {
        boolean isUserIdMatched = userIdPattern.matcher(txtUserId.getText()).matches();
        if (!isUserIdMatched) {
            txtUserId.setFocusColor(Paint.valueOf("Red"));
            txtUserId.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid User ID.").show();
        } else {
            txtUserName.requestFocus();
        }
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        if (!isUserNameMatched) {
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid User Name.").show();
        } else {
            txtUserEmail.requestFocus();
        }
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        boolean isEmailMatched = emailPattern.matcher(txtUserEmail.getText()).matches();
        if (!isEmailMatched) {
            txtUserEmail.setFocusColor(Paint.valueOf("Red"));
            txtUserEmail.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Email Address.").show();
        } else {
            txtUserAddress.requestFocus();
        }
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtUserAddress.getText()).matches();
        if (!isAddressMatched) {
            txtUserAddress.setFocusColor(Paint.valueOf("Red"));
            txtUserAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtUserContact.requestFocus();
        }

    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        boolean isPasswordMatched = passwordPattern.matcher(txtUserPassword.getText()).matches();
        if (!isPasswordMatched) {
            txtUserPassword.setFocusColor(Paint.valueOf("Red"));
            txtUserPassword.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Password.").show();
        }else{
            btnAddUserOnAction(actionEvent);
        }
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
        txtUserPassword.requestFocus();
    }

//====================== Mouse Click ============================================================

    public void nameMouseOnclick(MouseEvent mouseEvent){
        boolean isUserIdMatched = userIdPattern.matcher(txtUserId.getText()).matches();
        if (!isUserIdMatched) {
            txtUserId.setFocusColor(Paint.valueOf("Red"));
            txtUserId.requestFocus();
            lblId.setText("Error");
            lblId.setTextFill(Paint.valueOf("Red"));
        } else {
            txtUserName.requestFocus();
            lblId.setText("valid!");
            lblId.setTextFill(Paint.valueOf("Green"));
        }
    }

    public void emailOnMouseClick(MouseEvent mouseEvent) {
        boolean isNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        if (!isNameMatched) {
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid User Name.").show();
        } else {
            txtUserEmail.requestFocus();
        }
    }

    public void addressOnMouseClick(MouseEvent mouseEvent) {
        boolean isEmailMatched = emailPattern.matcher(txtUserEmail.getText()).matches();
        if (!isEmailMatched) {
            txtUserEmail.setFocusColor(Paint.valueOf("Red"));
            txtUserEmail.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Email Address.").show();
        } else {
            txtUserAddress.requestFocus();
        }
    }

    public void passwordOnMouseClick(MouseEvent mouseEvent) {
        boolean isContactMatched = contactPattern.matcher(txtUserContact.getText()).matches();
        if (!isContactMatched) {
            txtUserContact.setFocusColor(Paint.valueOf("Red"));
            txtUserContact.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Contact.").show();
        }else{
            txtUserPassword.requestFocus();
        }
    }

    public void contactOnMouseClick(MouseEvent mouseEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtUserAddress.getText()).matches();
        if (!isAddressMatched) {
            txtUserAddress.setFocusColor(Paint.valueOf("Red"));
            txtUserAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtUserContact.requestFocus();
        }
    }

}
