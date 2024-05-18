//package EntityList;
//import Entity.Employee;

public class EmployeeList {
    private Employee employees[];

    public EmployeeList() {
        employees = new Employee[50];
    }

    public EmployeeList(int size) {
        employees = new Employee[size];
    }

    // Show all employees
    public void showAllEmployees() {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employees[i].showInfo();
            }
        }
    }

    // Insert Employee into Array
    public void insertEmployee(Employee e) {
        boolean flag = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = e;
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("Employee added.");
        } else {
            System.out.println("Employee list is full! Failed to add.");
        }
    }

    public void removeEmployeeById(double id) {
        boolean flag = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    employees[i] = null;
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public Employee getEmployeeById(double id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null; // Employee with the given ID is not found
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public void setAllEmployees(Employee[] employees) {
        this.employees = employees;
    }
}
