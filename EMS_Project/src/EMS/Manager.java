package EMS;

public class Manager extends Employee {
    private double bonus;

    public Manager(int employeeID, String name, String department, double baseSalary, String performanceRating, double bonus) {
        super(employeeID, name, department, baseSalary, performanceRating);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }

    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Bonus: $" + bonus);
        System.out.println("Total Salary: $" + calculateSalary());
    }
}