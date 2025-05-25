package EMS;

public abstract class Employee {
    protected int employeeID;
    protected String name;
    protected String department;
    protected double baseSalary;
    protected String performanceRating;

    public Employee(int employeeID, String name, String department, double baseSalary, String performanceRating) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
        this.performanceRating = performanceRating;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(String performanceRating) {
        this.performanceRating = performanceRating;
    }

    public abstract double calculateSalary();

    public void displayEmployeeInfo() {
        System.out.println("ID: " + employeeID);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Performance Rating: " + performanceRating);
    }
}