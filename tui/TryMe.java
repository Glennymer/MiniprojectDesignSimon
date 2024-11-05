package tui;

import model.PersonContainer;
import model.BookItemContainer;
import model.BookContainer;
import model.Person;
import model.BookItem;
import model.Book;
import java.util.Calendar;

public class TryMe
{
    
    public static void populate() {
        
        Person p = new Person("Martin Hedegaard", "Æblegaardsvej 120", "3932", "Valving", "48484848");
        PersonContainer.addPerson(p);
        Book b = new Book("978-3-16-148410-0", 123, "Hjulets Historie", "Walter Jensen");
        BookContainer.addBook(b);
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2024);
        date.set(Calendar.MONTH, Calendar.MARCH); 
        date.set(Calendar.DAY_OF_MONTH, 17);
        BookItem bt = new BookItem(b, date, 239, "34");
        BookItemContainer.addBookItem(bt);
    }
    
    
    
}
