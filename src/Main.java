import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    public double getSalary() {
        return calculateSalary();
    }
    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }


    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public double displaySalary(int id){
        for(Employee employee:employeeList){
            if(employee.getId()== id){
                return employee.getSalary();
            }
        }
        return -1.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        while(true){

            System.out.println("Choose options: ");

            System.out.println("1.Add Employee Details");
            System.out.println("2.Remove Employee");
            System.out.println("3.Display All employee");
            System.out.println("4.Display Salary");
            System.out.println("5.Stop");

            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                System.out.println("Which type of Employee do you want to add ?");
                System.out.println("1.Full Time Employee");
                System.out.println("2.PartTime Employee ");
                int option = sc.nextInt();
                sc.nextLine();
                if(option==1){
                    System.out.println("Enter Employee's Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Employee's Id:");
                    int id =sc.nextInt();
                    System.out.println("Enter Employee's Income:");
                    double income = sc.nextDouble();
                    sc.nextLine();
                    FullTimeEmployee emp = new FullTimeEmployee(name,id ,income);
                    payrollSystem.addEmployee(emp);
                }else{
                    System.out.println("Enter Employee's Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Employee's Id:");
                    int id =sc.nextInt();
                    System.out.println("Enter Hours Worked:");
                    int hours = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Hourly Rate Worked:");
                    double hRate = sc.nextDouble();
                    sc.nextLine();
                    PartTimeEmployee emp = new PartTimeEmployee(name,id ,hours,hRate );
                    payrollSystem.addEmployee(emp);
                }

            }
            if (choice ==2){
                System.out.println("Do you know the id ?");
                String option = sc.next();
                if (option.equals("Yes") || option.equals("yes")) {
                    System.out.println("Enter id to remove employee: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    payrollSystem.removeEmployee(id);
                }else{
                    payrollSystem.displayEmployees();
                    System.out.println("Enter id to remove employee: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    payrollSystem.removeEmployee(id);
                }

                System.out.println("Employee removed");
            }

            if(choice ==3){
                System.out.println("Employee Details are: ");
                payrollSystem.displayEmployees();
            }
            if(choice == 4){
                System.out.println("Enter Employee id");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("The salary is "+payrollSystem.displaySalary(id));
            }
        }

    }
}
