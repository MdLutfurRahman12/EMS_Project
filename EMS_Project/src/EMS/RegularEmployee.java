package EMS;

public class RegularEmployee extends Employee {
    private double allowance;

    public RegularEmployee(int employeeID, String name, String department, double baseSalary, String performanceRating, double allowance) {
        super(employeeID, name, department, baseSalary, performanceRating);
        this.allowance = allowance;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + allowance;
    }

    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Allowance: $" + allowance);
        System.out.println("Total Salary: $" + calculateSalary());
    }
}