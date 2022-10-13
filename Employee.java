public class Employee {
    private String firstName;
    private String lastName;
    private int ID;
    private String designation;
    static int count = 0;

    public Employee(String firstName, String lastName, String designation) {
        this.ID = count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
    }

    public Employee(int ID, String firstName, String lastName, String designation) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        count++;
    }

    // Copy Constructor
    public Employee(Employee other) {
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.ID = count++;
        this.designation = other.getDesignation();
    }

    public String getDesignation() {
        return designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getID() {
        return ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    protected Object clone() {
        return new Employee(this);
    }
}
