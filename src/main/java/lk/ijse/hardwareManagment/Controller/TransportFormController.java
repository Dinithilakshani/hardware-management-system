package lk.ijse.hardwareManagment.Controller;



import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.TransportDeto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransportFormController  implements Initializable {

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
    private TableColumn<?, ?> colArea;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<Object> tblTransport;


    @FXML
        private TextField texTime;

        @FXML
        private TextField textId;

        @FXML
        private TextField txtArea;


    private AnchorPane root;

    @FXML
        void BtnSaveOnAction(ActionEvent event) {
            String id = this.textId.getText();
            String area = this.txtArea.getText();

            String time = this.texTime.getText();
            String sql = "INSERT INTO transportDetails VALUES(?, ?, ?, ?)";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(3, id);
                pstm.setObject(1, area);

                pstm.setObject(2,time );
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
        void btnClierOnAction(ActionEvent event) {this.clearFields();

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String id = this.textId.getText();
            String sql = "DELETE FROM tansportDetails WHERE T_id = ?";

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

        this.txtArea.setText("");
        this.texTime.setText("");
    }






        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String id = this.textId.getText();

            String area= this.txtArea.getText();
            String time= this.texTime.getText();
            String sql = "UPDATE TransportDetails SET t_time = ?, T_area = ? WHERE T_id = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(3, id);

                pstm.setObject(1, area);
                pstm.setObject(2, time);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Transport updated!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var8) {
                (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
            }

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colArea.setCellValueFactory(new PropertyValueFactory<>("T_area"));
        colId.setCellValueFactory(new PropertyValueFactory<>("T_id"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("t_time"));

        loadTableData();

    }

    private void loadTableData() {
        ArrayList<Object> Transport1 = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from transportDetails");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                TransportDeto transportDeto = new TransportDeto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)

                );
                Transport1.add(transportDeto);

            }
            tblTransport.setItems(FXCollections.observableList(Transport1));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}




