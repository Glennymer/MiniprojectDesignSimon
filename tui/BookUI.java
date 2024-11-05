package tui;
import java.util.Scanner;
import controller.BookController;
import controller.BookItemController;
import model.Book;

public class BookUI
{
    private BookController bc;
    private BookItemController btc;
    public BookUI()
    {
        bc = new BookController();
        btc = new BookItemController();
    }

    public void addBook() {
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;
        String ISBN="", title="", author="";
        int DCnum=0;
        
        while (running) {
            System.out.println("****** Skriv ISBN på ny bog ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            ISBN = keyboard.next();
            if (ISBN.equals("esc")) {
                running = false;
                break;
            }
            if (ISBN.length() == 17) {
                if (bc.searchBookByISBN(ISBN) == null) break;
                else System.out.println("The indtastede ISBN er allerede til stede i databasen. Prøv igen.");
            }
            System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
        }
        
        while (running) {
            System.out.println("****** Skriv Dewey klassifikation på ny bog ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            String DC = keyboard.next();
            if (DC.equals("esc")) {
                running = false;
                break;
            }
            try {
                DCnum = Integer.valueOf(DC);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dewey klassifikation skal være et heltal. Prøv igen.");
            }
            
        }
        
        if (running) {
            System.out.println("****** Skriv titel på ny bog ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            keyboard.nextLine();
            title = keyboard.nextLine();
            if (title.equals("esc")) running = false;
        }
        
        if (running) {
            System.out.println("****** Skriv forfatter på ny bog ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            author = keyboard.nextLine();
            if (author.equals("esc")) running = false;
        }
        
        if (running) {
            Book b = new Book(ISBN, DCnum, title, author);
            bc.addBook(b);
            System.out.println("Tilføjelse af ny bog fuldført.");
        } else {
            System.out.println("Tilføjelse af ny bog annulleret.");
        }
        
        
        
    }
    
    public void readBook() {
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("****** Skriv ISBN på en bog for at hente information ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            String ISBN = keyboard.next();
            if (ISBN.equals("esc")) {
                System.out.println("Informationslæsning af bøger afsluttet");
                break;
            } else {
                if (ISBN.length() == 17) {
                    bc.readBook(ISBN);
                } else System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
            }
        }
    }
    
    public void updateBook() {
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("****** Skriv ISBN på en bog for at opdatere bog ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            String ISBN = keyboard.next();
            if (ISBN.equals("esc")) {
                System.out.println("Opdatering af bog afsluttet");
                break;
            } else {
                if (ISBN.length() == 17) {
                    Book b = bc.searchBookByISBN(ISBN);
                    if (b != null) {
                        bc.readBook(ISBN);
                        boolean running = true;
                        while (running) {
                            System.out.println("****** Opdater bog ******");
                            System.out.println(" (1) Opdater bogens titel");
                            System.out.println(" (2) Opdater bogens forfatter");
                            System.out.println(" (3) Opdater bogens Dewey klassifikation");
                            System.out.println(" (4) Opdater bogens ISBN");
                            
                            System.out.println(" (0) Tilbage");
                            System.out.print("\n Vælg:");
                            int choice = getIntegerFromUser(keyboard);
                            switch (choice) {
                                case 1:
                                    System.out.println("****** Indtast ny titel på bogen ******");
                                    keyboard.nextLine();
                                    b.setTitle(keyboard.nextLine());
                                    
                                    System.out.println("Titel opdateret.");
                                    break;
                                case 2:
                                    System.out.println("****** Indtast ny forfatter på bogen ******");
                                    keyboard.nextLine();
                                    b.setAuthor(keyboard.nextLine());
                                    System.out.println("Forfatter opdateret.");
                                    break;
                                case 3:
                                    System.out.println("****** Indtast ny Dewey klassifikation på bogen ******");
                                    b.setDC(getIntegerFromUser(keyboard));
                                    System.out.println("Dewey klassifikation opdateret.");
                                    break;
                                case 4:
                                    while (true) {
                                        System.out.println("****** Indtast ny ISBN på bogen ******");
                                        System.out.println("****** Skriv 'esc' for at afslutte ******");
                                        String ISBN_new = keyboard.next();
                                        if (!ISBN_new.equals("esc")) {
                                            if (ISBN_new.length() == 17) {
                                                if (bc.searchBookByISBN(ISBN_new) == null) {
                                                    b.setISBN(ISBN_new);
                                                    System.out.println("ISBN opdateret.");
                                                    break;
                                                }
                                                else System.out.println("The indtastede ISBN er allerede til stede i databasen. Prøv igen.");
                                                
                                            } else System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
                                        } else break;
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
                    
                } else System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
            }
        }            
    }
    
    public void deleteBook() {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("****** Skriv ISBN på en bog for at slette bogen fra databasen ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            String ISBN = keyboard.next();
            if (ISBN.equals("esc")) {
                System.out.println("Sletning af bøger afsluttet");
                break;
            } else {
                if (ISBN.length() == 17) {
                    btc.deleteBookItemsByISBNNumber(ISBN);
                    bc.deleteBookByISBNNumber(ISBN);
                    System.out.println("Bog slettet fra databasen.");
                } else System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
            }
            
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
