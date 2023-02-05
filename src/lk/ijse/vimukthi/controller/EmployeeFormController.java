package lk.ijse.vimukthi.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vimukthi.util.Navigation;
import lk.ijse.vimukthi.util.Routes;

import java.io.IOException;

public class EmployeeFormController {

    public AnchorPane pane;
    public AnchorPane pane1;

    public void initialize() throws IOException {
        Navigation.navigate(Routes.PLACEORDER,pane1);
    }

    public void btnManageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGECUSTOMER,pane1);
    }

    public void btnManageSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGESUPPLIER,pane1);
    }

    public void btnManageItemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGEITEM, pane1);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER, pane1);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,pane);
    }
}
