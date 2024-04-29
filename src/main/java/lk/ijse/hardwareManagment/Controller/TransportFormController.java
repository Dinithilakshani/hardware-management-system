package lk.ijse.hardwareManagment.Controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardwareManagment.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransportFormController {

        @FXML
        private Button btnBack;

        @FXML
        private Button btnClier;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TextField texTime;

        @FXML
        private TextField textId;

        @FXML
        private TextField txtArea;

        @FXML
        private TextField txtQty;
    private AnchorPane root;

    @FXML
        void BtnSaveOnAction(ActionEvent event) {
            String id = this.textId.getText();
            String area = this.txtArea.getText();
            String qty= this.txtQty.getText();
            String time = this.texTime.getText();
            String sql = "INSERT INTO Transport VALUES(?, ?, ?, ?)";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, id);
                pstm.setObject(2, area);
                pstm.setObject(3, qty);
                pstm.setObject(4,time );
                boolean isSaved = pstm.executeUpdate() > 0;
                if (isSaved) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Transport saved!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var10) {
                (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
            }

        }





        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Dashboardform.fxml"));
                Stage stage = (Stage)this.root.getScene().getWindow();
                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard Form");
                stage.centerOnScreen();

        }

        @FXML
        void btnClierOnAction(ActionEvent event) {this.clearFields();

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String id = this.textId.getText();
            String sql = "DELETE FROM Ttansport WHERE id = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(1, id);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Transport deleted!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var5) {
                (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
            }

        }

    private void clearFields() {
        this.textId.setText("");
        this.txtQty.setText("");
        this.txtArea.setText("");
        this.texTime.setText("");
    }






        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String id = this.textId.getText();
            String qty = this.txtQty.getText();
            String area= this.txtArea.getText();
            String time= this.texTime.getText();
            String sql = "UPDATE Transport SET time = ?, Qty = ?, area = ? WHERE id = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(1, id);
                pstm.setObject(2, qty);
                pstm.setObject(3, area);
                pstm.setObject(4, time);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Transport updated!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var8) {
                (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
            }

        }

        }




