package LabManagement;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Create a Department
        Department dpt1 = new Department(
            "Computer Science",
            new Employee("Mr.", "Ahmed", "HOD"),
            new Employee("Mr.", "Ali", "Labs Incharge")
        );
        
        // Load Data from files
        // try {
        //     DataHandler.loadData(dpt1);
        // } catch (Exception e) {
        //     System.out.println("Files not found!");
        // }
        DataHandler.loadData(dpt1);

        // Print Menu
        Menu.printMenu(dpt1);

        // Save Data to files
        DataHandler.saveData(dpt1);
    }
}
