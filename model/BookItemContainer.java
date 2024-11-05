package model;
import java.util.ArrayList;

public class BookItemContainer
{
    private ArrayList<BookItem> bookItems;
    private static BookItemContainer instance;
    private BookItemContainer()
    {
        bookItems = new ArrayList<>();
    }

    public static BookItemContainer getInstance() {
        if (instance == null) {
            instance = new BookItemContainer();
        }
        return instance;
    }
    
    public static BookItem searchBookItemByBarcode(String barCode) {
        BookItem foundBookItem = null;
        for(BookItem p : getInstance().bookItems) if(p.getBarCode().equals(barCode)) foundBookItem = p; 
        return foundBookItem;
    }
    
    public static void addBookItem(BookItem bt) {
        getInstance().bookItems.add(bt);
    }
    
    
    public static void deleteBookItemByBarCode(String barCode) {
        ArrayList<BookItem> bookItems_new = new ArrayList<>();
        for (BookItem bt : getInstance().bookItems) if (!bt.getBarCode().equals(barCode))  bookItems_new.add(bt);
        getInstance().bookItems = bookItems_new;
    }
    
    public static void deleteBookItemsByISBNNumber(String ISBN) {
        ArrayList<BookItem> bookItems_new = new ArrayList<>();
        for (BookItem bt : getInstance().bookItems) if (!bt.getBook().getISBN().equals(ISBN))  bookItems_new.add(bt);
        getInstance().bookItems = bookItems_new;
    }
}
