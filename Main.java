import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {
    
    static File employeeFile = new File("E:\\Java\\LabManagement\\data\\employee.csv");
    static File pcFile = new File("E:\\Java\\LabManagement\\data\\pc.csv");
    static File labFile = new File("E:\\Java\\LabManagement\\data\\lab.csv");
    
    public static void main(String[] args) throws IOException {
        
        
        // Create a Department
        Employee HOD = new Employee("Dr", "Ahmed" , "HOD");
        Employee labIncharge = new Employee("Dr.","Ali", "Lab Incharge");
        Department department = new Department("Computer Science", HOD, labIncharge);

        // Load Data
        loadData(department);

        // Print Menu
        Menu.menu(department);

        // Save Data
        saveData(department);
    }

    
    // To Load Data in memory from files
    public static void loadData(Department department) throws IOException {
        
        System.out.println("Loading Data...");

        // Read data from employees.csv
        BufferedReader br = new BufferedReader(new FileReader(employeeFile));
        br.readLine();
        Employee[] employees = new Employee[50];  // Temporary Array
        String line;
        while ((line=br.readLine()) != null) {
            String[] fields = line.split(",");
            employees[Integer.parseInt(fields[0])] = new Employee(Integer.parseInt(fields[0]),fields[1], fields[2], fields[3]);
        }
        br.close();

        // Read data from labs.csv
        br = new BufferedReader(new FileReader(labFile));
        br.readLine();
        while ((line=br.readLine()) != null) {
            String[] fields = line.split(",");
            department.addLab(new Lab(Integer.parseInt(fields[0]), fields[1], employees[Integer.parseInt(fields[2])]));
        }
        br.close();

        // Read data from pc.csv
        br = new BufferedReader(new FileReader(pcFile));
        br.readLine();
        while ((line=br.readLine()) != null) {
            String[] fields = line.split(",");
            PC pc = new PC(Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Boolean.parseBoolean(fields[5]));
            department.getLabs()[department.getLabIndex(Integer.parseInt(fields[6]))].addPC(pc);
        }
        br.close();

        System.out.println("Data Loaded.");
    }


    // To save data to files
    public static void saveData(Department department) throws IOException {
        
        System.out.println("Saving Data...");
        
        // Initializing Buffers
        BufferedWriter pcWriter = new BufferedWriter(new FileWriter(pcFile));
        BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(employeeFile));
        BufferedWriter labWriter = new BufferedWriter(new FileWriter(labFile));

        // Writing Headers
        pcWriter.write("ID,Name,LCD,RAM,SSD,GPU,LabID");
        pcWriter.newLine();
        employeeWriter.write("ID,First Name,Last Name,Designation");
        employeeWriter.newLine();
        labWriter.write("ID,Name,Lab Attendant");
        labWriter.newLine();
        
        Lab[] labs = department.getLabs();
        for (Lab lab: labs) {
            if (lab != null) {
                // Write to Lab File
                labWriter.write(String.format("%d,%s,%d", lab.getID(), lab.getName(), lab.getLabAttendant().getID()));
                labWriter.newLine();
                // Writing Lab Attendant to Employee File
                Employee labAttendant = lab.getLabAttendant();
                String strToAppend = String.format("%d,%s,%s,%s", labAttendant.getID(), labAttendant.getFirstName(), labAttendant.getLastName(), labAttendant.getDesignation());
                employeeWriter.write(strToAppend);
                employeeWriter.newLine();
                
                // Writing PCs to PC File
                PC[] pcs = lab.getPCs();
                for (PC pc: pcs) {
                    if (pc != null) {
                        strToAppend = String.format("%d,%s,%s,%d,%d,%s,%d", pc.getID(), pc.getName(), pc.getLCDName(), pc.getRAMSizeGB(), pc.getDiskSizeGB(), pc.gethasGraphicsCard(), lab.getID());
                        pcWriter.write(strToAppend);
                        pcWriter.newLine();
                    }
                }
            }
        }

        // Closing buffers
        employeeWriter.close();
        labWriter.close();
        pcWriter.close();

        System.out.println("Data Saved.");
    }
}
    
   
