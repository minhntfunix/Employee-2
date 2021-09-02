


public class Employee extends Staff {
    public int overtimeHours;
    public double Salary;

    public Employee(String ID,String Name, int Age, double heso, String Date,String department,int Days, int hours){
        this.employeeID= ID;
        this.employeeName= Name;
        this.Age= Age;
        this.heSoLuong =heso;
        this.startDate= Date;
        this.Department= department;
        this.vacationDays= Days;
        this.overtimeHours= hours;
        numberOfEmployees +=1;
    }




    public void displayInformation(){
        System.out.printf( "%s %8s  %5d  %5.1f  %18s  %15s %10d  %10d\t\t\t\t  %.1f",
                employeeID,employeeName,Age,heSoLuong,startDate,Department,vacationDays,overtimeHours,calculateSalary());
    }

    public double calculateSalary(){
        //Hệ số lương * 3,000,000 + số giờ làm thêm * 200,000
        Salary= heSoLuong * 3000000 + overtimeHours * 200000;
        return Salary;
    }





}
