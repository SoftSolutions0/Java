import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Create a Department
        Department department = new Department(
                "Computer Science",
                new Employee("Dr", "Ahmed", "HOD"),
                new Employee("Dr.", "Ali", "Lab Incharge"));

        // Load Data
        DataHandler.loadData(department);

        // Print Menu
        Menu.menu(department);

        // Save Data
        DataHandler.saveData(department);
    }
}
