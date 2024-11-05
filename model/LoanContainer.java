package model;
import java.util.ArrayList;

public class LoanContainer
{
    private ArrayList<Loan> loans;
    private static LoanContainer instance;
    private int loanNumber;
    
    private LoanContainer()
    {
        loans = new ArrayList<>();
        loanNumber = 0;
    }
    
    private void incrementLoanNumber() {
        loanNumber += 1;
    }

    public static LoanContainer getInstance() {
        if (instance == null) {
            instance = new LoanContainer();
        }
        return instance;
    }
    
    public static Loan createLoan(Person person) {
        Loan loan = new Loan(person);
        loan.setLoanNumber(getInstance().loanNumber);
        getInstance().incrementLoanNumber();
        getInstance().loans.add(loan);
        return loan;
    }
    
    public static void deleteLoanByLoanNumber(int loanNumber) {
        ArrayList<Loan> loans_new = new ArrayList<>();
        for (Loan l : getInstance().loans) if (l.getLoanNumber() != loanNumber)  loans_new.add(l);
        getInstance().loans = loans_new;
    }
    
    public static ArrayList<Loan> getLoans(Person p) {
        ArrayList<Loan> loans_new = new ArrayList<>();
        for (Loan l : getInstance().loans) if (l.getPerson().getName().equals(p.getName()) || l.getPerson().getPhone().equals(p.getPhone())) loans_new.add(l);
        return loans_new;
    }
}