import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    static String employeeFilePath = "E:\\Java\\LabManagement\\data\\employee.csv";
    static String pcFilePath = "E:\\Java\\LabManagement\\data\\pc.csv";
    static String labFilePath = "E:\\Java\\LabManagement\\data\\lab.csv";
    
    public static void main(String[] args) throws IOException {
        
        
        // Create a Department
        Employee HOD = new Employee("Dr", "Ahmed" , "HOD");
        Employee labIncharge = new Employee("Dr.","Ali", "Lab Incharge");
        Department department = new Department("Computer Science", HOD, labIncharge);

        // Load Data
        loadData(department);

        // Print Menu
        menu(department);
    }

    public static void menu(Department department) throws IOException{
        
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println(String.format("%32s","").replace(" ", "="));
            System.out.println(String.format("%17s", "MENU"));
            System.out.println(String.format("%32s","").replace(" ", "="));
            System.out.println("1. Add a Lab");
            System.out.println("2. Remove a Lab");
            System.out.println("3. Add PC to a Lab");
            System.out.println("4. Remove PC from a Lab");
            System.out.println("5. Print Labs");
            System.out.println("6. Print PCs of a Lab");
            System.out.println("7. Print Department Info");
            System.out.println("8. Exit Program");
            System.out.println(String.format("%32s","").replace(" ", "="));
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println(String.format("%32s","").replace(" ", "="));

            switch (choice) {
                case 1:
                    System.out.println("Enter Lab Name: ");
                    String labName = sc.nextLine();
                    System.out.println("Enter Lab Attendant First Name: ");
                    String labAFirstName = sc.nextLine();
                    System.out.println("Enter Lab Attendant Last Name: ");
                    String labALastNAme = sc.nextLine();
                    department.addLab(new Lab(labName, new Employee(labAFirstName, labALastNAme, "Lab Attendant")));
                    System.out.println("Lab added successfully!\n");
                    break;
                case 2:
                    System.out.println("Enter Lab Name: ");
                    String name = sc.nextLine();
                    int index = department.searchLab(name);
                    if (index != -1) {
                        department.removeLab(department.getLabs()[index]);
                        System.out.println("Lab removed successfully!\n");
                    } else {
                        System.out.println("Lab not found!\n");
                    }
                    break;
                case 3:
                    System.out.println("Enter Lab Name: ");
                    String labName1 = sc.nextLine();
                    int index1 = department.searchLab(labName1);
                    if (index1 != -1) {
                        System.out.println("Enter PC Name: ");
                        String pcName = sc.nextLine();
                        System.out.println("Enter LCD Name: ");
                        String monitorName = sc.nextLine();
                        System.out.println("Enter RAM Size (GB): ");
                        int ramSize = sc.nextInt();
                        System.out.println("Enter SSD Size (GB): ");
                        int hardDiskSize = sc.nextInt();
                        System.out.println("Is PC has a graphic card? (true/false): ");
                        boolean hasGraphicCard = sc.nextBoolean();
                        department.getLabs()[index1].addPC(new PC(pcName, monitorName, ramSize, hardDiskSize, hasGraphicCard));
                        System.out.println("PC added successfully!\n");
                    } else {
                        System.out.println("Lab not found!\n");
                    }
                    break;

                case 4:
                    System.out.println("Enter Lab Name: ");
                    String labName2 = sc.nextLine();
                    int index2 = department.searchLab(labName2);
                    if (index2 != -1) {
                        System.out.println("Enter PC Name: ");
                        String pcName = sc.nextLine();
                        int index3 = department.getLabs()[index2].searchPC(pcName);
                        if (index3 != -1) {
                            department.getLabs()[index2].removePC(department.getLabs()[index2].getPCs()[index3]);
                            System.out.println("PC removed successfully!\n");
                        } else {
                            System.out.println("PC not found!\n");
                        }
                    } else {
                        System.out.println("Lab not found!\n");
                    }
                    break;

                case 5:
                    department.printLabs();
                    break;

                case 6:
                    System.out.println("Enter Lab Name: ");
                    String labName3 = sc.nextLine();
                    int index4 = department.searchLab(labName3);
                    if (index4 != -1) {
                        department.getLabs()[index4].printPCs();
                    } else {
                        System.out.println("Lab not found!\n");
                    }
                    break;

                case 7:
                    System.out.println(department);
                    break;

                case 8:
                    sc.close();
                    saveData(department);  // Save the Data
                    System.out.println("Program Exited.");
                    System.exit(0);
                
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }

    public static void loadData(Department department) throws IOException {
        
        System.out.println("Loading Data...");

        // Read data from employees.csv
        FileReader fr = new FileReader(new File(employeeFilePath));
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        Employee[] employees = new Employee[50];  // Temporary Array
        String line;
        while ((line=br.readLine()) != null) {
            String[] fields = line.split(",");
            employees[Integer.parseInt(fields[0])] = new Employee(Integer.parseInt(fields[0]),fields[1], fields[2], fields[3]);
        }
        br.close();

        // Read data from labs.csv
        fr = new FileReader(new File(labFilePath));
        br = new BufferedReader(fr);
        br.readLine();
        while ((line=br.readLine()) != null) {
            String[] fields = line.split(",");
            department.addLab(new Lab(Integer.parseInt(fields[0]), fields[1], employees[Integer.parseInt(fields[2])]));
        }
        br.close();

        // Read data from pc.csv
        fr = new FileReader(pcFilePath);
        br = new BufferedReader(fr);
        br.readLine();
        while ((line=br.readLine()) != null) {
            String[] fields = line.split(",");
            PC pc = new PC(Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Boolean.parseBoolean(fields[5]));
            department.getLabs()[department.getLabIndex(Integer.parseInt(fields[6]))].addPC(pc);
        }
        br.close();

        System.out.println("Data Loaded.");
    }

    public static void saveData(Department department) throws IOException {
        
        System.out.println("Saving Data...");
        
        // Initializing Buffers
        BufferedWriter pcWriter = new BufferedWriter(new FileWriter(new File(pcFilePath)));
        BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(new File(employeeFilePath)));
        BufferedWriter labWriter = new BufferedWriter(new FileWriter(new File(labFilePath)));

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
    
   
