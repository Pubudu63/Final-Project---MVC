package lk.ijse.vimukthi.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.vimukthi.model.CustomerModel;
import lk.ijse.vimukthi.model.ItemModel;
import lk.ijse.vimukthi.model.OrderModel;
import lk.ijse.vimukthi.to.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceOrderFormController {

    public ComboBox cmbItemCode;
    public Label lblName;
    public Label lblUnitPrice;
    public TextField txtQty;

    public TableView tblCart;

    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colAction;
    public Label lblCustomerName;

    public JFXComboBox cmbCustomerID;
    public Label lblBillingID;
    public Label lblOrderDate;
    public Label lblQty;

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadNextOrderId();
        loadOrderDate();
        loadCustomerIds();
        LoadItemCodes();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory("name"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("button"));
    }


    private void LoadItemCodes() {
        ObservableList<String> observableList = FXCollections.observableArrayList();

        try {
            ArrayList<String> codeList = CustomerModel.loadItemCode();
            for (String code : codeList) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadCustomerIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<String> idList = CustomerModel.loadCustomerIds();
            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerID.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            lblBillingID.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbCustomerIDOnAction(ActionEvent actionEvent) {
        String id = String.valueOf(cmbCustomerID.getValue());

        try {
            Customer customer = CustomerModel.searchCustomer(id);
            fillCustomerName(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillCustomerName(Customer customer) {
        lblCustomerName.setText(customer.getName());
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbItemCode.getValue());

        try {
            Item item = ItemModel.searchItem(code);
            fillTheFields(item);
            txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillTheFields(Item item) {
        lblName.setText(item.getItemName());
        lblUnitPrice.setText(item.getItemPrice() + "");
        lblQty.setText(item.getItemQty() + "");
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource("/lk/ijse/vimukthi/view/ManageCustomerForm.fxml")
        )));
        stage.show();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbItemCode.getValue());
        String description = lblName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.clear();

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);

                    total = unitPrice * qty;
                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblCart.refresh();
                    return;
                }
            }
        }

        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = (PlaceOrderTM) tblCart.getSelectionModel().getSelectedItem();
                tblCart.getItems().removeAll(tm);
            }});
        obList.add(new PlaceOrderTM(code,description,qty,unitPrice,total,btnDelete));
        tblCart.setItems(obList);
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
        String billingID = lblBillingID.getText();
        String customerId = String.valueOf(cmbCustomerID.getValue());

        ArrayList<CartDetails> list = new ArrayList();

        for (int i = 0 ; i < tblCart.getItems().size(); i++){
            PlaceOrderTM tm = obList.get(i);
            list.add(new CartDetails(tm.getCode(),String.valueOf(tm.getQty())));
        }
        PlaceOrder placeOrder = new PlaceOrder(billingID,customerId,list);

        try {
            boolean b = OrderModel.placeOrderMethod(placeOrder);

            if (b){
                new Alert(Alert.AlertType.INFORMATION,"ADDED").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"ERROR").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

    