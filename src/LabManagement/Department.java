package LabManagement;

import java.util.ArrayList;
import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.ASCIITableHeader;

public class Department {
    private int ID;
    private String name;
    private Employee hod, labIncharge;
    private ArrayList<Lab> labs;
    static int count = 0;

    public Department(String name, Employee hod, Employee labIncharge) {
        this.ID = ++count;
        this.name = name;
        this.hod = hod;
        this.labIncharge = labIncharge;
        this.labs = new ArrayList<Lab>();
    }

    public Department(Department other) {
        this.ID = other.getID();
        this.name = other.getName();
        this.hod = other.getHod();
        this.labIncharge = other.getLabIncharge();
        this.labs = new ArrayList<Lab>();
        // Copying labs
        for (Lab lab : other.getLabs()) {
            this.labs.add((Lab) lab.clone());
        }
    }

    public int getID() {
        return ID;
    }

    public Employee getHod() {
        return hod;
    }

    public Employee getLabIncharge() {
        return labIncharge;
    }

    public ArrayList<Lab> getLabs() {
        return labs;
    }

    public String getName() {
        return name;
    }

    public void setHod(Employee hod) {
        this.hod = hod;
    }

    public void setLabIncharge(Employee labIncharge) {
        this.labIncharge = labIncharge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLab(Lab lab) {
        this.labs.add(lab);
    }

    public void removeLab(Lab lab) {
        this.labs.remove(lab);
    }

    public Lab searchLab(String name) {
        for (Lab lab : this.labs) {
            if (lab.getName().equalsIgnoreCase(name)) {
                return lab;
            }
        }
        return null;
    }

    public Lab searchLabByID(int ID) {
        for (Lab lab : this.labs) {
            if (lab.getID() == ID) {
                return lab;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("\tName: %s\n\tHOD: %s\n\tLab Incharge: %s\n",
            this.name, this.hod.getName(), this.labIncharge.getName());
    }

    public Object clone() {
        return new Department(this);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Department) {
            Department other = (Department) obj;
            return this.ID == other.getID() &&
                this.name.equals(other.getName()) &&
                this.hod.equals(other.getHod()) &&
                this.labIncharge.equals(other.getLabIncharge()
            );
        }
        return false;
    }

    public void prettyPrintLabs() {
        ASCIITableHeader[] headers = new ASCIITableHeader[5];
        headers[0] = new ASCIITableHeader("ID", ASCIITable.ALIGN_LEFT);
        headers[1] = new ASCIITableHeader("Name", ASCIITable.ALIGN_LEFT);
        headers[2] = new ASCIITableHeader("Lab Attendant", ASCIITable.ALIGN_LEFT);
        headers[3] = new ASCIITableHeader("Total PCs", ASCIITable.ALIGN_CENTER);
        headers[4] = new ASCIITableHeader("Total Softwares", ASCIITable.ALIGN_CENTER);
        String[][] data = new String[this.labs.size()][5];
        for (int i = 0; i < this.labs.size(); i++) {
            Lab lab = this.labs.get(i);
            data[i][0] = lab.getID() + "";
            data[i][1] = lab.getName();
            data[i][2] = lab.getLabAttendant().getName();
            data[i][3] = Integer.toString((lab.getPCs().size()));
            data[i][4] = Integer.toString((lab.getSoftwares().size()));
        }
        ASCIITable.getInstance().printTable(headers, data);
    }
}
