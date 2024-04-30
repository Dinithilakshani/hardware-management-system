package lk.ijse.hardwareManagment.Controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;



    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableView<Object> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String sql = "DELETE FROM Customer WHERE id = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
        }


    }





    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = this.txtId.getText();
        String name = this.txtName.getText();
        String address = this.txtAddress.getText();
        String tel = this.txtNumber.getText();
        String email = this.txtEmail.getText();
        String sql = "INSERT INTO Customer VALUES(?, ?, ?, ?,?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            pstm.setObject(5, tel);
            pstm.setObject(4, email);
            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!", new ButtonType[0])).show();
                this.clearFields();
            }
        } catch (SQLException var10) {
            (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
        }

}


            private void clearFields () {
                this.txtId.setText("");
                this.txtName.setText("");
                this.txtAddress.setText("");
                this.txtNumber.setText("");
                this.txtEmail.setText("");
            }




        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String eid = this.txtId.getText();
            String name = this.txtName.getText();
            String address = this.txtAddress.getText();
            String contactnumber = this.txtNumber.getText();
            String email = this.txtEmail.getText();
            String sql = "UPDATE Customer SET name = ?, address = ?,email = ? , contactnumber = ? WHERE id = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(2, name);
                pstm.setObject(3, address);
                pstm.setObject(4, contactnumber);
                pstm.setObject(1, eid);
                pstm.setObject(5,email);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var8) {
                (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
            }

        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNum.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));



        loadTableData();


    }

    private void loadTableData() {
        ArrayList<Object> Customer1 = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {


               CustomerDto customerDto   = new CustomerDto(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getInt(5),
                        resultSet.getString(4)


                );
                Customer1.add(customerDto);

            }
            tblCustomer.setItems(FXCollections.observableList(Customer1));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //soutsss
}





