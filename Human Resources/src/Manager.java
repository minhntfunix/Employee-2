//Quản lý: Hệ số lương * 5,000,000 + lương trách nhiệm


public class Manager extends Staff{
    public String positionName;
    public double Salary;


    public Manager(String id, String name, int age, double heso, String date, String department, int days, String s) {
        this.employeeID= id;
        this.employeeName= name;
        this.Age= age;
        this.heSoLuong =heso;
        this.startDate= date;
        this.Department= department;
        this.vacationDays= days;
        this.positionName= s;
        numberOfEmployees+=1;

    }

    void displayInformation(){
        System.out.printf( "%s %8s  %5s  %5.1f  %18s  %15s %8d\t\t  %s\t  %.1f",
                employeeID,employeeName,Age,heSoLuong,startDate,Department,vacationDays,positionName,calculateSalary());
    }


    //Hệ số lương * 5,000,000 + lương trách nhiệm
    // Business Leader = 8,000,000
    //Project Leader = 5,000,000
    //Technical Leader = 6,000,000
    @Override
    public double calculateSalary() {
        if (positionName.equalsIgnoreCase("Business Leader") )
            Salary = heSoLuong * 5000000 + 8000000;
        else if (positionName.equalsIgnoreCase("Project Leader"))
            Salary= heSoLuong * 5000000 + 5000000;
        else if (positionName.equalsIgnoreCase("Technical Leader")) {
            Salary = heSoLuong * 5000000 + 6000000;
        }

        return Salary;
    }

}
