import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class MemberController {
    @FXML private Button editButton;
    @FXML private Button backButton;
    @FXML private Button addButton;
    @FXML private Button viewButton;
    @FXML private Button deleteButton;

    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> nimNipColumn;
    @FXML private TableColumn<Member, String> majorColumn;
    @FXML private TableColumn<Member, String> phoneColumn;
    @FXML private TableColumn<Member, String> statusColumn;

    @FXML private TextField nameField;
    @FXML private TextField nimNipField;
    @FXML private TextField majorField;
    @FXML private TextField phoneField;
    @FXML private ChoiceBox<String> statusField;
    private String[] category = {"Mahasiswa", "Dosen"}; 

    private ObservableList<Member> memberList = FXCollections.observableArrayList();

    @FXML
    private void handleAdd() {
        if (!validateInputs()) return;

        String name = nameField.getText();
        String nimNip = nimNipField.getText();
        String major = majorField.getText();
        String phone = phoneField.getText();
        String status = statusField.getValue();

        try (Connection conn = Database.connect()) {
            String query = "INSERT INTO member (nimNip, name, major, phone, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nimNip);
            statement.setString(2, name);
            statement.setString(3, major);
            statement.setString(4, phone);
            statement.setString(5, status);
            statement.executeUpdate();

            memberList.add(new Member(nimNip, name, major, phone, status));
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEdit() {
        Member selectedMember = tableView.getSelectionModel().getSelectedItem();
        if (selectedMember != null && validateInputs()) {
            String name = nameField.getText();
            String nimNip = nimNipField.getText();
            String major = majorField.getText();
            String phone = phoneField.getText();
            String status = statusField.getValue();

            try (Connection conn = Database.connect()) {
                String query = "UPDATE member SET name = ?, major = ?, phone = ?, status = ? WHERE nimNip = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, major);
                statement.setString(3, phone);
                statement.setString(4, status);
                statement.setString(5, nimNip);
                statement.executeUpdate();

                // Update the table view
                selectedMember.setName(name);
                selectedMember.setMajor(major);
                selectedMember.setPhone(phone);
                selectedMember.setStatus(status);
                tableView.refresh();

                System.out.println("Member updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No member selected for editing.");
        }
    }  

    @FXML
    private void handleView() {
        try (Connection conn = Database.connect()){
            String query = ("SELECT * FROM member");
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            if (!memberList.isEmpty()) {
                memberList.clear(); // Prevent duplicate entries
            }
    
            // Populate memberList with data from ResultSet
            while (resultSet.next()) {
                String nimNip = resultSet.getString("nimNip");
                String name = resultSet.getString("name");
                String major = resultSet.getString("major");
                String phone = resultSet.getString("phone");
                String status = resultSet.getString("status");
    
                memberList.add(new Member(nimNip, name, major, phone, status));
            }

            // Refresh Table View to reflect Changes
            tableView.setItems(memberList);
            tableView.refresh();
            System.out.println("Data successfully loaded into the table.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading data from the database.");
        }    
    }

    @FXML
    private void handleDelete() {
        // Mendapatkan member yang dipilih di TableView
        Member selectedMember = tableView.getSelectionModel().getSelectedItem();

        if (selectedMember == null) {
            System.out.println("No member selected for deletion.");
            return;
        }

        // Konfirmasi penghapusan (opsional)
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to delete this member?");
        confirmationAlert.setContentText("Name: " + selectedMember.getName() + 
                                        "\nNIM/NIP: " + selectedMember.getNimNip());
        if (confirmationAlert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return; // Membatalkan penghapusan jika pengguna tidak mengonfirmasi
        }

        try (Connection conn = Database.connect()) {
            // Query untuk menghapus data berdasarkan NIM/NIP
            String query = "DELETE FROM member WHERE nimNip = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, selectedMember.getNimNip());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Menghapus data dari ObservableList
                memberList.remove(selectedMember);
                tableView.refresh();
                System.out.println("Member deleted successfully.");
            } else {
                System.out.println("Failed to delete the member. No matching record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting member from the database.");
        }
    }

    
    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home2.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showData(MouseEvent event) {
        try {
            Member selectedMember = tableView.getSelectionModel().getSelectedItem();

            if (selectedMember != null) {
                nameField.setText(selectedMember.getName());
                nimNipField.setText(selectedMember.getNimNip());
                majorField.setText(selectedMember.getMajor());
                phoneField.setText(selectedMember.getPhone());
                statusField.setValue(selectedMember.getStatus());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("No member selected");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error occurred while displaying member data");
            alert.showAndWait();
        }
    }


    public void initialize() {
        // Initialize ChoiceBox
        statusField.getItems().addAll(category);
        statusField.setValue(category[0]);

        nimNipColumn.setCellValueFactory(new PropertyValueFactory<>("nimNip"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.setItems(memberList);
    }

    private boolean validateInputs() {
        if (nameField.getText().isEmpty() || nimNipField.getText().isEmpty() ||
            majorField.getText().isEmpty() || phoneField.getText().isEmpty() ||
            statusField.getValue() == null) {
            System.out.println("Please fill all fields!");
            return false;
        }
        return true;
    }
}
