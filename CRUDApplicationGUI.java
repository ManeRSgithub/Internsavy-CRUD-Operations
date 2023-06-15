import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private int age;
    private String department;

    public Employee(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

public class CRUDApplicationGUI extends JFrame {
    private List<Employee> employees;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField departmentField;
    private JTextArea outputArea;

    public CRUDApplicationGUI() {
        employees = new ArrayList<>();

        setTitle("CRUD Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("CRUD Application", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(10);

        JLabel departmentLabel = new JLabel("Department:");
        departmentField = new JTextField(10);

        JButton createButton = new JButton("Create");
        JButton readButton = new JButton("Read");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        JLabel outputLabel = new JLabel("Output:");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        inputPanel.add(titleLabel);
        inputPanel.add(new JLabel());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(departmentLabel);
        inputPanel.add(departmentField);

        inputPanel.add(createButton);
        inputPanel.add(readButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String department = departmentField.getText();

                Employee employee = new Employee(name, age, department);
                employees.add(employee);

                outputArea.append("Employee created: " + employee.getName() + "\n");
                clearInputFields();
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Employees:\n");
                for (Employee employee : employees) {
                    outputArea.append("Name: " + employee.getName() +
                            ", Age: " + employee.getAge() +
                            ", Department: " + employee.getDepartment() + "\n");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String department = departmentField.getText();

                boolean found = false;
                for (Employee employee : employees) {
                    if (employee.getName().equals(name)) {
                        employee.setAge(age);
                        employee.setDepartment(department);
                        outputArea.append("Employee updated: " + employee.getName() + "\n");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    outputArea.append("Employee not found!\n");
                }

                clearInputFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();

                boolean removed = employees.removeIf(employee -> employee.getName().equals(name));

                if (removed) {
                    outputArea.append("Employee deleted: " + name + "\n");
                } else {
                    outputArea.append("Employee not found!\n");
                }

                clearInputFields();
            }
        });
    }

    private void clearInputFields() {
        nameField.setText("");
        ageField.setText("");
        departmentField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CRUDApplicationGUI crudGUI = new CRUDApplicationGUI();
                crudGUI.setVisible(true);
            }
        });
    }
}
