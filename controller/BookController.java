package controller;

import model.BookContainer;
import model.Book;

public class BookController
{
    
    public BookController()
    {
       
    }
    
    public void addBook(Book b) {
        BookContainer.addBook(b);
    }
    
    public void readBook(String ISBN) {
        
        Book b = searchBookByISBN(ISBN);
        if (b != null) {
            System.out.println(b.toString());
        } else {
            System.out.println("Bog ikke fundet i database.");
        }
        
    }
    
    public Book searchBookByISBN(String ISBN) {
        return BookContainer.searchBookByISBN(ISBN);
    }
    
    public void deleteBookByISBNNumber(String ISBN) {
        BookContainer.deleteBookByISBNNumber(ISBN);
    }

 
}
