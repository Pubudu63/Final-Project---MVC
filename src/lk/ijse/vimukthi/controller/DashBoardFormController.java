package lk.ijse.vimukthi.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vimukthi.model.LoginModel;
import lk.ijse.vimukthi.to.User;
import lk.ijse.vimukthi.util.Navigation;
import lk.ijse.vimukthi.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class DashBoardFormController {
    public JFXTextField txtEmail;
    
    public AnchorPane pane;
    public AnchorPane pane1;
    public JFXPasswordField txtPassword;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (txtEmail.getText().equals("") || txtPassword.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter Email And Password.!").show();
            return;
        }
        User user = new User(email,password);
        try {
            boolean b = LoginModel.searchEmail(user);
            if (b) {
                if (LoginModel.resultSet.getString(5).equals("Admin")) {
                    Navigation.navigate(Routes.ADMIN,pane);
                } else {
                    Navigation.navigate(Routes.EMPLOYEE,pane);
                }
            } else {
                new Alert(Alert.AlertType.WARNING,"Please enter correct Email and Password.!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/RegisterForm.fxml"))));
        stage.setTitle("Register Form");
        stage.show();

    }

    public void txtEmailOnaction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnaction(ActionEvent actionEvent) throws IOException {
        btnLoginOnAction(actionEvent);
    }
}
