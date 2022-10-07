public class Main {
    public static void main(String[] args) {
        Employee hod = new Employee("Dr. A", "123", "HOD");
        Employee labIncharge = new Employee("Dr. B", "456", "Lab Incharge");
        Employee labAttendant = new Employee("Dr. C", "476", "Lab Attendand");
        Employee labAttendant2 = new Employee("Dr. D", "434", "Lab Attendand");


        Department department = new Department("Computer Science", hod, labIncharge);

        Lab lab1 = new Lab("Lab 1", labAttendant);
        Lab lab2 = new Lab("Lab 2", labAttendant2);
        department.addLab(lab1);
        department.addLab(lab2);
        PC pc1 = new PC("1", "PC 1", "LCD 1", 1024, 500, true);
        PC pc2 = new PC("2", "PC 2", "LCD 2", 2048, 1000, false);
        lab1.addPC(pc1);
        lab1.addPC(pc2);
        // department.printLabs();
        lab1.printPCs();
        lab1.removePC(pc2);
        lab1.printPCs();
    }
}