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
        
        DataHandler.loadData(dpt1);

        // Print Menu
        Menu.printMenu(dpt1);

        // Save Data to files
        DataHandler.saveData(dpt1);
    }
}
