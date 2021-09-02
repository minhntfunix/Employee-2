import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HumanResources {
    public static Scanner input = new Scanner(System.in);
    //public static String []a= {"Business Leader", "Project Leader", "Technical Leader"};
    public static ArrayList<Staff> list = new ArrayList<>();        //arraylist của Employee và Manager
    public static ArrayList<Department> list1 = new ArrayList<>();   //arraylist của Department

    public static void main(String args[]) {
        //add sẵn department mẫu cho list
        list1.add(new Department("1001", "Accounting", 0));
        list1.add(new Department("1002", "Marketing", 0));
        list1.add(new Department("1003", "Operations", 0));

        int choose;
        Scanner scan = new Scanner(System.in);

        do {
            showMenu();
            System.out.print("Choose number from menu:");
            choose = scan.nextInt();
            switch (choose) {
                case 1:
                    //Hien thi danh sach tat ca nhan vien
                    hienThi();
                    break;
                case 2:
                    // Hien thi tổng nhan viên cho mỗi department
                    System.out.println("\nHiển thị tổng số nhan viên cho mỗi department\n");
                    for (int i = 0; i < list1.size(); i++) {
                        System.out.println(list1.get(i));
                    }
                    System.out.println();
                    break;
                case 3:
                    //Hiển thị nhân viên theo từng bộ phận
                    showByDepartment();
                    break;
                case 4:
                    // Add nhân viên mới
                    addEmployee();
                    break;
                case 5:
                    //Tìm kiếm nhân viên theo ID hoặc name
                    showByNameOrID();
                    System.out.println();
                    break;
                case 6:
                    //In ra danh sách nhân viên
                    printSalary();
                    break;
                case 7:

                    //Hiển thị danh sách theo thứ tự tăng dần
                    sortBySalary();
                    break;
                case 8:
                    //Thoát
                    System.out.println("Goodbye!!!");
                    break;
                default:
                    System.out.println("Nhap sai!!!!");
                    break;
            }

        } while (choose != 8);


    }


    //hàm add nhân viên mới
    public static void addEmployee() {
        int i;
        do {
            System.out.print("\nNhập (1.Nhân viên thông thường , 2. Quản lý): ");
            i = input.nextInt();

            //Chỉ chấp nhận user nhập 1 hoặc 2
            if (i != 1 && i != 2) {
                System.out.println("You entered wrong number. Enter again");
            }
        } while (i != 1 && i != 2);
        input.nextLine();
        System.out.println(" Nhập info nhân vien: ");
        System.out.print("Mã nhân viên: ");
        String ID = input.nextLine();
        System.out.print("Tên nhân viên: ");
        String Name = input.nextLine();
        System.out.print("Tuổi nhân viên: ");
        int Age = input.nextInt();
        System.out.print("Hệ số lương: ");
        double heso = input.nextDouble();
        input.nextLine();
        String d;
        Boolean x;
        do {
            System.out.print("Ngày vào làm (dd/mm/yyyy): ");
            d = input.nextLine();
            x = validate(d);        //kiểm tra format ngày nhập vào
            if (x) break;
            else {
                System.out.println("You enter wrong date format. Enter again");
            }
        } while (!x);

        String department;
        boolean y = false;
        do {
            System.out.print("Bộ phận làm việc: ");
            department = input.nextLine();

            //Kiểm tra nếu bộ phận của nhân viên mới nhập vào có khớp với danh sách bộ phận hay ko
            for (int j = 0; j < list1.size(); j++) {
                if (!list1.get(j).Department.equals(department)) {
                    y = false;
                } else {
                    //Nếu khớp thì cộng 1 vô tổng số employee của từng department
                    list1.get(j).numOfEmployees += 1;
                    y = true;
                    break;
                }
            }
            if (!y)
                System.out.println("You enter wrong department or wrong format. Enter again:");
        } while (!y);


        System.out.print("Số ngày nghỉ phép: ");
        int days = input.nextInt();
        input.nextLine();

        if (i == 1) {
            System.out.print("Số giờ overtime: ");
            int hours = input.nextInt();

            //Add info employee vô ArrayList
            list.add(new Employee(ID, Name, Age, heso, d, department, days, hours));
            System.out.println("Input info successful");
        }

        String position;
        String[] a = {"Business Leader", "Project Leader", "Technical Leader"};
        if (i == 2) {
            y = false;
            do {
                //Kiểm tra có nhập đúng chức vụ đã có hay không
                System.out.print("Chức vụ: ");
                position = input.nextLine();
                for (int m = 0; m < a.length; m++) {
                    if (position.equals(a[m])) {
                        y = true;
                        break;
                    }
                }
                if (!y)
                    System.out.println("You enter wrong position or wrong format. Enter again:");
            } while (!y);

            //add info vào ArrayList
            list.add(new Manager(ID, Name, Age, heso, d, department, days, position));
            System.out.println("Input info successful");
        }
        System.out.println();
    }

    //hàm hien thi info các nhan vien
    public static void hienThi() {
        System.out.println("\nHien thi danh sach tat ca nhan vien:\n");
        System.out.println("ID      Name     Age  heSoLuong     startDate        Department    vacationDays  Overtime/Position     Salary  ");
        for (Staff staff : list) {
            staff.displayInformation();
            System.out.println();
        }
        System.out.println();

    }

    public static void hienThi(String Department){
        for (int i=0; i<list.size(); i++){
            if (list.get(i).Department.equalsIgnoreCase(Department)){
                System.out.println(list.get(i));
            }

        }

    }

    //kiem tra format date
    public static boolean validate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            formatter.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //hàm hiển thị Menu chính
    public static void showMenu() {
        System.out.println("1. Hiển thị danh sách nhân viên hiện có trong công ty");
        System.out.println("2. Hiển thị các bộ phận trong công ty");
        System.out.println("3. Hiển thị các nhân viên theo từng bộ phận");
        System.out.println("4. Thêm nhân viên mới vào công ty: ");
        System.out.println("5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên");
        System.out.println("6. Hiển thị bảng lương của nhân viên toàn công ty");
        System.out.println("7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần");
        System.out.println("8. Thoát");
    }


    //hien thi nhan vien theo từng department
    public static void showByDepartment() {
        System.out.println("\nHiển thị các nhân viên theo từng department\n");

        //duyệt từng department trong danh sách bộ phận
        for (int i = 0; i < list1.size(); i++) {
            System.out.printf("\nBộ phận %s: \n\n", list1.get(i).Department);

            //duyệt từng phần tử trong ArrayList của Staff
            for (int j = 0; j < list.size(); j++)
                if (list.get(j).Department.equals(list1.get(i).Department)) {
                    System.out.printf("ID:%s    Name:%s     Salary:%.1f\n", list.get(j).employeeID,list.get(j).employeeName,list.get(j).calculateSalary() );

                }

        }
        System.out.println();
    }


    //hàm tìm nhân viên theo name hoặc ID
    public static void showByNameOrID() {
        System.out.print("Enter name or ID to search: ");
        boolean x= false;
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        if (list.size() == 0) {
            System.out.println("Chưa có nhập thông tin. Please input info");
        } else {
            System.out.println("ID     Name     Age  heSoLuong     startDate        Department    vacationDays  Overtime/Position     Salary  ");
            //duyệt từng phần tử của ArrayList của Staff
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).employeeName.equals((s))) {
                    list.get(j).displayInformation();
                    System.out.println();
                    x= true;
                } else if (list.get(j).employeeID.equals((s))) {
                    list.get(j).displayInformation();
                    System.out.println();
                    x=true;
                }else if (!x && j==list.size()-1){
                    System.out.println("Không tìm thấy");
                }

                }
            }
    }

    //hàm in ra lương nhân viên
    public static void printSalary() {
        System.out.println("\nIn ra bảng lương của các nhân vien:\n");

        //duyện từng phần tử của ArrayList của Staff
        for (int j = 0; j < list.size(); j++) {
            System.out.printf("ID: %s  Name: %s\t   Department: %s\t   Salary: %.1f\n", list.get(j).employeeID, list.get(j).employeeName,list.get(j).Department, list.get(j).calculateSalary());
        }
        System.out.println();
    }

    //hiển thị bảng lương theo thứ tự tăng dần
    public static void sortBySalary() {
        System.out.println("\nHiển thị bảng lương của nhân viên theo thứ tự tăng dần\n");
        Collections.sort(list, new Comparator<Staff>() {
            @Override
            public int compare(Staff o1, Staff o2) {
                if (o1.calculateSalary() > o2.calculateSalary())
                    return 1;
                else
                    return -1;

            }
        });
        for (int i=0; i< list.size(); i++){
            System.out.printf("ID: %s  Name: %s\t   Department: %s\t    Salary: %.1f\n", list.get(i).employeeID, list.get(i).employeeName,list.get(i).Department, list.get(i).calculateSalary());
        }

        System.out.println();
    }


}