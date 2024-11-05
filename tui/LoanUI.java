package tui;
import model.Person;
import model.Loan;
import controller.LoanController;
import model.BookItem;
import java.util.*;

public class LoanUI
{
    // instansvariabler - erstat eksemplet herunder med dine egne variabler
    private LoanController lc;
    public LoanUI()
    {
         lc = new LoanController();
    }

    public void createLoan(Person p) {
        lc.createLoan(p);
    }
    
    public boolean addBookItemToLoan(BookItem bt) {
        return lc.addBookItemToLoan(bt);
    }
    
    public void completeLoan() {
        lc.completeLoan();
    }
    
    public void deleteLoan() {
        lc.deleteLoan();
    }
    
    public ArrayList<Loan> getLoans(Person p) {
        return lc.getLoans(p);
    }
    
    public void setLoan(Loan l) {
        lc.setLoan(l);
    }
}
