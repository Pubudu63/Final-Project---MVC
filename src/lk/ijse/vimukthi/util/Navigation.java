package lk.ijse.vimukthi.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes routes, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
         Navigation.pane.getChildren().clear();

        switch (routes){
            case DASHBOARD:
                initUI("DashBoardForm.fxml");
                break;

            case REGISTER:
                initUI("RegisterForm.fxml");
                break;

            case ADMIN:
                initUI("AdminForm.fxml");
                break;

            case EMPLOYEE:
                initUI("EmployeeForm.fxml");
                break;

            case MANAGEEMPLOYEE:
                initUI("ManageEmployeeForm.fxml");
                break;

            case MANAGEUSER:
                initUI("ManageUserForm.fxml");
                break;

            case MANAGECUSTOMER:
                initUI("ManageCustomerForm.fxml");
                break;

            case MANAGESUPPLIER:
                initUI("ManageSupplierForm.fxml");
                break;

            case MANAGEITEM:
                initUI("ManageItemForm.fxml");
                break;

            case INCOMEREPORT:
                initUI("IncomeReportForm.fxml");
                break;

            case PLACEORDER:
                initUI("PlaceOrderForm.fxml");
                break;
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader
                .load(Navigation.class.getResource("/lk/ijse/vimukthi/view/"+location))
        );
    }
}
