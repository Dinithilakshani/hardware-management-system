package lk.ijse.hardwareManagment.Controller;





import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardwareManagment.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnback;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;
    private AnchorPane root;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Dashboardform.fxml"));
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();


    }



    @FXML
    void btnClearOnAction(ActionEvent event) {this.clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtid.getText();
        String sql = "DELETE FROM Supplier WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
        }

    }



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = this.txtid.getText();
        String name = this.txtname.getText();
        String address = this.txtAddress.getText();
        String tel = this.txtNumber.getText();
        String sql = "INSERT INTO Supplier VALUES(?, ?, ?, ?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            pstm.setObject(4, tel);
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var10) {
            (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
        }

    }

    private void clearFields() {
        this.txtid.setText("");
        this.txtname.setText("");
        this.txtAddress.setText("");
        this.txtNumber.setText("");
    }





    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = this.txtid.getText();
        String sql = "SELECT * FROM Supplier WHERE id = ?";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String tel = resultSet.getString(4);
                this.txtname.setText(name);
                this.txtAddress.setText(address);
                this.txtNumber.setText(tel);
            } else {
                (new Alert(Alert.AlertType.INFORMATION, "Supplier id can't be find!", new ButtonType[0])).show();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String eid = this.txtid.getText();
        String name = this.txtname.getText();
        String address = this.txtAddress.getText();
        String contactnumber = this.txtNumber.getText();
        String sql = "UPDATE Supplier SET name = ?, address = ?, contactnumber = ? WHERE eid = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, name);
            pstm.setObject(2, address);
            pstm.setObject(3, contactnumber);
            pstm.setObject(4, eid);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var8) {
            (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
        }

    }

    }





