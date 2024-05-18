//package Entity;

public class Employee extends Person {

    
    private String salary;
    private String position;
    private int workingHour;

    public Employee(double id, String name, double age, String gender,  String salary, String position, int workingHour) {
        super(id, name, age, gender);
        
        this.salary = salary;
        this.position = position;
        this.workingHour = workingHour;
    }


    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setWorkingHour(int workingHour) {
        this.workingHour = workingHour;
    }

    public int getWorkingHour() {
        return workingHour;
    }

    public void showInfo() {
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Age: " + age);
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Gender: " + gender);
        System.out.println("Employee Salary: " + salary);
        System.out.println("Employee Position: " + position);
        System.out.println("Employee Working Hour: " + workingHour);
    }
}
