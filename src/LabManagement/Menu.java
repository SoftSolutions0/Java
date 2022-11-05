package LabManagement;

import java.util.Scanner;
import com.bethecoder.ascii_table.ASCIITable;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static void printMenu(Department department) {
        String[] options = {
            "Add a new lab",
            "Remove a lab",
            "Add PC to a lab",
            "Remove PC from a lab",
            "Add software to a lab",
            "Remove software from a lab",
            "Print Department details",
            "Print Labs",
            "Print PCs of a Lab",
            "Print Softwares of a Lab",
            "Exit"
        };

        menu: while (true) {
            String[] header = {"MENU"};
            String[][] data = new String[options.length][1];
            for (int i = 0; i < options.length; i++) {
                data[i][0] = String.format("%2d. %s", i + 1, options[i]);
            }
            ASCIITable.getInstance().printTable(header, ASCIITable.ALIGN_CENTER, data, ASCIITable.ALIGN_LEFT);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addLab(department);    
                    if (exit()) { break menu; }
                    break;
                case 2:
                    removeLab(department);
                    if (exit()) { break menu; }
                    break;
                case 3:
                    addPC(department);
                    if (exit()) { break menu; }
                    break;
                case 4:
                    removePC(department);
                    if (exit()) { break menu; }
                    break;
                case 5:
                    addSoftware(department);
                    if (exit()) { break menu; }
                    break;
                case 6:
                    removeSoftware(department);
                    if (exit()) { break menu; }
                    break;
                case 7:
                    System.out.println(department);
                    if (exit()) { break menu; }
                    break;
                case 8:
                    department.prettyPrintLabs();
                    if (exit()) { break menu; }
                    break;
                case 9:
                    printPCs(department);
                    if (exit()) { break menu; }
                    break;
                case 10:
                    printSoftwares(department);
                    if (exit()) { break menu; }
                    break;
                case 11:
                    break menu;
                default:
                    System.out.println("Invalid choice!");
            }
        }

    }

    public static boolean exit() {
        System.out.println("\tPress 1 to go back to the main menu");
        System.out.println("\tPress 0 to exit");
        System.out.print(">>> ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            return choice == 0 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void addLab(Department department) {
        department.addLab(Lab.scanLab());
        System.out.println("Lab Added Successfully!");
    } 

    public static void removeLab(Department department) {
        department.prettyPrintLabs();
        System.out.print("Which lab to remove?\n\tEnter Lab Name: ");
        String name = scanner.nextLine();
        try {
            department.getLabs().remove(department.searchLab(name));
            System.out.println("Lab removed successfully!");
        } catch (Exception e) {
            System.out.println("Lab not found!");
        }
    }

    public static void addPC(Department department) {
        department.prettyPrintLabs();
        System.out.print("In which Lab to add PC?\n\tChoose and Enter Lab Name: ");
        String labName = scanner.nextLine();
        try {
            department.searchLab(labName).addPC(PC.scanPC());
            System.out.println("PC added successfully!");
        } catch (Exception e) {
            System.out.println("Lab not found!");
        }
    }

    public static void removePC(Department department) {
        department.prettyPrintLabs();
        System.out.print("From which Lab to remove PC?\n\tChoose and Enter Lab Name: ");
        String labName = scanner.nextLine();
        try {
            Lab lab = department.searchLab(labName);
            lab.prettyPrintPCs();
            System.out.print("Which PC to remove?\n\tEnter PC Name: ");
            String pcName = scanner.nextLine();
            lab.getPCs().remove(lab.searchPC(pcName));
            System.out.println("PC removed successfully!");
        } catch (Exception e) {
            System.out.println("Not found!");
        }
    }

    public static void addSoftware(Department department) {
        department.prettyPrintLabs();
        System.out.print("In which Lab to add Software?\n\tChoose and Enter Lab Name: ");
        String labName = scanner.nextLine();
        try {
            department.searchLab(labName).addSoftware(Software.scanSoftware());
            System.out.println("Software added successfully!");
        } catch (Exception e) {
            System.out.println("Not found!");
        }
    }

    public static void removeSoftware(Department department) {
        department.prettyPrintLabs();
        System.out.print("From which Lab to remove Software?\n\tChoose and Enter Lab Name: ");
        String labName = scanner.nextLine();
        try {
            Lab lab = department.searchLab(labName);
            lab.prettyPrintSoftwares();
            System.out.print("Which Software to remove?\n\tEnter Software Name: ");
            String softwareName = scanner.nextLine();
            lab.getSoftwares().remove(lab.searchSoftware(softwareName));
            System.out.println("Software removed successfully!");
        } catch (Exception e) {
            System.out.println("Not found!");
        }
    }

    public static void printPCs(Department department) {
        department.prettyPrintLabs();
        System.out.print("Which Lab's PCs to print?\n\tChoose and Enter Lab Name: ");
        String labName = scanner.nextLine();
        try {
            department.searchLab(labName).prettyPrintPCs();
        } catch (NullPointerException e) {
            System.out.println("Not found!");
        }
    }

    public static void printSoftwares(Department department) {
        department.prettyPrintLabs();
        System.out.print("Which Lab's Softwares to print?\n\tChoose and Enter Lab Name: ");
        String labName = scanner.nextLine();
        try {
            department.searchLab(labName).prettyPrintSoftwares();
        } catch (NullPointerException e) {
            System.out.println("Not found!");
        }
    }
}
