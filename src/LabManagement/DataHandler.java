package LabManagement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class DataHandler {
    // Files to deal with
    static File employeeFile = new File("E:\\Java\\LabManagement\\data\\employee.csv");
    static File pcFile = new File("E:\\Java\\LabManagement\\data\\pc.csv");
    static File labFile = new File("E:\\Java\\LabManagement\\data\\lab.csv");
    static File softwareFile = new File("E:\\Java\\LabManagement\\data\\software.csv");

    static {

        if (!employeeFile.exists()) {
            throw new RuntimeException("Employee file not found!"); 
        }
        if (!pcFile.exists()) {
            throw new RuntimeException("PC file not found!"); 
        }
        if (!labFile.exists()) {
            throw new RuntimeException("Lab file not found!"); 
        }
        if (!softwareFile.exists()) {
            throw new RuntimeException("Software file not found!"); 
        }
        
    }

    // To Load Data in memory from files
    public static void loadData(Department department) throws IOException {

        System.out.println("Loading Data...");

        // Read data from employees.csv
        BufferedReader br = new BufferedReader(new FileReader(employeeFile));
        br.readLine(); // Skip Header
        Employee[] employees = new Employee [50]; // Temporary Array
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            employees[Integer.parseInt(fields[0])] = new Employee(
                Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]
            );
        }
        br.close();

        // Read data from labs.csv
        br = new BufferedReader(new FileReader(labFile));
        br.readLine(); // Skip Header
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            department.addLab(
                new Lab(
                    Integer.parseInt(fields[0]), fields[1],
                    (Employee) (employees[Integer.parseInt(fields[2])]).clone()
                )
            );
        }
        br.close();

        // Read data from pc.csv
        br = new BufferedReader(new FileReader(pcFile));
        br.readLine(); // Skip Header
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            PC pc = new PC(
                Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]),
                Integer.parseInt(fields[4]), Boolean.parseBoolean(fields[5])
            );
            department.searchLabByID(Integer.parseInt(fields[6])).addPC(pc);
        }
        br.close();

        // Read data from software.csv
        br = new BufferedReader(new FileReader(softwareFile));
        br.readLine(); // Skip Header
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            Software software = new Software(
                fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4])
            );
            department.searchLabByID(Integer.parseInt(fields[5])).addSoftware(software);
        }
        System.out.println("Data Loaded.");
    }

    // To save data to files
    public static void saveData(Department department) throws IOException {

        System.out.println("Saving Data...");

        // Initializing Buffers
        BufferedWriter pcWriter = new BufferedWriter(new FileWriter(pcFile));
        BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(employeeFile));
        BufferedWriter labWriter = new BufferedWriter(new FileWriter(labFile));
        BufferedWriter softwareWriter = new BufferedWriter(new FileWriter(softwareFile));

        // Writing Headers
        pcWriter.write("ID,Name,LCD,RAM,SSD,GPU,Lab ID\n");
        employeeWriter.write("ID,First Name,Last Name,Designation\n");
        labWriter.write("ID,Name,Lab Attendant\n");
        softwareWriter.write("Name,Type,Company,Version,Size (MB),Lab ID\n");

        ArrayList<Lab> labs = department.getLabs();
        for (Lab lab : labs) {
            // Write to Lab File
            labWriter.write(String.format("%d,%s,%d\n", lab.getID(), lab.getName(), lab.getLabAttendant().getID()));
            // Writing Lab Attendant to Employee File
            Employee labAttendant = lab.getLabAttendant();
            employeeWriter.write(
                    String.format("%d,%s,%s,%s\n", labAttendant.getID(), labAttendant.getFirstName(),
                        labAttendant.getLastName(), labAttendant.getDesignation()));

            // Writing PCs to PC File
            for (PC pc : lab.getPCs()) {
                pcWriter.write(
                    String.format("%s,%s,%s,%d,%d,%s,%s\n",
                        pc.getID(), pc.getName(), pc.getLcd(),
                        pc.getRam(), pc.getSpace(), pc.getGpu(), lab.getID()));
            }

            // Writing Softwares to Software File
            for (Software software : lab.getSoftwares()) {
                softwareWriter.write(
                    String.format("%s,%s,%s,%s,%d,%s\n",
                        software.getName(), software.getVersion(), software.getType(),
                        software.getCompany(), software.getSize(), lab.getID()));
            }
        }
        // Closing buffer
        employeeWriter.close();
        labWriter.close();
        pcWriter.close();
        softwareWriter.close();

        System.out.println("Data Saved.");
    }
}
