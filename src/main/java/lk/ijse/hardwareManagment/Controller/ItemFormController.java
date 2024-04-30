/*package lk.ijse.hardwareManagment.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.hardwareManagment.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemFormController {

        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colCode;

        @FXML
        private TableColumn<?, ?> colDescription;

        @FXML
        private TableColumn<?, ?> colQty;

        @FXML
        private TableView<?> tblItem;

        @FXML
        private TextField txtCode;

        @FXML
        private TextField txtDescription;


        @FXML
        void btnClearOnACtion(ActionEvent event) {
            this.clearFields()

        }

        private void clearFields() {
            this.txtid.setText("");
            this.txtname.setText("");
            this.txtAddress.setText("");
            this.txtNumber.setText("");
        }
        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {

        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String tel = this.txtNumber.getText();
            String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?)";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, id);
                pstm.setObject(4, name);
                pstm.setObject(3, address);
                pstm.setObject(2, tel);
                boolean isSaved = pstm.executeUpdate() > 0;
                if (isSaved) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var10) {
                (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
            }

        }

        }

        @FXML
        void btnUpdateOnACtion(ActionEvent event) {
            String eid = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String contactnumber = this.txtNumber.getText();
            String sql = "UPDATE Employee SET name = ?, address = ?, contactnumber = ? WHERE eid = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(4, name);
                pstm.setObject(3, address);
                pstm.setObject(2, contactnumber);
                pstm.setObject(1, eid);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var8) {
                (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
            }

        }


        }

    }*/
package lk.ijse.hardwareManagment.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.ItemDto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colPrice;


    @FXML
    private TableView<Object> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void btnClearOnACtion(ActionEvent event) {
        this.clearFields();

    }

    private void clearFields() {
        this.txtCode.setText("");
        this.txtDescription.setText("");
        this.txtQty.setText("");
        this.txtPrice.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtCode.getText();
        String sql = "DELETE FROM item WHERE code = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "item deleted!", new ButtonType[0])).show();
                loadTableData();
                this.clearFields();
            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        String qty = this.txtQty.getText();
        String price = this.txtPrice.getText();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, code);
            pstm.setObject(2, description);
            pstm.setObject(3, qty);
            pstm.setObject(4, price);
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                (new Alert(Alert.AlertType.CONFIRMATION, "item saved!", new ButtonType[0])).show();
                loadTableData();
                this.clearFields();
            }
        } catch (SQLException var10) {
            (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
        }

    }





    @FXML
    void btnUpdateOnACtion(ActionEvent event) {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        String qty = this.txtQty.getText();
        String price = this.txtPrice.getText();
        String sql = "UPDATE item SET description = ?, qtyOnHand= ?, unitPrice = ? WHERE code = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, code);
            pstm.setObject(2, description);
            pstm.setObject(4, qty);
            pstm.setObject(3, price);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "item updated!", new ButtonType[0])).show();
                loadTableData();
                this.clearFields();
            }
        } catch (SQLException var8) {
            (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadTableData();


    }

    private void loadTableData() {
        ArrayList<Object> item = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from item");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                ItemDto itemDto = new ItemDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getInt(3)


                );
                  item.add(itemDto);

            }
                tblItem.setItems(FXCollections.observableList(item));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    }







