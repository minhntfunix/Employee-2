/*mã nhân viên, tên nhân viên, tuổi nhân viên,
        hệ số lương, ngày vào làm,
        bộ phận làm việc, số ngày nghỉ phép*/

public abstract class Staff implements ICalculator{
    public double Salary;
    protected String employeeID;
    protected String employeeName;
    protected int Age;
    protected double heSoLuong;
    protected String startDate;
    protected String Department;
    protected int vacationDays;
    public static int numberOfEmployees;

    abstract void displayInformation();

}
