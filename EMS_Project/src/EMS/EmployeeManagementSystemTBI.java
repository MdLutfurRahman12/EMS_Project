package EMS;

import java.util.Scanner;

public class EmployeeManagementSystemTBI {
    public static void main(String[] args) {
        EmployeeManagementSystem.loadFromFile();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Employee Management System (TBI) =====");
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Delete Employee");
            System.out.println("5. Sort Employees by Salary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0;
            }

            switch (choice) {
                case 1 -> EmployeeManagementSystem.addEmployee(sc);
                case 2 -> EmployeeManagementSystem.viewAllEmployees();
                case 3 -> EmployeeManagementSystem.searchEmployee(sc);
                case 4 -> EmployeeManagementSystem.deleteEmployee(sc);
                case 5 -> EmployeeManagementSystem.sortEmployees();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    EmployeeManagementSystem.saveToFile();
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
    }
    
}