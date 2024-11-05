package tui;
import java.util.Scanner;
import model.Person;
import model.BookItem;
import java.util.*;
import model.Loan;


/**
 * Write a description of class LoanMenu here.
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version 
 */
public class LoanMenu {
    // instance variables
    
    private PersonUI pui;
    private LoanUI lui;
    private BookItemUI btui;

    
    /**
     * Constructor for objects of class LoanMenu
     */
    public LoanMenu() {
        // initialise instance variables
        
        pui = new PersonUI();
        lui = new LoanUI();
        btui = new BookItemUI();
    }

    public void start() {
        loanMenu();
    }

    private void loanMenu() {
        boolean running = true, running2;
        while (running) {
            int choice = writeLoanMenu();
            switch (choice) {
                case 1:
                    running2 = true;
                    while (running2) {
                        int choice2 = writeSearchForPersonMenu();
                        Person p;
                        switch (choice2) {
                            case 1: 
                                p = pui.writeSearchPersonByName();
                                createLoan(p);
                                running2 = false;
                                break;
                            case 2:
                                p = pui.writeSearchPersonByPhone();
                                createLoan(p);
                                running2 = false;
                                break;
                            case 0:
                                running2 = false;
                                break;
                            default:
                              System.out.println("Der er sket en uforklarlig fejl, choice = "+choice);
                              break;
                        }
                    }
                    break;
                case 2:
                    running2 = true;
                    while (running2) {
                        int choice2 = writeSearchForPersonMenu();
                        Person p;
                        switch (choice2) {
                            case 1: 
                                p = pui.writeSearchPersonByName();
                                returnBooks(p);
                                running2 = false;
                                break;
                            case 2:
                                p = pui.writeSearchPersonByPhone();
                                returnBooks(p);
                                running2 = false;
                                break;
                            case 0:
                                running2 = false;
                                break;
                            default:
                              System.out.println("Der er sket en uforklarlig fejl, choice = "+choice);
                              break;
                        }
                    }
                    break;
                case 0:
                  running = false;
                  break;
                default:
                  System.out.println("En uforklarlig fejl er sket med choice = " + choice);
                  break;
            }
        }
    }
    
    private void returnBooks(Person p) {
        Scanner keyboard = new Scanner(System.in);
        String barCode;
        while (true) {
            System.out.println("****** Skriv stregkode på bog som skal afleveres ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            
            barCode = keyboard.next();
            if (barCode.equals("esc")) {
                break;
            }
            BookItem bt = btui.searchBookItemByBarcode(barCode);
            if (bt != null) {
                boolean r = false;
                for (Loan l : lui.getLoans(p)) {
                    r = r || l.deleteBookItem(bt);
                    bt.setAvailable();
                    if (l.isEmpty()) {
                        lui.setLoan(l);
                        lui.deleteLoan();
                    }
                }
                if (r) System.out.println("Bogkopi afleveret.");
                else System.out.println("Bogkopi ikke udlånt til person.");
            } else System.out.println("Bogkopi ikke fundet i database. Prøv igen.");
            
        }
    }
    
    private int writeLoanMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Udlånsmenu ******");
        System.out.println(" (1) Opret lån");
        System.out.println(" (2) Aflever bøger");
        System.out.println(" (0) Tilbage");
        System.out.print("\n Vælg:");
        int choice = getIntegerFromUser(keyboard);
        return choice;
    }
    
    private int writeSearchForPersonMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Søg efter person ******");
        System.out.println(" (1) Navn");
        System.out.println(" (2) Telefonnummer");
        System.out.println(" (0) Tilbage");
        System.out.print("\n Vælg:");
        int choice = getIntegerFromUser(keyboard);
        return choice;
    }
    
    private void createLoan(Person p) {
        if (p != null) {
            lui.createLoan(p);
            while (true) {
                String barCode = btui.writeBookItem();
                
                if (barCode.equals("esc")) {
                    break;
                }
                BookItem bt = btui.searchBookItemByBarcode(barCode);
                if (bt != null) {
                    if (lui.addBookItemToLoan(bt)) {
                        System.out.print("Bogkopi tilføjet til lån.\n");
                    } else {
                        System.out.print("Bogkopi er allerede udlånt og dermed ikke tilføjet til lån.\n");
                    }
                    
                    
                } else {
                    System.out.println("Bogkopi med denne stregkode ikke fundet i database.");
                }
            }
            Scanner keyboard = new Scanner(System.in);
            System.out.println("****** Bekræft lån ******");
            System.out.println(" (1) Bekræft");
            System.out.println(" (0) Annuller");
            System.out.print("\n Vælg:");
            int choice = getIntegerFromUser(keyboard);
            switch (choice) {
                case 1:
                    lui.completeLoan();
                    System.out.println("Lån bekræftet.");
                    break;
                case 0:
                    lui.deleteLoan();
                    System.out.println("Lån annulleret.");
                    break;
                default:
                  System.out.println("Der er sket en uforklarlig fejl, choice = "+choice);
                  break;
            }
            
        } else {
            System.out.println("Person ikke fundet i database.");
        }
    }
    
    private int getIntegerFromUser(Scanner keyboard) {
        while (!keyboard.hasNextInt()) {
            System.out.println("Input skal være et tal - prøv igen");
            keyboard.nextLine();
        }
        return keyboard.nextInt();
    }
}

