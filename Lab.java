public class Lab {
    private int ID;
    private String name;
    private Employee labAttendant;
    private PC[] computers = new PC[100];
    static int count = 0;

    public Lab(String name, Employee labAttendant) {
        this.ID = count++;
        this.name = name;
        this.labAttendant = labAttendant;
    }
    
    public Lab(int ID, String name, Employee labAttendant) {
        this.ID = ID;
        this.name = name;
        this.labAttendant = labAttendant;
        count++;
    }

    public Lab(Lab other){
        this.ID = count++;
        this.name = other.getName();
        this.labAttendant = new Employee(other.getLabAttendant());
        
        // Creating deep copy of the array
        PC temp[] = new PC[other.getPCs().length];
        for (int i = 0; i < temp.length; i++) {
            if(other.getPCs()[i] != null){
                temp[i] = new PC(other.getPCs()[i]);
            }
        }
        this.computers = temp;
    }

    public int getID() {
        return ID;
    }
    
    public PC[] getPCs() {
        return this.computers;
    }

    public Employee getLabAttendant() {
        return labAttendant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabAttendant(Employee labAttendant) {
        this.labAttendant = labAttendant;
    }

    public void addPC(PC pc){
        for (int i = 0; i < computers.length; i++) {
            if(computers[i] == null){
                computers[i] = pc;
                break;
            }
        }
    }

    public void printPCs(){
        System.out.println(String.format("%70s","").replace(" ", "="));
        System.out.println(String.format("%5s   %-22s %-15s %10s %10s", "ID", "Name", "LCD Name", "RAM (GB)", "SSD (GB)"));
        System.out.println(String.format("%70s","").replace(" ", "="));
        for(PC computer: computers){
            if (computer != null){
                System.out.println(computer);
            }
        }
        System.out.println(String.format("%70s","").replace(" ", "="));
    }

    @Override
    public String toString() {
        return String.format("%5s %-8s %-20s %5d", ID, name, labAttendant, totalPCs());
    }

    public void removePC(PC pc){
        for (int i = 0; i < computers.length; i++) {
            if (computers[i] != null && computers[i].equals(pc)){
                computers[i] = null;
                break;
            }
        }
    }

    @Override
    protected Object clone() {
        return new Lab(this);
    }

    public int searchPC(String name){
        for (int i = 0; i < computers.length; i++) {
            if (computers[i] != null && computers[i].getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    public int totalPCs(){
        int count = 0;
        for (PC computer : computers) {
            if (computer != null){
                count++;
            }
        }
        return count;
    }
}

