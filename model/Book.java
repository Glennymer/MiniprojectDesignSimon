package model;


public class Book
{
    private String ISBN, title, author;
    private int DC;
    public Book(String ISBN, int DC, String title, String author)
    {
        setISBN(ISBN);
        setDC(DC);
        setTitle(title);
        setAuthor(author);
    }
    
    public String getISBN() {
        return this.ISBN;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setDC(int DC) {
        this.DC = DC;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public String toString() {
        String s = "Titel: " + this.title + "\n";
        s += "Forfatter: " + this.author + "\n";
        s += "Dewey klassifikation: " + String.valueOf(this.DC) + "\n";
        s += "ISBN: " + getISBN() + "\n";
        
        return s;
    }
}
