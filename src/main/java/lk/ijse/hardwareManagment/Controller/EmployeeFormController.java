package lk.ijse.hardwareManagment.Controller;




import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.EmployeeDto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private Button BtnClear;

    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button btnBack;


    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<?, ?> colAdresss;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableView<Object> tblEmployee;


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
    void BtnClearOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void BtnSaveOnAction(ActionEvent event) {
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
                loadTableData();

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
    void BtnUpdateOnAction(ActionEvent event) {
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
                loadTableData();

            }
        } catch (SQLException var8) {
            (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
        }

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = this.txtid.getText();
        String sql = "DELETE FROM Employee WHERE eid = ?";

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!", new ButtonType[0])).show();
                this.clearFields();
                loadTableData();

            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNumber.setCellValueFactory(new PropertyValueFactory<>("contactnumber"));
        colId.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colAdresss.setCellValueFactory(new PropertyValueFactory<>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadTableData();


    }

    private void loadTableData() {
        ArrayList<Object> Employee1 = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from Employee");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                EmployeeDto employeeDto = new EmployeeDto(
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(1),
                        resultSet.getInt(2)

                );
                Employee1.add(employeeDto);

            }
            tblEmployee.setItems(FXCollections.observableList(Employee1));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}