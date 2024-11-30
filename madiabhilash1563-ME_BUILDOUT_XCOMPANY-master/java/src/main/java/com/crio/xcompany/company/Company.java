package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {
    private String companyName;
    private Employee founder;
    private Map<String, Employee> employeeBook;

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        this.employeeBook = new HashMap<>();
        this.employeeBook.put(founder.getName(), founder);
    }

    public static Company create(String companyName, Employee founder) {
        return new Company(companyName, founder);
    }

    public void registerEmployee(String employeeName, Gender gender) {
        if (!employeeBook.containsKey(employeeName)) {
            employeeBook.put(employeeName, new Employee(employeeName, gender));
        }
    }

    public Employee getEmployee(String employeeName) {
        return employeeBook.get(employeeName);
    }

    public void deleteEmployee(String employeeName) {
        Employee employee = employeeBook.get(employeeName);

        if (employee != null) {
            Employee manager = employee.getManager();
            if (manager != null) {
                manager.getDirectReports().remove(employee);
            }

            employeeBook.remove(employeeName);
        }
    }

    public List<Employee> getDirectReports(String employeeName) {
        Employee manager = employeeBook.get(employeeName);
        return (manager != null) ? manager.getDirectReports() : new ArrayList<>();
    }

    public List<Employee> getTeamMates(String employeeName) {
        Employee employee = employeeBook.get(employeeName);
        return (employee != null) ? employee.getTeamMates() : new ArrayList<>();
    }

    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        Employee manager = employeeBook.get(managerName);
        List<List<Employee>> hierarchy = new ArrayList<>();

        if (manager != null) {
            buildHierarchy(manager, hierarchy, 0);
        }

        return hierarchy;
    }

    private void buildHierarchy(Employee manager, List<List<Employee>> hierarchy, int level) {
        // Ensure the hierarchy list has enough levels
        if (hierarchy.size() <= level) {
            hierarchy.add(new ArrayList<>());
        }

        // Add the manager to the current level
        hierarchy.get(level).add(manager);

        // Recursively add direct reports
        for (Employee employee : manager.getDirectReports()) {
            buildHierarchy(employee, hierarchy, level + 1);
        }
    }

    public void assignManager(String employeeName, String managerName) {
        Employee employee = employeeBook.get(employeeName);
        Employee manager = employeeBook.get(managerName);

        if (employee != null && manager != null) {
            employee.assignManager(manager);

            if (!manager.getDirectReports().contains(employee)) {
                manager.getDirectReports().add(employee);
            }
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public Employee getFounder() {
        return founder;
    }
}
