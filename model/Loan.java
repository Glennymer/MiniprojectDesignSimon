package model;
import java.util.*;

public class Loan
{
    private int loanNumber;
    private Calendar borrowDate;
    private int period;
    private boolean state;
    private Person person;
    
    private ArrayList<BookItem> bookItems;

    public Loan(Person person)
    {
        this.person = person;
        this.borrowDate = Calendar.getInstance();
        this.borrowDate.set(Calendar.HOUR_OF_DAY, 0);
        this.period = 30;
        this.state = false;
        this.bookItems = new ArrayList<>();
    }
    
    public void setLoanNumber(int loanNumber){
        this.loanNumber = loanNumber;
    }
    
    public int getLoanNumber() {
        return this.loanNumber;
    }
    
    public boolean addBookItemToLoan(BookItem bt) {
        if (!state && bt.isAvailable()) {
            boolean isAdded = false;
            for (BookItem bt1 : this.bookItems) if (bt1.getBarCode().equals(bt.getBarCode())) isAdded = true;
            if (isAdded) return false;
            else {
                this.bookItems.add(bt);
                return true;
            }
        }
        return false;
    }
    
    public void completeLoan() {
        for (BookItem bt : this.bookItems) {
            bt.setUnavailable(); 
        }
        this.state = true;
    }
    
    public Person getPerson() {
        return this.person;
    }
    
    public ArrayList<BookItem> getBookItems() {
        return bookItems;
    }
    
    public boolean deleteBookItem(BookItem bt) {
        ArrayList<BookItem> bookitems_new = new ArrayList<>();
        for (BookItem bt1 : this.bookItems) if (!bt1.getBarCode().equals(bt.getBarCode()))  bookitems_new.add(bt1);
        boolean r = this.bookItems.size() != bookitems_new.size();
        this.bookItems = bookitems_new;
        return r;
    }
 
}
