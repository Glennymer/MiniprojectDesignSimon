package tui;
import java.util.Scanner;
import model.Person;
import model.Loan;
import model.BookItem;

public class PersonMenu
{
    private PersonUI pui;
    private LoanUI lui;
    
    public PersonMenu()
    {
        pui = new PersonUI();
        lui = new LoanUI();
    }
    
    public void start() {
        personMenu();
    }

    private void personMenu() {
        boolean running = true, running2;
        while (running) {
            int choice = writePersonMenu();
            switch (choice) {
                case 1:
                    pui.addPerson();
                    break;
                case 2:
                    running2 = true;
                    while (running2) {
                        int choice2 = writeSearchForPersonMenu();
                        Person p;
                        switch (choice2) {
                            case 1: 
                                p = pui.writeSearchPersonByName();
                                
                                printPerson(p);
                                running2 = false;
                                break;
                            case 2:
                                p = pui.writeSearchPersonByPhone();
                                printPerson(p);
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
                case 3:
                    running2 = true;
                    while (running2) {
                        int choice2 = writeSearchForPersonMenu();
                        Person p;
                        switch (choice2) {
                            case 1: 
                                p = pui.writeSearchPersonByName();
                                pui.updatePerson(p);
                                running2 = false;
                                break;
                            case 2:
                                p = pui.writeSearchPersonByPhone();
                                pui.updatePerson(p);
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
                case 4:
                    running2 = true;
                    while (running2) {
                        int choice2 = writeSearchForPersonMenu();
                        Person p;
                        switch (choice2) {
                            case 1: 
                                p = pui.writeSearchPersonByName();
                                deletePerson(p);
                                running2 = false;
                                break;
                            case 2:
                                p = pui.writeSearchPersonByPhone();
                                deletePerson(p);
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
                    System.out.println("Der er sket en uforklarlig fejl, choice = "+choice);
                    break;
            }
        }
    }
    
    private void deletePerson(Person p) {
        if (p != null) {
            System.out.println(lui.getLoans(p).size());
            if (lui.getLoans(p).size() == 0) {
                pui.deletePerson(p);
                System.out.println("Person slettet fra database.");
            }
            else System.out.println("Person har stadig aktive lån og kan ikke slettes.");
        } else System.out.println("Person ikke fundet i database.");
    }
    
    private void printPerson(Person p) {
        if (p != null) {
            System.out.println(p.toString());
            System.out.println("Udlånte bøger: ");
            for (Loan l : lui.getLoans(p)) {
                for (BookItem bt : l.getBookItems()) {
                    System.out.println(bt.toString());
                    System.out.println();
                }
            }
        } else System.out.println("Person ikke fundet i database.");
    }
    
    
    private int writePersonMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Udlånsmenu ******");
        System.out.println(" (1) Tilføj person");
        System.out.println(" (2) Læs information om person");
        System.out.println(" (3) Opdater person");
        System.out.println(" (4) Slet person");

        
        System.out.println(" (0) Tilbage");
        System.out.print("\n Vælg:");
        int choice = getIntegerFromUser(keyboard);
        return choice;
    }
    
    private int getIntegerFromUser(Scanner keyboard) {
        while (!keyboard.hasNextInt()) {
            System.out.println("Input skal være et tal - prøv igen");
            keyboard.nextLine();
        }
        return keyboard.nextInt();
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
    
}
