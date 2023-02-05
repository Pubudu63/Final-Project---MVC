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
import lk.ijse.vimukthi.model.ItemModel;
import lk.ijse.vimukthi.to.Employee;
import lk.ijse.vimukthi.to.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageItemFormController {
    public TableView tblItem;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colItemQty;
    public TableColumn colItemPrice;

    public JFXTextField txtItemId;
    public JFXTextField txtItemPrice;
    public JFXTextField txtitemQty;
    public JFXTextField txtItemName;

    private Pattern itemIdPattern;
    private Pattern namePattern;
    private Pattern qtyPattern;
    private Pattern pricePattern;

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String itemId = txtItemId.getText();

        try {
            Item item = ItemModel.searchItem(itemId);
            if (item.equals("null")) {
                new Alert(Alert.AlertType.WARNING, "Item Not Found!").show();
            } else {
                txtItemId.setText(item.getItemId());
                txtItemName.setText(item.getItemName());
                txtitemQty.setText(item.getItemQty());
                txtItemPrice.setText(item.getItemPrice());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        String itemQty = txtitemQty.getText();
        String itemPrice = txtItemPrice.getText();


        try {
            boolean isAdded = ItemModel.addNewItem(itemId,itemName,itemQty,itemPrice);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added Sucess..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtItemId.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtitemQty.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        String itemQty = txtitemQty.getText();
        String itemPrice = txtItemPrice.getText();

        Item item = new Item(itemId,itemName,itemQty,itemPrice);

        try {
            boolean isAdded = ItemModel.updateItem(item);
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

        txtItemId.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtitemQty.clear();
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete = ItemModel.deleteItem(txtItemId.getText());
            if(isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted Success..!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something is going wrong..!").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtItemId.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtitemQty.clear();
    }

    public ObservableList<Item> observableList = FXCollections.observableArrayList();

    public void initialize(){

        colItemId.setCellValueFactory(new PropertyValueFactory<Employee,String>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<Employee,String>("itemName"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<Employee,String>("itemQty"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<Employee,String>("itemPrice"));

        itemIdPattern = Pattern.compile("^([I0]{3})([0-9]{2})$");
        namePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        qtyPattern = Pattern.compile("^([0-9]{1,4})$");
        pricePattern = Pattern.compile("^([0-9]{1,}[.][0-9]{2})$");

    }

    private void LoadItem() {
        try {
            ArrayList<Item> list = ItemModel.getAllItem();

            for (Item item : list) {
                observableList.add(item);
            }
            tblItem.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        observableList.clear();
        LoadItem();
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        boolean isName = namePattern.matcher(txtItemName.getText()).matches();
        if (!isName){
            txtItemName.setFocusColor(Paint.valueOf("Red"));
            txtItemName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Description.").show();
        } else {
            txtitemQty.requestFocus();
        }
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        boolean isQtyMatched = qtyPattern.matcher(txtitemQty.getText()).matches();
        if (!isQtyMatched) {
            txtitemQty.setFocusColor(Paint.valueOf("Red"));
            txtitemQty.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Qty.").show();
        } else {
            txtItemPrice.requestFocus();
        }
    }

    public void txtPriceOnAction(ActionEvent actionEvent) {
        boolean isPriceMatched = pricePattern.matcher(txtItemPrice.getText()).matches();
        if (!isPriceMatched) {
            txtItemPrice.setFocusColor(Paint.valueOf("Red"));
            txtItemPrice.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Price.").show();
        } else {
            btnUpdateOnAction(actionEvent);
        }
    }

    public void txtItemIdOnAction(ActionEvent actionEvent) {
        if (txtItemId.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING,"Please enter ID.").show();
        }
        String iCode = txtItemId.getText();

        try {
            Item item = ItemModel.searchItem(iCode);
            if (item.equals(null)) {
                new Alert(Alert.AlertType.WARNING, "Item Not Found!").show();
            } else {
                txtItemName.setText(item.getItemName());
                txtitemQty.setText(item.getItemQty()+"");
                txtItemPrice.setText(item.getItemPrice()+"");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
