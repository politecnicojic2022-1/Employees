package co.edu.poli.ces3.employees.entities;

public class Employee {

    public String COMPANY_PUBLIC_KEY = "abc";
    protected String Id;
    private String name;
    private String lastName;
    private int age;


    public Employee(String id, String name, String lastName, int age) {
        Id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Employee(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getCOMPANY_PUBLIC_KEY() {
        return COMPANY_PUBLIC_KEY;
    }

    public void setCOMPANY_PUBLIC_KEY(String COMPANY_PUBLIC_KEY) {
        this.COMPANY_PUBLIC_KEY = COMPANY_PUBLIC_KEY;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }


}
