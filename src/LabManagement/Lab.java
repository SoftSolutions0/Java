package LabManagement;

import java.util.ArrayList;
import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.ASCIITableHeader;

public class Lab {
    private int ID;
    private String name;
    private Employee labAttendant;
    private ArrayList<PC> PCs;
    private ArrayList<Software> softwares;
    static int count = 0;

    public Lab (String name, Employee labAttendant) {
        this.ID = ++count;
        this.name = name;
        this.labAttendant = labAttendant;
        this.PCs = new ArrayList<PC>();
        this.softwares = new ArrayList<Software>();
    }

    public Lab(int ID, String name, Employee labAttendant) {
        this.ID = ID;
        this.name = name;
        this.labAttendant = labAttendant;
        this.PCs = new ArrayList<PC>();
        this.softwares = new ArrayList<Software>();
        count++;
    }

    public Lab(Lab other) {
        this.ID = other.getID();
        this.name = other.getName();
        this.labAttendant = other.getLabAttendant();
        this.PCs = new ArrayList<PC>();
        // Copying PCs
        for (PC pc : other.getPCs()) {
            this.PCs.add((PC) pc.clone());
        }
        this.softwares = new ArrayList<Software>();
        // Copying softwares
        for (Software software : other.getSoftwares()) {
            this.softwares.add((Software) software.clone());
        }
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Software> getSoftwares() {
        return softwares;
    }

    public Employee getLabAttendant() {
        return labAttendant;
    }

    public ArrayList<PC> getPCs() {
        return PCs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabAttendant(Employee labAttendant) {
        this.labAttendant = labAttendant;
    }

    public void addPC(PC pc) {
        PCs.add(pc);
    }

    public void removePC(PC pc) {
        PCs.remove(pc);
    }

    public void addSoftware(Software software) {
        softwares.add(software);
    }

    public void removeSoftware(Software software) {
        softwares.remove(software);
    }

    public PC searchPC(String name) {
        for (PC pc : PCs) {
            if (pc.getName().equalsIgnoreCase(name)) {
                return pc;
            }
        }
        return null;
    }

    public Software searchSoftware(String name) {
        for (Software software : softwares) {
            if (software.getName().equalsIgnoreCase(name)) {
                return software;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Lab Attendant: %s, Total PCs: %d", ID, name, labAttendant, PCs.size());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Lab && this.ID == ((Lab) obj).getID();
    }

    @Override
    protected Object clone() {
        return new Lab(this);
    }

    public void prettyPrintPCs() {
        
        // Printing PCs with Third Party Library
        ASCIITableHeader[] headers = new ASCIITableHeader[] {
            new ASCIITableHeader("ID", ASCIITable.ALIGN_LEFT),
            new ASCIITableHeader("Name", ASCIITable.ALIGN_LEFT),
            new ASCIITableHeader("LCD", ASCIITable.ALIGN_LEFT),
            new ASCIITableHeader("RAM", ASCIITable.ALIGN_CENTER),
            new ASCIITableHeader("Space", ASCIITable.ALIGN_CENTER),
            new ASCIITableHeader("GPU", ASCIITable.ALIGN_CENTER)
        };
        String[][] data = new String[PCs.size()][6];
        for (int i = 0; i < PCs.size(); i++) {
            data[i][0] = PCs.get(i).getID() + "";
            data[i][1] = PCs.get(i).getName();
            data[i][2] = PCs.get(i).getLcd();
            data[i][3] = PCs.get(i).getRam() + "";
            data[i][4] = PCs.get(i).getSpace() + "";
            data[i][5] = String.format("%s", PCs.get(i).getGpu() ? "Yes" : "No");
        }
        ASCIITable.getInstance().printTable(headers, data);
    }

    public void prettyPrintSoftwares() {
        
        // Printing softwares with Third Party Library
        ASCIITableHeader[] headers = new ASCIITableHeader[] {
            new ASCIITableHeader("Name", ASCIITable.ALIGN_LEFT),
            new ASCIITableHeader("Version", ASCIITable.ALIGN_CENTER),
            new ASCIITableHeader("Type", ASCIITable.ALIGN_LEFT),
            new ASCIITableHeader("Company", ASCIITable.ALIGN_LEFT),
            new ASCIITableHeader("Size (MB)", ASCIITable.ALIGN_CENTER)
        };
        String[][] data = new String[softwares.size()][5];
        for (int i = 0; i < softwares.size(); i++) {
            data[i][0] = softwares.get(i).getName();
            data[i][1] = softwares.get(i).getVersion();
            data[i][2] = softwares.get(i).getType();
            data[i][3] = softwares.get(i).getCompany();
            data[i][4] = String.valueOf(softwares.get(i).getSize());
        }
        ASCIITable.getInstance().printTable(headers, data);
    }

    public static Lab scanLab() {
        System.out.println("Enter Lab Details:");
        System.out.print("Name: ");
        String name = Menu.scanner.nextLine();
        System.out.print("Lab Attendant's First Name: ");
        String firstName = Menu.scanner.nextLine();
        System.out.print("Lab Attendant's Last Name: ");
        String lastName = Menu.scanner.nextLine();
        return new Lab(name, new Employee(firstName, lastName, "Lab Attendant"));
    }

}
