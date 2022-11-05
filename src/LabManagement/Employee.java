package LabManagement;

public class Employee {
    private int ID;
    private String firstName, lastName, designation;
    static int count = 0;

    public Employee(int ID, String firstName, String lastName, String designation) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        count++;
    }

    public Employee(String firstName, String lastName, String designation) {
        this.ID = ++count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
    }

    public Employee(Employee other) {
        this.ID = other.getID();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.designation = other.getDesignation();
    }

    public int getID() {
        return ID;
    }

    public String getDesignation() {
        return designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s %s, Desigantion: %s", ID, firstName, lastName, designation);
    }

    @Override
    protected Object clone() {
        return new Employee(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Employee && this.ID == ((Employee) obj).getID();
    }
}
