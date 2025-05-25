package EMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private ArrayList<Employee> employeeList;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField searchField;
    private JLabel statusLabel;
    private JTextField idField, nameField, deptField, salaryField, ratingField, bonusField, allowanceField, universityField, durationField;
    private JComboBox<String> roleComboBox;

    public EmployeeGUI(ArrayList<Employee> employeeList) {
        this.employeeList = (employeeList != null) ? employeeList : new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Employee Management System - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus Look and Feel not available. Using default.");
        }

        // Table setup
        String[] columnNames = {"ID", "Name", "Department", "Base Salary", "Performance Rating", "Total Salary"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(100, 150, 200)); // Header color
        table.getTableHeader().setForeground(Color.WHITE);

        // Enable sorting and filtering
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(tableScrollPane, BorderLayout.CENTER);

        // North panel: Search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(220, 220, 220));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }
        });
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // South panel: Buttons and status
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(220, 220, 220));
        southPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(new Color(220, 220, 220));
        JButton addButton = createStyledButton("Add Employee", "Add a new employee");
        JButton viewButton = createStyledButton("View All", "Refresh the employee list");
        JButton deleteButton = createStyledButton("Delete Selected", "Delete the selected employee");
        JButton sortButton = createStyledButton("Sort by Salary", "Sort employees by total salary");
        JButton clearButton = createStyledButton("Clear Form", "Clear the input form");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(clearButton);

        // Status label
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(new Color(0, 128, 0)); // Green for success messages

        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.add(statusLabel, BorderLayout.SOUTH);

        // East panel: Form for adding employees
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Employee"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form fields
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(15);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        JLabel deptLabel = new JLabel("Department:");
        deptField = new JTextField(15);
        JLabel salaryLabel = new JLabel("Base Salary:");
        salaryField = new JTextField(15);
        JLabel ratingLabel = new JLabel("Performance Rating:");
        ratingField = new JTextField(15);
        JLabel roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(new String[]{"Manager", "Regular", "Intern"});
        roleComboBox.addActionListener(e -> updateFormFields());
        JLabel bonusLabel = new JLabel("Bonus:");
        bonusField = new JTextField(15);
        JLabel allowanceLabel = new JLabel("Allowance:");
        allowanceField = new JTextField(15);
        JLabel universityLabel = new JLabel("University:");
        universityField = new JTextField(15);
        JLabel durationLabel = new JLabel("Duration (months):");
        durationField = new JTextField(15);

        // Layout form fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(deptLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(deptField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(salaryLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(salaryField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(ratingLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ratingField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(roleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(roleComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(bonusLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(bonusField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(allowanceLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(allowanceField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(universityLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(universityField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(durationLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(durationField, gbc);

        // Initially hide role-specific fields
        updateFormFields();

        add(southPanel, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.EAST);

        // Add action listeners
        addButton.addActionListener(e -> addEmployeeGUI());
        viewButton.addActionListener(e -> updateTable());
        deleteButton.addActionListener(e -> deleteSelectedEmployee());
        sortButton.addActionListener(e -> sortEmployees());
        clearButton.addActionListener(e -> clearForm());

        updateTable();
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 150, 200));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        return button;
    }

    private void updateFormFields() {
        String role = (String) roleComboBox.getSelectedItem();
        boolean isManager = role.equals("Manager");
        boolean isRegular = role.equals("Regular");
        boolean isIntern = role.equals("Intern");

        bonusField.setVisible(isManager);
        allowanceField.setVisible(isRegular);
        universityField.setVisible(isIntern);
        durationField.setVisible(isIntern);
        bonusField.setEnabled(isManager);
        allowanceField.setEnabled(isRegular);
        universityField.setEnabled(isIntern);
        durationField.setEnabled(isIntern);
    }

    private void addEmployeeGUI() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            double baseSalary = Double.parseDouble(salaryField.getText().trim());
            String rating = ratingField.getText().trim();
            String role = (String) roleComboBox.getSelectedItem();

            if (name.isEmpty() || dept.isEmpty() || rating.isEmpty()) {
                showStatus("Please fill in all required fields.", false);
                return;
            }

            Employee emp = null;
            switch (role) {
                case "Manager":
                    double bonus = Double.parseDouble(bonusField.getText().trim());
                    emp = new Manager(id, name, dept, baseSalary, rating, bonus);
                    break;
                case "Regular":
                    double allowance = Double.parseDouble(allowanceField.getText().trim());
                    emp = new RegularEmployee(id, name, dept, baseSalary, rating, allowance);
                    break;
                case "Intern":
                    String university = universityField.getText().trim();
                    int duration = Integer.parseInt(durationField.getText().trim());
                    emp = new Intern(id, name, dept, baseSalary, rating, university, duration);
                    break;
            }

            employeeList.add(emp);
            EmployeeManagementSystem.saveToFile();
            updateTable();
            showStatus("Employee added successfully!", true);
        } catch (NumberFormatException e) {
            showStatus("Invalid numeric input. Please check your entries.", false);
        } catch (Exception e) {
            showStatus("Error adding employee: " + e.getMessage(), false);
        }
    }

    private void deleteSelectedEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showStatus("Please select an employee to delete.", false);
            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        int id = (int) tableModel.getValueAt(modelRow, 0);
        boolean removed = employeeList.removeIf(emp -> emp.getEmployeeID() == id);

        if (removed) {
            EmployeeManagementSystem.saveToFile();
            updateTable();
            showStatus("Employee deleted successfully!", true);
        } else {
            showStatus("Employee not found.", false);
        }
    }

    private void sortEmployees() {
        if (employeeList.isEmpty()) {
            showStatus("No employees to sort.", false);
            return;
        }
        EmployeeManagementSystem.quickSort(employeeList, 0, employeeList.size() - 1);
        updateTable();
        showStatus("Employees sorted by salary.", true);
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        deptField.setText("");
        salaryField.setText("");
        ratingField.setText("");
        bonusField.setText("");
        allowanceField.setText("");
        universityField.setText("");
        durationField.setText("");
        roleComboBox.setSelectedIndex(0);
        showStatus("Form cleared.", true);
    }

    private void filterTable() {
        String text = searchField.getText().trim();
        if (text.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Employee emp : employeeList) {
            Object[] row = {emp.getEmployeeID(), emp.getName(), emp.getDepartment(), emp.getBaseSalary(),
                    emp.getPerformanceRating(), emp.calculateSalary()};
            tableModel.addRow(row);
        }
        showStatus("Table updated.", true);
    }

    private void showStatus(String message, boolean isSuccess) {
        statusLabel.setText(message);
        statusLabel.setForeground(isSuccess ? new Color(0, 128, 0) : new Color(200, 0, 0));
    }
}