public class Department {
    public String departmentID;
    public String Department;
    public int numOfEmployees;



    public Department(String departmentID, String department, int numOfEmployees) {
        this.departmentID = departmentID;
        this.Department = department;
        this.numOfEmployees = numOfEmployees;
    }

    @Override
    public String toString() {
        return
                "departmentID=" + departmentID +
                ", Department='" + Department + '\'' +
                ", numOfEmployees=" + numOfEmployees
                ;
    }

}