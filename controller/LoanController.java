package controller;
import model.LoanContainer;
import model.Person;
import model.Loan;
import model.BookItem;
import java.util.*;

public class LoanController
{
    private Loan loan;
    
    public LoanController()
    {
        
    }

    public void createLoan(Person p) {
        loan = LoanContainer.createLoan(p);
    }
    
    public boolean addBookItemToLoan(BookItem bt) {
        return loan.addBookItemToLoan(bt);
    }
    
    public void completeLoan() {
        loan.completeLoan();
    }
    
    public void deleteLoan() {
        LoanContainer.deleteLoanByLoanNumber(loan.getLoanNumber());
    }
    
    public ArrayList<Loan> getLoans(Person p) {
        return LoanContainer.getLoans(p);
    }
    
}
