public class dataBook {
    private int id;
    private String title;
    private int year;
    private String author;
    private String isbn;
    private String status;

    public dataBook(int id, String title, int year, String author, String isbn, String status) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public dataBook(String title, int year, String author, String isbn, String status){
        this.title = title;
        this.year = year;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getStatus() { return status; }

    public void setTitle(String title){ this.title = title;}
    public void setYear(int year){ this.year = year;}
    public void setAuthor(String author){ this.author = author;}
    public void setIsbn(String isbn){ this.isbn = isbn;}
    public void setStatus(String status){ this.status = status; }
}
