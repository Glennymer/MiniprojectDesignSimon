package tui;
import java.util.Scanner;
import model.BookItem;
import model.Book;
import controller.BookItemController;
import controller.BookController;
import java.util.Calendar;

public class BookItemUI
{
    private BookItemController btc;
    private BookController bc;
    
    public BookItemUI()
    {
        btc = new BookItemController();
        bc = new BookController();
    }
    
    public String writeBookItem() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Tilføj bog til lån ved stregkode ******");
        System.out.println("****** Skriv 'esc' for at afslutte med at tilføje ******");
        return keyboard.next();
    }
    
    public BookItem searchBookItemByBarcode(String barCode) {
        return btc.searchBookItemByBarcode(barCode);
    }
    

    public void addBookItem() {
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;
        String barCode = "", ISBN = "";
        Calendar date = Calendar.getInstance();
        float purchasePrice = 0;
        Book book = null;
        
        
        while (running) {
            System.out.println("****** Skriv ISBN på bog af ny bogkopi ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            ISBN = keyboard.next();
            if (ISBN.equals("esc")) {
                running = false;
                break;
            }
            if (ISBN.length() == 17) {
                book = bc.searchBookByISBN(ISBN);
                if (book != null) break;
                else System.out.println("Bog ikke fundet i  database. Prøv igen.");
            }
            System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
        }
        
        while (running) {
            System.out.println("****** Skriv købsdato for ny bogkopi ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            String datestring = keyboard.next();
            if (datestring.equals("esc")) {
                running = false;
                break;
            }
            try {
                String[] s = datestring.split("\\-", 0);
                date.set(Calendar.DAY_OF_MONTH, Integer.valueOf(s[0]));
                date.set(Calendar.MONTH, Integer.valueOf(s[1]) - 1); 
                date.set(Calendar.YEAR, Integer.valueOf(s[2]));
                
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dag, måned og år skal være et heltal. Prøv igen.");
            }
            
        }
        
        while (running) {
            System.out.println("****** Skriv købspris på ny bogkopi ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            String purchasePriceString = keyboard.next();
            if (purchasePriceString.equals("esc")) {
                running = false;
                break;
            }
            try {
                purchasePrice = Integer.valueOf(purchasePriceString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Købspris skal være et heltal. Prøv igen.");
            }
            
        }
        
        
        while (running) {
            System.out.println("****** Skriv stregkode på ny bogkopi ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            barCode = keyboard.next();
            if (barCode.equals("esc")) {
                running = false;
                break;
            } 
            if (btc.searchBookItemByBarcode(barCode) == null) {
                break;
            } else System.out.println("Stregkode allerede til stede i databasen. Prøv igen.");
        }
        
        if (running) {
            BookItem bt = new BookItem(book, date, purchasePrice, barCode);
            btc.addBookItem(bt);
            System.out.println("Tilføjelse af ny bogkopi fuldført.");
        } else {
            System.out.println("Tilføjelse af ny bogkopi annulleret.");
        }
        
        
        
    }
    
    
    public void readBookItem() {
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("****** Skriv stregkode på et bogkopi for at hente information ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            String barCode = keyboard.next();
            if (barCode.equals("esc")) {
                System.out.println("Informationslæsning af bogkopier afsluttet");
                break;
            } else {
                btc.readBookItem(barCode);
                
            }
        }
    }
    
    public void updateBookItem() {
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("****** Skriv stregkode på et bogkopi for at opdatere bogkopi ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            String barCode = keyboard.next();
            if (barCode.equals("esc")) {
                System.out.println("Opdatering af bog afsluttet");
                break;
            } else {
            
                BookItem bt = btc.searchBookItemByBarcode(barCode);
                if (bt != null) {
                    btc.readBookItem(barCode);
                    boolean running = true;
                    while (running) {
                        System.out.println("****** Opdater bogkopi ******");
                        System.out.println(" (1) Opdater bogenkopiets bog");
                        System.out.println(" (2) Opdater bogenkopiets købsdato");
                        System.out.println(" (3) Opdater bogenkopiets købspris");
                        System.out.println(" (4) Opdater bogenkopiets stregkode");
                        
                        System.out.println(" (0) Tilbage");
                        System.out.print("\n Vælg:");
                        int choice = getIntegerFromUser(keyboard);
                        switch (choice) {
                            case 1:
                                String ISBN;
                                while (true) {
                                    System.out.println("****** Indtast ISBN på ny bog af bogkopi ******");
                                    System.out.println("****** Skriv 'esc' for at annullere ******");
                                    ISBN = keyboard.next();
                                    if (ISBN.equals("esc")) break;
                                    if (ISBN.length() == 17) {
                                         Book b = bc.searchBookByISBN(ISBN);
                                         if (b != null) {
                                             bt.setBook(b);
                                             System.out.println("Bogkopi opdateret med bog.");
                                             break;
                                         } else System.out.println("Bog ikke fundet i database. Prøv igen.");
                                         
                                    };
                                    System.out.println("Det indtastede ISBN er i forkert format. Prøv igen.");
                                }
                                
                                
                                break;
                            case 2:
                                ;
                                Calendar date = Calendar.getInstance();
                                while (true) {
                                    System.out.println("****** Indtast ny købsdato på bogkopi ******");
                                    String datestring = keyboard.next();
                                    try {
                                        String[] s = datestring.split("\\-", 0);
                                        date.set(Calendar.DAY_OF_MONTH, Integer.valueOf(s[0]));
                                        date.set(Calendar.MONTH, Integer.valueOf(s[1])); 
                                        date.set(Calendar.YEAR, Integer.valueOf(s[2]));
                                        bt.setPurchaseDate(date);
                                        System.out.println("Købsdato opdateret.");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Dag, måned og år skal være et heltal. Prøv igen.");
                                    }
                                }
                                
                                break;
                            case 3:
                                
                                while (true) {
                                    System.out.println("****** Indtast ny købspris på bogkopi ******");
                                    String purchasePriceString = keyboard.next();
                                    
                                    try {
                                        bt.setPurchasePrice(Integer.valueOf(purchasePriceString));
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Købspris skal være et heltal. Prøv igen.");
                                    }
                                }
                                break;
                            case 4:
                                while (true) {
                                    System.out.println("****** Skriv ny stregkode på bogkopi ******");
                                    String barCode_new = keyboard.next();
                                    if (btc.searchBookItemByBarcode(barCode_new) == null) {
                                        bt.setBarCode(barCode_new);
                                        break;
                                    } else System.out.println("Stregkode allerede til stede i databasen. Prøv igen."); 
                                    
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
                    
                
            }
        }            
    }
    
    public void deleteBookItem() {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("****** Skriv stregkode på et bogkopi for at slette bogkopiet fra databasen ******");
            System.out.println("****** Skriv 'esc' for at afslutte ******");
            String barCode = keyboard.next();
            if (barCode.equals("esc")) {
                System.out.println("Sletning af bogkopier afsluttet");
                break;
            } else {
                btc.deleteBookItemByBarCode(barCode);
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
