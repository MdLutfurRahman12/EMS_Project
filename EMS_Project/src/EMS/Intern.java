package EMS;

public class Intern extends Employee {
    private String university;
    private int duration; 

    public Intern(int employeeID, String name, String department, double baseSalary, String performanceRating, String university, int duration) {
        super(employeeID, name, department, baseSalary, performanceRating);
        this.university = university;
        this.duration = duration;
    }

    @Override
    public double calculateSalary() {
        return baseSalary; 
    }

    @Override
    public void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("University: " + university);
        System.out.println("Internship Duration: " + duration + " months");
        System.out.println("Total Salary: $" + calculateSalary());
    }
}