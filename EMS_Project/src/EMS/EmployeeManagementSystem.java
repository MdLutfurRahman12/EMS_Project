package EMS;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class EmployeeManagementSystem {
    static ArrayList<Employee> employeeList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Load employees from file on startup
    public static void loadFromFile() {
        File file = new File("employee_data.csv");
        if (!file.exists()) {
            System.out.println("No previous data found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String dept = parts[2];
                double salary = Double.parseDouble(parts[3]);
                String rating = parts[4];
                String role = parts[5];

                Employee emp = null;
                switch (role) {
                    case "manager" -> emp = new Manager(id, name, dept, salary, rating, 0);
                    case "regular" -> emp = new RegularEmployee(id, name, dept, salary, rating, 0);
                    case "intern" -> emp = new Intern(id, name, dept, salary, rating, "Unknown", 0);
                }
                if (emp != null) employeeList.add(emp);
            }
            System.out.println("Employee data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Save employees to file
    public static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("employee_data.csv"))) {
            for (Employee emp : employeeList) {
                if (emp instanceof Manager m) {
                    pw.println(m.getEmployeeID() + "," + m.getName() + "," + m.getDepartment() + "," + m.getBaseSalary() + "," + m.getPerformanceRating() + ",manager," + m.calculateSalary());
                } else if (emp instanceof RegularEmployee r) {
                    pw.println(r.getEmployeeID() + "," + r.getName() + "," + r.getDepartment() + "," + r.getBaseSalary() + "," + r.getPerformanceRating() + ",regular," + r.calculateSalary());
                } else if (emp instanceof Intern i) {
                    pw.println(i.getEmployeeID() + "," + i.getName() + "," + i.getDepartment() + "," + i.getBaseSalary() + "," + i.getPerformanceRating() + ",intern," + i.calculateSalary());
                }
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Add employee (used by both GUI and TBI)
    public static void addEmployee(Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            System.out.print("Enter Base Salary: ");
            double baseSalary = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Performance Rating: ");
            String rating = sc.nextLine();

            System.out.print("Enter Role (Manager / Regular / Intern): ");
            String role = sc.nextLine().toLowerCase();

            Employee emp = null;

            switch (role) {
                case "manager" -> {
                    System.out.print("Enter Bonus: ");
                    double bonus = Double.parseDouble(sc.nextLine());
                    emp = new Manager(id, name, dept, baseSalary, rating, bonus);
                }
                case "regular" -> {
                    System.out.print("Enter Allowance: ");
                    double allowance = Double.parseDouble(sc.nextLine());
                    emp = new RegularEmployee(id, name, dept, baseSalary, rating, allowance);
                }
                case "intern" -> {
                    System.out.print("Enter University: ");
                    String university = sc.nextLine();
                    System.out.print("Enter Duration (months): ");
                    int duration = Integer.parseInt(sc.nextLine());
                    emp = new Intern(id, name, dept, baseSalary, rating, university, duration);
                }
                default -> {
                    System.out.println("Invalid role. Employee not added.");
                    return;
                }
            }

            employeeList.add(emp);
            saveToFile();
            System.out.println("Employee added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // View all employees
    public static void viewAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees in the system.");
        } else {
            for (Employee emp : employeeList) {
                System.out.println("-----------------------------");
                emp.displayEmployeeInfo();
            }
        }
    }

    // Search employee by ID using Binary Search
    public static void searchEmployee(Scanner sc) {
        try {
            System.out.print("Enter Employee ID to search: ");
            int id = Integer.parseInt(sc.nextLine());

            // Sort by ID for Binary Search
            employeeList.sort((e1, e2) -> Integer.compare(e1.getEmployeeID(), e2.getEmployeeID()));
            Employee emp = binarySearch(id);

            if (emp != null) {
                System.out.println("-----------------------------");
                emp.displayEmployeeInfo();
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching employee: " + e.getMessage());
        }
    }

    // Delete employee
    public static void deleteEmployee(Scanner sc) {
        try {
            System.out.print("Enter Employee ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());
            boolean removed = employeeList.removeIf(emp -> emp.getEmployeeID() == id);

            if (removed) {
                saveToFile();
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    // Sort employees by salary using QuickSort
    public static void sortEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to sort.");
            return;
        }
        quickSort(employeeList, 0, employeeList.size() - 1);
        System.out.println("Employees sorted by salary.");
        viewAllEmployees();
    }

    // QuickSort implementation
    public static void quickSort(ArrayList<Employee> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Employee> list, int low, int high) {
        double pivot = list.get(high).calculateSalary();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).calculateSalary() <= pivot) {
                i++;
                Employee temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Employee temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    // Binary Search implementation
    public static Employee binarySearch(int id) {
        int low = 0, high = employeeList.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (employeeList.get(mid).getEmployeeID() == id) return employeeList.get(mid);
            if (employeeList.get(mid).getEmployeeID() < id) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}