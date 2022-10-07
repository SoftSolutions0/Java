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

    public Lab[] getLabs() {
        return labs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHOD() {
        return HOD;
    }

    public Employee getLabIncharge() {
        return labIncharge;
    }

    public void setHOD(Employee hOD) {
        this.HOD = hOD;
    }

    public void setLabIncharge(Employee labIncharge) {
        this.labIncharge = labIncharge;
    }

    public void printLabs(){
        for(Lab lab: labs){
            if(lab != null){
                System.out.println(lab);
            }
        }
    }

    public void addLab(Lab lab){
        for (int i = 0; i < labs.length; i++) {
            if(labs[i] == null){
                labs[i] = new Lab(lab);
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
}
