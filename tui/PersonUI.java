package tui;
import java.util.Scanner;
import model.Person;
import controller.PersonController;

public class PersonUI
{
    
    private PersonController pc;
    
    public PersonUI()
    {
        pc = new PersonController();
    }
    
    public Person writeSearchPersonByName() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Søg efter person ved navn ******");
        String name = keyboard.nextLine();
        return pc.searchPersonByName(name);
    }
    
    public Person writeSearchPersonByPhone() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Søg efter person ved navn ******");
        String phone = keyboard.next();
        return pc.searchPersonByPhone(phone);
    }
    
    public void addPerson() {
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;
        String name="", address="", postalCode="", city = "", phone = "";
        
        
        while (running) {
            System.out.println("****** Skriv navn på ny person ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            name = keyboard.nextLine();
            if (name.equals("esc")) {
                running = false;
                break;
            }
            
            if (pc.searchPersonByName(name) == null) break;
            else System.out.println("The indtastede navn er allerede til stede i databasen. Prøv igen.");
        
        }
        
        if (running) {
            System.out.println("****** Skriv adresse på ny person ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            address = keyboard.nextLine();
            if (address.equals("esc")) {
                running = false;
            }
            
        }
        
        if (running) {
            System.out.println("****** Skriv postnummer på ny person ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            postalCode = keyboard.next();
            if (postalCode.equals("esc")) {
                running = false;
            }
            
        }
        
        if (running) {
            System.out.println("****** Skriv by på ny person ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            keyboard.nextLine();
            city = keyboard.nextLine();
            if (city.equals("esc")) {
                running = false;
            }
            
        }
        
        while (running) {
            System.out.println("****** Skriv telefonnummer på ny person ******");
            System.out.println("****** Skriv 'esc' for at annullere ******");
            phone = keyboard.next();
            if (phone.equals("esc")) {
                running = false;
                break;
            }
            
            if (pc.searchPersonByPhone(phone) == null) break;
            else System.out.println("The indtastede telefonnummer er allerede til stede i databasen. Prøv igen.");
        
        }
       
        if (running) {
            Person p = new Person(name, address, postalCode, city, phone);
            pc.addPerson(p);
            System.out.println("Tilføjelse af ny person fuldført.");
        } else {
            System.out.println("Tilføjelse af ny person annulleret.");
        }
    }
    
    public void deletePerson(Person p) {
        pc.deletePerson(p);
    }
    
    public void updatePerson(Person p) {
        System.out.println(p.toString());
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("****** Opdater bog ******");
            System.out.println(" (1) Opdater personens navn");
            System.out.println(" (2) Opdater personens adresse");
            System.out.println(" (3) Opdater personens postnummer");
            System.out.println(" (4) Opdater personens by");
            System.out.println(" (5) Opdater personens telefonnummer");
            
            System.out.println(" (0) Tilbage");
            System.out.print("\n Vælg:");
            int choice = getIntegerFromUser(keyboard);
            switch (choice) {
                case 1:
                    System.out.println("****** Indtast nyt navn på person ******");
                    keyboard.nextLine();
                    String name = keyboard.nextLine();
                    if (pc.searchPersonByName(name) == null) {
                        p.setName(name);
                        System.out.println("Navn opdateret.");
                    }
                    else System.out.println("The indtastede navn er allerede til stede i databasen. Navn ikke opdateret.");
                    
                    
                    
                    break;
                case 2:
                    System.out.println("****** Indtast ny adresse på person ******");
                    keyboard.nextLine();
                    p.setAddress(keyboard.nextLine());
                    System.out.println("Adresse opdateret.");
                    break;
                case 3:
                    System.out.println("****** Indtast nyt postnummer på person ******");
                    p.setPostalCode(keyboard.next());
                    System.out.println("Postnummer opdateret.");
                    break;
                case 4:
                    System.out.println("****** Indtast ny by på person ******");
                    keyboard.nextLine();
                    p.setCity(keyboard.nextLine());
                    System.out.println("By opdateret.");
                    
                    break;
                case 5:
                    System.out.println("****** Indtast nyt telefonnummer på person ******");
                    String phone = keyboard.next();
                    if (pc.searchPersonByPhone(phone) == null) {
                        p.setPhone(phone);
                        System.out.println("Telefonnummer opdateret.");
                    }
                    else System.out.println("The indtastede telefonnummer er allerede til stede i databasen. Telefonnummer ikke opdateret.");
                    
                    
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
    
    private int getIntegerFromUser(Scanner keyboard) {
        while (!keyboard.hasNextInt()) {
            System.out.println("Input skal være et tal - prøv igen");
            keyboard.nextLine();
        }
        return keyboard.nextInt();
    }
}
