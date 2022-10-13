public class Department {
    private String name;
    private Lab[] labs= new Lab[20];
    private Employee HOD;
    private Employee labIncharge;

    public Department(String name, Employee HOD, Employee labIncharge) {
        this.name = name;
        this.HOD = HOD;
        this.labIncharge = labIncharge;
    }

    // Copy Constructor
    public Department(Department other){
        this.name = other.getName();
        this.HOD = new Employee(other.getHOD());
        this.labIncharge = new Employee(other.getLabIncharge());

        // Creating deep copy of the array
        Lab temp[] = new Lab[other.getLabs().length];
        for (int i = 0; i < temp.length; i++) {
            if(other.getLabs()[i] != null){
                temp[i] = new Lab(other.getLabs()[i]);
            }
        }
        this.labs = temp;
    }

    @Override
    public String toString() {
        return String.format("Department Name: %s\nHOD Name: %s\nLab Incharge Name: %s\nTotal Labs: %d", name, HOD, labIncharge, totalLabs());
    }

    public Lab[] getLabs() {
        return labs;
    }

    public String getName() {
        return name;
    }

    public Employee getHOD() {
        return HOD;
    }

    public Employee getLabIncharge() {
        return labIncharge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHOD(Employee hOD) {
        this.HOD = hOD;
    }

    public void setLabIncharge(Employee labIncharge) {
        this.labIncharge = labIncharge;
    }

    public void printLabs(){
        System.out.println(String.format("%43s","").replace(" ", "="));
        System.out.println(String.format("%5s %-8s %-20s %5s", "ID", "Name", "Lab Attendant", "PCs"));
        System.out.println(String.format("%43s","").replace(" ", "="));
        for(Lab lab: labs){
            if(lab != null){
                System.out.println(lab);
            }
        }
        System.out.println(String.format("%43s","").replace(" ", "="));
    }

    public void addLab(Lab lab){
        for (int i = 0; i < labs.length; i++) {
            if(labs[i] == null){
                labs[i] = lab;
                break;
            }
        }
    }

    public void removeLab(Lab lab){
        for (int i = 0; i < labs.length; i++) {
            if(labs[i] != null && labs[i].equals(lab)){
                labs[i] = null;
                break;
            }
        }
    }

    @Override
    protected Object clone() {
        return new Department(this);
    }

    public int searchLab(String name){
        for (int i = 0; i < labs.length; i++) {
            if(labs[i] != null && labs[i].getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    public int getLabIndex(int index) {
        for (int i = 0; i < labs.length; i++) {
            if (labs[i] != null && labs[i].getID() == index) {
                return i;
            }
        }
        return -1;
    }

    public int totalLabs(){
        int count = 0;
        for (Lab lab : labs) {
            if(lab != null){
                count++;
            }
        }
        return count;
    }
}
