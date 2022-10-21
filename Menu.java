import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void menu(Department department) {

        menu: while (true) {
            System.out.println(String.format("%32s", "").replace(" ", "="));
            System.out.println(String.format("%17s", "MENU"));
            System.out.println(String.format("%32s", "").replace(" ", "="));
            System.out.println(" 1. Add a Lab");
            System.out.println(" 2. Remove a Lab");
            System.out.println(" 3. Add PC to a Lab");
            System.out.println(" 4. Remove PC from a Lab");
            System.out.println(" 5. Add Software to Lab(s)");
            System.out.println(" 6. Remove Software from Lab(s)");
            System.out.println(" 7. Print Labs");
            System.out.println(" 8. Print PCs of a Lab");
            System.out.println(" 9. Print Softwares of a Lab");
            System.out.println("10. Print Department Info");
            System.out.println("11. Exit Program");
            System.out.println(String.format("%32s", "").replace(" ", "="));
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Ignore this
            System.out.println(String.format("%32s", "").replace(" ", "="));

            switch (choice) {
                case 1:
                    Menu.addLab(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 2:
                    Menu.removeLab(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 3:
                    Menu.addPC(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 4:
                    Menu.removePC(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 5:
                    Menu.addSoftware(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 6:
                    Menu.removeSoftware(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 7:
                    department.printLabs();
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 8:
                    Menu.printPCs(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 9:
                    Menu.printSoftwares(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 10:
                    System.out.println(department);
                    if (Menu.navigate() == 0) {break menu;}
                    break;
                case 11:
                    sc.close();
                    break menu;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }

    // MENU Helper Functions

    public static void addLab(Department department) {
        System.out.println("Enter Lab Name: ");
        String labName = sc.nextLine();
        System.out.println("Enter Lab Attendant First Name: ");
        String labAFirstName = sc.nextLine();
        System.out.println("Enter Lab Attendant Last Name: ");
        String labALastNAme = sc.nextLine();
        department.addLab(new Lab(labName, new Employee(labAFirstName, labALastNAme, "Lab Attendant")));
        System.out.println("Lab added successfully!\n");
    }

    public static void removeLab(Department department) {
        System.out.println("Enter Lab Name: ");
        String name = sc.nextLine();
        int index = department.searchLab(name);
        if (index != -1) {
            department.removeLab(department.getLabs()[index]);
            System.out.println("Lab removed successfully!\n");
        } else {
            System.out.println("Lab not found!\n");
        }
    }

    public static void addPC(Department department) {
        System.out.println("Enter Lab Name: ");
        String labName = sc.nextLine();
        int index = department.searchLab(labName);
        if (index != -1) {
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
            department.getLabs()[index].addPC(new PC(pcName, monitorName, ramSize, hardDiskSize, hasGraphicCard));
            System.out.println("PC added successfully!\n");
        } else {
            System.out.println("Lab not found!\n");
        }
    }

    public static void removePC(Department department) {
        System.out.println("Enter Lab Name: ");
        String labName = sc.nextLine();
        int index = department.searchLab(labName);
        if (index != -1) {
            System.out.println("Enter PC Name: ");
            String pcName = sc.nextLine();
            int index2 = department.getLabs()[index].searchPC(pcName);
            if (index2 != -1) {
                department.getLabs()[index].removePC(department.getLabs()[index].getPCs()[index2]);
                System.out.println("PC removed successfully!\n");
            } else {
                System.out.println("PC not found!\n");
            }
        } else {
            System.out.println("Lab not found!\n");
        }
    }

    public static void printPCs(Department department) {
        System.out.println("Enter Lab Name: ");
        String labName = sc.nextLine();
        int index = department.searchLab(labName);
        if (index != -1) {
            department.getLabs()[index].printPCs();
        } else {
            System.out.println("Lab not found!\n");
        }
    }

    public static void addSoftware(Department department) {
        System.out.println("Enter Lab Name (0 to add in all labs): ");
        String labName = sc.nextLine();
        if (labName.equals("0")) {
            Software software = getSoftware();
            for (Lab lab : department.getLabs()) {
                if (lab != null) {
                    lab.addSoftware(software);
                }
            }
            System.out.println("Software added successfully!\n");
        } 
        else {
            int index = department.searchLab(labName);
            if (index != -1) {
                department.getLabs()[index].addSoftware(getSoftware());
                System.out.println("Software added successfully!\n");
            } else {
                System.out.println("Lab not found!\n");
            }
        }
    }

    public static void removeSoftware(Department department) {
        System.out.println("Enter Lab Name (0 to remove from all labs): ");
        String labName = sc.nextLine();
        if (labName.equals("0")) {
            System.out.println("Enter Software Name: ");
            String softwareName = sc.nextLine();
            for (Lab lab : department.getLabs()) {
                if (lab != null) {
                    int index = lab.searchSoftware(softwareName);
                    if (index != -1) {
                        lab.removeSoftware(lab.getSoftwares()[index]);
                    }
                }
            }
            System.out.println("Software removed successfully!\n");
        } 
        else {
            int index = department.searchLab(labName);
            if (index != -1) {
                System.out.println("Enter Software Name: ");
                String softwareName = sc.nextLine();
                int index2 = department.getLabs()[index].searchSoftware(softwareName);
                if (index2 != -1) {
                    department.getLabs()[index].removeSoftware(department.getLabs()[index].getSoftwares()[index2]);
                    System.out.println("Software removed successfully!\n");
                } else {
                    System.out.println("Software not found!\n");
                }
            } else {
                System.out.println("Lab not found!\n");
            }
        }
    }

    public static void printSoftwares(Department department) {
        System.out.println("Enter Lab Name: ");
        String labName = sc.nextLine();
        int index = department.searchLab(labName);
        if (index != -1) {
            department.getLabs()[index].printSoftwares();
        } else {
            System.out.println("Lab not found!\n");
        }
    }

    // Scans a software from Input and returns it (Helper of Menu.addSoftware)
    public static Software getSoftware() {
        System.out.println("Enter Software Name: ");
        String softwareName = sc.nextLine();
        System.out.println("Enter type of " + softwareName + ": ");
        String softwareType = sc.nextLine();
        System.out.println("Enter Company of " + softwareName + ": ");
        String softwareCompany = sc.nextLine();
        System.out.println("Enter Version of " + softwareName + ": ");
        String softwareVersion = sc.nextLine();
        System.out.println("Enter Size of " + softwareName + " (MB): ");
        int softwareSize = sc.nextInt();
        return new Software(softwareName, softwareType, softwareCompany, softwareVersion, softwareSize);
    }

    public static int navigate() {
        System.out.println("Enter:\n\t(1) to go back to Menu\n\t(0) to exit program");
        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }
}
