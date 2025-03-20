public class BorrowBook {
    private int idBorrow;
    private String nimNip;
    private String name;
    private int bookId;
    private String bookTitle;
    private String borrowDate;
    private String returnDate;
    private int charge;

    public BorrowBook(int idBorrow, String nimNip, String name, int bookId, String bookTitle, String borrowDate, String returnDate, int charge){
        this.idBorrow = idBorrow;
        this.nimNip = nimNip;
        this.name = name;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.charge = charge;
    }

    public BorrowBook(String nimNip, String name, int bookId, String bookTitle, String borrowDate, String returnDate, int charge){
        this.nimNip = nimNip;
        this.name = name;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.charge = charge;
    }

    public BorrowBook(String nimNip, String name, int bookId, String bookTitle, String borrowDate, String returnDate){
        this.nimNip = nimNip;
        this.name = name;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowBook(String nimNip, String name, int bookId, String bookTitle, String borrowDate){
        this.nimNip = nimNip;
        this.name = name;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
    }

    public BorrowBook(String nimNip, int bookId, String returnDate, int charge){
        this.nimNip = nimNip;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.charge = charge;
    }

    public int getIdBorrow() { return idBorrow; }
    public String getNimNip() { return nimNip; }
    public String getName() { return name; }
    public int getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }
    public int getCharge() { return charge; }

    public void setIdBorrow(int idBorrow) { this.idBorrow = idBorrow; }
    public void setNimNip(String nimNip) { this.nimNip = nimNip; }
    public void setName(String name) { this.name = name; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public void setCharge(int charge) { this.charge = charge; }
}

