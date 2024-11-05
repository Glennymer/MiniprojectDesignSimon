package model;
import java.util.ArrayList;

public class PersonContainer
{
    private ArrayList<Person> persons;
    private static PersonContainer instance;
    private PersonContainer()
    {
        persons = new ArrayList<>();
    }

    public static PersonContainer getInstance() {
        if (instance == null) {
            instance = new PersonContainer();
        }
        return instance;
    }
    
    public static Person searchPersonByName(String name) {
        Person foundPerson = null;
        for(Person p : getInstance().persons) if(p.getName().equals(name)) foundPerson = p; 
        return foundPerson;
    }
    
    public static Person searchPersonByPhone(String phone) {
        Person foundPerson = null;
        for(Person p : getInstance().persons) if(p.getPhone().equals(phone)) foundPerson = p; 
        return foundPerson;
    }
    
    public static void addPerson(Person p) {
        getInstance().persons.add(p);
    }
    
    public static void deletePerson(Person p) {
        ArrayList<Person> persons_new = new ArrayList<>();
        for (Person p1 : getInstance().persons) if (!(p1.getName().equals(p.getName()) || p1.getPhone().equals(p.getPhone()))) persons_new.add(p1);
        getInstance().persons = persons_new;
    }
}
