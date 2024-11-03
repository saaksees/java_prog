package saakshiapplication;

abstract class Employee {
    private int eId;
    private String eName;
    private String des;

    
    public Employee(int eid, String ename, String d) {
        this.eId = eid;
        this.eName = ename;
        this.des = d;
    }

    
    public abstract double calBonus();

   
    public void display() {
        System.out.println("Employee ID: " + eId);
        System.out.println("Name: " + eName);
        System.out.println("Designation: " + des);
        System.out.println("Bonus: " + calBonus());
    }
}

class HourlyEmp extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmp(int eId, String eName, String des, double hourlyRate, int hoursWorked) {
        super(eId, eName, des);  
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    
    public double calBonus() {
        return (hourlyRate * hoursWorked) * 0.05;
    }

    
    public double calWeeklySalary() {
        return hourlyRate * hoursWorked;
    }


    public void display() {
        super.display();  
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Weekly Salary: " + calWeeklySalary());
        System.out.println("Annual Earnings: " + (calWeeklySalary() * 52));  
    }
}

class SalariedEmp extends Employee {
    protected double monthlySalary;

    public SalariedEmp(int eId, String eName, String des, double monthlySalary) {
        super(eId, eName, des);  
        this.monthlySalary = monthlySalary;
    }

    
    public double calBonus() {
        return monthlySalary * 0.1;
    }

   
    public double calWeeklySalary() {
        return monthlySalary / 4;  
    }

   
    public void display() {
        super.display();  
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Weekly Salary: " + calWeeklySalary());
        System.out.println("Annual Earnings: " + (monthlySalary * 12));  
    }
}

class ExecutiveEmp extends SalariedEmp {
    private double bonusPer;

    public ExecutiveEmp(int eId, String eName, String des, double monthlySalary, double bonusPercentage) {
        super(eId, eName, des, monthlySalary);  
        this.bonusPer = bonusPercentage;
    }


    public double calBonus() {
        double baseBonus = super.calBonus();  
        return baseBonus + (getMonthlySalary() * (bonusPer / 100));
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

   
    public void display() {
        super.display();  // Use super to call the parent display method
        System.out.println("Bonus Percentage: " + bonusPer + "%");
        System.out.println("Annual Earnings: " + ((monthlySalary * 12) + calBonus()));  // Include annual bonus in earnings
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        
        HourlyEmp e1 = new HourlyEmp(1, "Aditi", "Developer", 20.0, 40);
        SalariedEmp e2 = new SalariedEmp(2, "Samyuktaa", "Manager", 3000.0);
        ExecutiveEmp e3 = new ExecutiveEmp(3, "Saakshi", "CEO", 5000.0, 15.0);

        
        System.out.println(" Employee Details:");
        e1.display();
        System.out.println();

        e2.display();
        System.out.println();

        e3.display();
        System.out.println();

        
        double totalPayroll = (e1.calWeeklySalary() * 52) + (e2.monthlySalary * 12) + (e3.getMonthlySalary() * 12 + e3.calBonus());
        System.out.println("Total Payroll for All Employees: " + totalPayroll);
    }
}
