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

public class dataBookController {
    @FXML private Button backButton;
    @FXML private Button viewButton;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;

    @FXML private TableView<dataBook> tableView;
    @FXML private TableColumn<dataBook, Integer> idColumn;
    @FXML private TableColumn<dataBook, String> titleColumn;
    @FXML private TableColumn<dataBook, Integer> yearColumn;
    @FXML private TableColumn<dataBook, String> authorColumn;
    @FXML private TableColumn<dataBook, String> isbnColumn;
    @FXML private TableColumn<dataBook, String> statusColumn;

    @FXML private TextField idField;
    @FXML private TextField titleField;
    @FXML private TextField yearField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private ChoiceBox<String> statusField;
    private String[] category = {"Available", "Unavailable"};

    private ObservableList<dataBook> dataBookList = FXCollections.observableArrayList();

    @FXML
    private void handleAdd() {
        String title = titleField.getText();
        int year = Integer.parseInt(yearField.getText());
        String author = authorField.getText();
        String isbn = isbnField.getText();
        String status = statusField.getValue();
        
        try (Connection conn = Database.connect()) {
            String query = "INSERT INTO book (title, year, author, isbn, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setInt(2, year);
            statement.setString(3, author);
            statement.setString(4, isbn);
            statement.setString(5, status);
            statement.executeUpdate();

            dataBookList.add(new dataBook(title, year, author, isbn, status));
            tableView.refresh();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleView() {
        try (Connection conn = Database.connect()){
            String query = ("SELECT * FROM book");
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            if (!dataBookList.isEmpty()) {
                dataBookList.clear(); // Prevent duplicate entries
            }
    
            // Populate dataBookList with data from ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                String status = resultSet.getString("status");
    
                dataBookList.add(new dataBook(id, title, year, author, isbn, status));
            }

            // Refresh Table View to reflect Changes
            tableView.setItems(dataBookList);
            tableView.refresh();
            System.out.println("Data successfully loaded into the table.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading data from the database.");
        }    
    }

    @FXML
    private void handleEdit() {
        dataBook selectedDataBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedDataBook != null && validateInputs()) {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());   
            String author = authorField.getText();
            String isbn = isbnField.getText();
            String status = statusField.getValue();
            
            try (Connection conn = Database.connect()) {
                String query = "UPDATE book SET title = ?,  year = ?, author = ?, isbn = ?, status = ? WHERE id = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, title);
                statement.setInt(2, year);
                statement.setString(3, author);
                statement.setString(4, isbn);
                statement.setString(5, status);
                statement.setInt(6, id);
                statement.executeUpdate();

                // Update the table view
                selectedDataBook.setTitle(title);
                selectedDataBook.setYear(year);
                selectedDataBook.setAuthor(author);
                selectedDataBook.setIsbn(isbn);
                selectedDataBook.setStatus(status);
                tableView.refresh();

                System.out.println("Book updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No book selected for editing.");
        }
    }

    @FXML
    private void handleDelete() {
        dataBook selecteDataBook = tableView.getSelectionModel().getSelectedItem();
        // int id = Integer.parseInt(idField.getText());
        
        if (selecteDataBook == null) {
            System.out.println("No book selected for deletion");
            return;
        }

        // Konfirmasi penghapusan (opsional)
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to delete this member?");
        confirmationAlert.setContentText("Title: " + selecteDataBook.getTitle() + 
                                        "\nID: " + selecteDataBook.getId());
        if (confirmationAlert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return; // Membatalkan penghapusan jika pengguna tidak mengonfirmasi
        }

        try (Connection conn = Database.connect()) {
            //Query untuk menghapus data berdasarkan ID
            String query = "DELETE FROM book WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, selecteDataBook.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Menghapus data dari ObservableList
                dataBookList.remove(selecteDataBook);
                tableView.refresh();
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Failed to delete the Book. No matching record found.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting book from the database.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookMenu.fxml"));
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
            dataBook selectedBook = tableView.getSelectionModel().getSelectedItem();

            if (selectedBook != null) {
                idField.setText(String.valueOf(selectedBook.getId()));
                titleField.setText(selectedBook.getTitle());
                yearField.setText(String.valueOf(selectedBook.getYear()));
                authorField.setText(selectedBook.getAuthor());
                isbnField.setText(selectedBook.getIsbn());
                statusField.setValue(selectedBook.getStatus());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("No book selected");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error occurred while displaying book data");
            alert.showAndWait();
        }
    }


    public void initialize() {
        // Initialize ChoiceBox
        statusField.getItems().addAll(category);
        statusField.setValue(category[0]);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    

    private boolean validateInputs() {
        if (idField.getText().isEmpty() || titleField.getText().isEmpty() ||
            yearField.getText().isEmpty() || authorField.getText().isEmpty() || isbnField.getText().isEmpty() ||
            statusField.getValue() == null) {
            System.out.println("Please fill all fields!");
            return false;
        }
        return true;
    }
}

