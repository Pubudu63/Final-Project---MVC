package lk.ijse.vimukthi.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vimukthi.util.Navigation;
import lk.ijse.vimukthi.util.Routes;

import java.io.IOException;

public class AdminFormController {
    public AnchorPane pane;
    public AnchorPane pane1;

    public void initialize() throws IOException {
        Navigation.navigate(Routes.PLACEORDER,pane1);
    }

    public void btnManageCustomers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGECUSTOMER,pane1);
    }

    public void btnManageEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGEEMPLOYEE,pane1);
    }

    public void btnManageSupplier(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGESUPPLIER,pane1);
    }


    public void btnIncomeReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INCOMEREPORT, pane1);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,pane);
    }

    public void btnPlaceOrder(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, pane1);
    }

    public void btnManageItem(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGEITEM, pane1);
    }

    public void btnManageUserOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGEUSER, pane1);
    }
}
