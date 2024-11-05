package tui;

import java.util.Scanner;

public class BookMenu
{
    private BookUI bui;
    private BookItemUI btui;
    public BookMenu()
    {
        bui = new BookUI();
        btui = new BookItemUI();
    }

    public void start() {
        bookMenu();
    }
    
    private void bookMenu() {
        boolean running = true;
        while (running) {
            int choice = writeBookMenu();
            switch (choice) {
                case 1:
                    bui.addBook();
                    break;
                case 2:
                    bui.readBook();
                    break;
                case 3:
                    bui.updateBook();
                    break;
                case 4:
                    bui.deleteBook();
                    break;
                case 5:
                    btui.addBookItem();
                    break;
                case 6:
                    btui.readBookItem();
                    break;
                case 7:
                    btui.updateBookItem();
                    break;
                case 8:
                    btui.deleteBookItem();
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
    
    private int writeBookMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Udlånsmenu ******");
        System.out.println(" (1) Tilføj bog");
        System.out.println(" (2) Læs information om bog");
        System.out.println(" (3) Opdater bog");
        System.out.println(" (4) Slet bog");
        System.out.println(" (5) Tilføj bogkopi");
        System.out.println(" (6) Læs information om bogkopi");
        System.out.println(" (7) Opdater bogkopi");
        System.out.println(" (8) Slet bogkopi");
        
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
}
