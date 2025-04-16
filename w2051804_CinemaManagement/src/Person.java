public class Person {
    private String name;
    private String surname;
    private String email;


    Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void personInformation() {
        System.out.println("Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}');

    }
}
