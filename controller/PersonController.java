package controller;
import model.PersonContainer;
import model.Person;
import model.LoanContainer;
import model.Loan;

public class PersonController
{
    
    public PersonController()
    {
        
    }
    
    public Person searchPersonByName(String name) {
        return PersonContainer.searchPersonByName(name);
    }
    
    public Person searchPersonByPhone(String phone) {
        return PersonContainer.searchPersonByPhone(phone);
    }
  
    public Loan createLoan(Person p) {
        return LoanContainer.createLoan(p);
    }
    
    public void deletePerson(Person p) {
        PersonContainer.deletePerson(p);
    }
    
    public void addPerson(Person p) {
        PersonContainer.addPerson(p);
    }
}
