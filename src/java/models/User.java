package models;


public class User {
    
    private int id;
    private String firstname;
    private String lastname;
    private String surname;
    private String email;
    private String password;
    private String role;
    
    public User() {
        id = 0;
        firstname = "";
        lastname = "";
        surname = "";
        email = "";
        password = "";
        role = "";
    }
    
    public User(int id, String firstname, String lastname, 
                String surname, String email, String password, String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getFirstName() {
        return this.firstname;
    }
    
    public String getLastName() {
        return this.lastname;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
