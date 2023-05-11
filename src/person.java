public class person {
    private String name;
    private String surname;
    private String email;

    //This line declares a constructor for the "person" class. It takes three parameters of type "String"
    // - name, surname, and email - and initializes the corresponding instance variables of the newly created object.
    public person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;

    }
    //three getters for each variable
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {return email;
    }
}