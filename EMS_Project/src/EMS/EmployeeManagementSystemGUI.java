package EMS;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class EmployeeManagementSystemGUI {
    public static void main(String[] args) {
        // Prompt user to choose interface type
        Object[] options = {"GUI interface", "Text-based interface"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "Please select your preferred mode of interaction:",
            "Choose Interface",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        try {
            // Load employee data from file
            EmployeeManagementSystem.loadFromFile();
            // Ensure employeeList is initialized to avoid NullPointerException
            if (EmployeeManagementSystem.employeeList == null) {
                EmployeeManagementSystem.employeeList = new ArrayList<>();
                System.out.println("Employee list was null; initialized as empty ArrayList.");
            }

            // Launch based on user choice
            if (choice == JOptionPane.YES_OPTION) {
                // Launch GUI interface
                SwingUtilities.invokeLater(() -> new EmployeeGUI(EmployeeManagementSystem.employeeList));
            } else if (choice == JOptionPane.NO_OPTION) {
                // Launch text-based interface
                EmployeeManagementSystemTBI.main(args); // Pass args to TBI main method
            } else {
                // User canceled or closed the dialog
                System.out.println("Application terminated by user.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.err.println("Error starting the application: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to start the application: " + e.getMessage(),
                    "Startup Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}