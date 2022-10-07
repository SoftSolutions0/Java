public class Employee {
    private String name;
    private String employeeID;
    private String designation;

    public Employee(String name, String employeeID, String designation) {
        this.name = name;
        this.employeeID = employeeID;
        this.designation = designation;
    }

    public Employee(Employee other){
        this.name = other.getName();
        this.employeeID = other.getEmployeeID();
        this.designation = other.getDesignation();
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Employee(this);
    }
}
