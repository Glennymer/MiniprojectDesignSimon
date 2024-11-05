package model;
import java.util.ArrayList;

public class BookContainer
{
    private ArrayList<Book> books;
    private static BookContainer instance;
    private BookContainer()
    {
        books = new ArrayList<>();
    }

    public static BookContainer getInstance() {
        if (instance == null) {
            instance = new BookContainer();
        }
        return instance;
    }
    
    public static void addBook(Book b){
        getInstance().books.add(b);
    }
    
    public static Book searchBookByISBN(String ISBN) {
        Book foundBook = null;
        for(Book b : getInstance().books) if(b.getISBN().equals(ISBN)) foundBook = b; 
        return foundBook;
    }
    
    public static void deleteBookByISBNNumber(String ISBN) {
        ArrayList<Book> books_new = new ArrayList<>();
        for (Book b : getInstance().books) if (!b.getISBN().equals(ISBN))  books_new.add(b);
        getInstance().books = books_new;
    }
    
}
