package controller;
import model.BookItemContainer;
import model.BookItem;

public class BookItemController
{
    
    public BookItemController()
    {
       
    }
    
    public BookItem searchBookItemByBarcode(String barCode) {
        return BookItemContainer.searchBookItemByBarcode(barCode);
    }
    
    public void addBookItem(BookItem bt) {
        BookItemContainer.addBookItem(bt);
    }
    
    public void readBookItem(String barCode) {
        BookItem bt = searchBookItemByBarcode(barCode);
        if (bt != null) {
            System.out.println(bt.toString());
        } else {
            System.out.println("Bogkopi ikke fundet i database.");
        }
    }
    
    public void deleteBookItemByBarCode(String barCode) {
        BookItemContainer.deleteBookItemByBarCode(barCode);
    }
    
    public void deleteBookItemsByISBNNumber(String ISBN) {
        BookItemContainer.deleteBookItemsByISBNNumber(ISBN);
    }

}
