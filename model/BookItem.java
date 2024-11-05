package model;
import java.util.*;
import java.text.SimpleDateFormat;

public class BookItem
{
    private boolean available;
    private Calendar purchaseDate;
    private float purchasePrice;
    private Book book;
    private String barCode;
   
    public BookItem(Book book, Calendar purchaseDate, float purchasePrice, String barCode)
    {
        setBook(book);
        setPurchaseDate(purchaseDate);
        setPurchasePrice(purchasePrice);
        setBarCode(barCode);
        setAvailable();
    }
    
    public String getBarCode() {
        return this.barCode;
    }

    public boolean isAvailable(){
        return available;
    }
    
    public void setUnavailable() {
        this.available = false;
    }
    
    public void setAvailable() {
        this.available = true;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public Book getBook() {
        return this.book;
    }
    
    public void setPurchaseDate(Calendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    
    public String toString() {
        String s = this.book.toString();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        s += "Købsdato: " + format1.format(purchaseDate.getTime()) + "\n";
        s += "Købspris: " + String.valueOf(this.purchasePrice) + "\n";
        s += "Stregkode: " + this.barCode + "\n";
        s += "Udlånt: " + String.valueOf(!available) + "\n";
        return s;
    }
    
    
}
