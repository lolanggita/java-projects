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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BorrowBookController {
    @FXML private Button confirmButton;
    @FXML private Button addButton;
    @FXML private Button viewButton;
    @FXML private Button backButton;

    @FXML private TextField nimNipField;
    @FXML private TextField nameField;
    @FXML private TextField bookIdBorrowField;
    @FXML private TextField bookTitleField;
    @FXML private DatePicker borrowDateField;
    @FXML private TextField nimNipReturnField;
    @FXML private TextField bookIdReturnField;
    @FXML private DatePicker returnDateField;

    @FXML private TableView<BorrowBook> tableView;
    @FXML private TableColumn<BorrowBook, Integer> idBorrowColumn;
    @FXML private TableColumn<BorrowBook, String> nimNipColumn;
    @FXML private TableColumn<BorrowBook, String> nameColumn;
    @FXML private TableColumn<BorrowBook, Integer> bookIDColumn;
    @FXML private TableColumn<BorrowBook, String> bookTitleColumn;
    @FXML private TableColumn<BorrowBook, String> borrowDateColumn;
    @FXML private TableColumn<BorrowBook, String> returnDateColumn;
    @FXML private TableColumn<BorrowBook, Integer> chargeColumn;

    private ObservableList<BorrowBook> borrowList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idBorrowColumn.setCellValueFactory(new PropertyValueFactory<>("idBorrow"));
        nimNipColumn.setCellValueFactory(new PropertyValueFactory<>("nimNip"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        chargeColumn.setCellValueFactory(new PropertyValueFactory<>("charge"));

        // menambahkan listener ke TableView untuk memantau data setiap kali diperbarui
        bookIDColumn.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        System.out.println("bookIdColumn mendapatkan nilai: " + item);
                        setText(item.toString());
                    }
                }
            };
        });

        tableView.setItems(borrowList);
    }

    @FXML
    private void handleBorrow() {
        try(Connection conn = Database.connect()) {
            String nimNip = nimNipField.getText();
            String name = nameField.getText();
            int bookId = Integer.parseInt(bookIdBorrowField.getText());
            String bookTitle = bookTitleField.getText();
            // Mengambil tanggal dari DatePicker dan ubah ke format String
            LocalDate borrowDateLocal = borrowDateField.getValue(); // Pastikan DatePicker digunakan
            if (borrowDateLocal == null) {
                throw new IllegalArgumentException("The date of borrowing must be filled in.");
            }
            String borrowDate = borrowDateLocal.toString(); // Format ke "YYYY-MM-DD"

            String query = "INSERT INTO borrow (nimNip, name, bookId, bookTitle, borrowDate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nimNip);
            statement.setString(2, name);
            statement.setInt(3, bookId);
            statement.setString(4, bookTitle);
            statement.setString(5, borrowDate);
            statement.executeUpdate();
            clearFields();

            // Validate and create BorrowBook object
            BorrowBook borrowBook  = new BorrowBook(nimNip, name, bookId, bookTitle, borrowDate);
            borrowList.add(borrowBook);
            // clearFields();
        } catch (SQLException e) {
            showAlert("Error", "Invalid input. Please check your data.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleReturn() {
        String nimNip = nimNipReturnField.getText();
        String bookId = bookIdReturnField.getText();
        LocalDate returnDateLocal = returnDateField.getValue(); // Pastikan DatePicker digunakan

        if (nimNip.isEmpty() || bookId.isEmpty() || returnDateLocal == null) {
            showAlert("Error", "All fields must be filled.", Alert.AlertType.ERROR);
            return;
        }

        String returnDate = returnDateLocal.toString(); // Format ke "YYYY-MM-DD"

        try (Connection conn = Database.connect()) {
            // Cek apakah nimNip dan bookId ada dalam tabel borrow
            String selectQuery = "SELECT borrowDate FROM borrow WHERE nimNip = ? AND bookId = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setString(1, nimNip);
            selectStmt.setString(2, bookId);

            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                // Ambil tanggal pinjaman
                String borrowDate = rs.getString("borrowDate");

                // Hitung denda
                int charge = CalculationFines(borrowDate, returnDate);

                // Update returnDate dan fine di database
                String updateQuery = "UPDATE borrow SET returnDate = ?, charge = ? WHERE nimNip = ? AND bookId = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, returnDate);
                updateStmt.setString(2, String.valueOf(charge)); // Simpan sebagai TEXT
                updateStmt.setString(3, nimNip);
                updateStmt.setString(4, bookId);
                updateStmt.executeUpdate();
                clearFields();

                // Tampilkan hasil sukses dan informasi denda
                showAlert("Success", "Book return processed successfully.\nFine: " + charge, Alert.AlertType.INFORMATION);

                // Bersihkan field setelah berhasil
            } else {
                // Jika tidak ditemukan
                showAlert("Error", "Matching borrow record not found.", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            showAlert("Error", "Invalid input. Please check your data.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private int CalculationFines(String borrowDate, String returnDate) {
        try {
            // Konversi string tanggal menjadi LocalDate
            LocalDate borrowLocalDate = LocalDate.parse(borrowDate);
            LocalDate returnLocalDate = LocalDate.parse(returnDate);

            // Hitung selisih hari antara tanggal pinjaman dan pengembalian
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(borrowLocalDate, returnLocalDate);

            // Debugging: Periksa nilai borrowDate, returnDate, dan daysBetween
            System.out.println("Borrow Date: " + borrowDate);
            System.out.println("Return Date: " + returnDate);
            System.out.println("Days Between: " + daysBetween);

            // Jika selisih hari kurang dari atau sama dengan 7, tidak ada denda
            if (daysBetween <= 7) {
                return 0;
            }

            // Jika lebih dari 7 hari, hitung denda
            long overdueDays = daysBetween - 7;
            int charge = 10000 + (int) overdueDays * 1000; // Denda dasar 10.000 + 1.000 per hari setelah 7 hari

            return charge;
        } catch (Exception e) {
            // Jika ada error dalam parsing tanggal, kembalikan 0 sebagai fallback
            e.printStackTrace();
            return 0;
        }
    }

    @FXML
    private void handleView() {
        try (Connection conn = Database.connect()){
            String query = ("SELECT * FROM borrow");
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            if (!borrowList.isEmpty()) {
                borrowList.clear(); // Prevent duplicate entries
            }
    
            // Populate BorrowList with data from ResultSet
            while (resultSet.next()) {
                int idBorrow = resultSet.getInt("idBorrow");
                String nimNip = resultSet.getString("nimNip");
                String name = resultSet.getString("name");
                int bookId = resultSet.getInt("bookId");
                String bookTitle = resultSet.getString("bookTitle");
                String borrowDate = resultSet.getString("borrowDate");
                String returnDate = resultSet.getString("returnDate");
                int charge = resultSet.getInt("charge");

                // Logging untuk debugging
                System.out.println("Data dari ResultSet: " +
                "ID Borrow = " + idBorrow + 
                ", Book ID = " + bookId + 
                ", Name = " + name + 
                ", Book Title = " + bookTitle);
                
                // ObservableList
                BorrowBook borrowBook = new BorrowBook(idBorrow, nimNip, name, bookId, bookTitle, borrowDate, returnDate, charge);
                borrowList.add(borrowBook);
                clearFields();
            }

            // Refresh Table View to reflect Changes
            tableView.setItems(borrowList);
            tableView.refresh();
            System.out.println("Data successfully loaded into the table.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load data from the database.", Alert.AlertType.ERROR);
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
            BorrowBook selectedBorrow = tableView.getSelectionModel().getSelectedItem();

            if (selectedBorrow != null) {
                nimNipField.setText(selectedBorrow.getNimNip());
                nimNipReturnField.setText(selectedBorrow.getNimNip());
                nameField.setText(selectedBorrow.getName());
                bookIdBorrowField.setText(String.valueOf(selectedBorrow.getIdBorrow()));
                bookIdReturnField.setText(String.valueOf(selectedBorrow.getIdBorrow()));
                bookTitleField.setText(selectedBorrow.getBookTitle());
                borrowDateField.setValue(LocalDate.parse(selectedBorrow.getBorrowDate()));
                returnDateField.setValue(LocalDate.parse(selectedBorrow.getReturnDate()));
            } else { 
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("No borrow record selected");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error occurred while displaying borrow data");
            alert.showAndWait();
        }
    }

    @FXML
    private void clearFields() {
        nimNipField.clear();
        nameField.clear();
        bookIdBorrowField.clear();
        bookTitleField.clear();
        borrowDateField.setValue(null);
        nimNipReturnField.clear();
        bookIdReturnField.clear();
        returnDateField.setValue(null);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
