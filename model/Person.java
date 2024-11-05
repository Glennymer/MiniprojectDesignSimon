package model;


public class Person
{
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String phone;
 
    public Person(String name, String address, String postalCode, String city, String phone)
    {
       setName(name);
       setAddress(address);
       setPostalCode(postalCode);
       setCity(city);
       setPhone(phone);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public String toString() {
        String s = "Navn: " + this.name + "\n";
        s += "Adresse: " + this.address + ", " + this.postalCode + " " + this.city + "\n";
        s += "Telefonnummer: " + this.phone + "\n";
        return s;
    }
}
