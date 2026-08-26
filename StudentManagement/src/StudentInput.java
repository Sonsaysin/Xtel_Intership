import model.InternationalStudent;
import model.RegularStudent;
import model.Student;
import service.StudentService;

import java.util.Scanner;

public class StudentInput {

    private final Scanner scanner = new Scanner(System.in);
    private final StudentService service;

    public StudentInput(StudentService service) {
        this.service = service;
    }

    // ==================== NHẬP SỐ NGUYÊN ====================

    public int getInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số nguyên: ");
            }
        }
    }

    // ==================== NHẬP SỐ THỰC ====================

    public double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số: ");
            }
        }
    }

    // ==================== THÊM SINH VIÊN ====================

    public void addStudent() {

        System.out.println("\n===== THÊM SINH VIÊN =====");
        System.out.println("1. Regular Student");
        System.out.println("2. International Student");
        System.out.print("Chọn loại sinh viên: ");

        int type = getInt();

        if (type != 1 && type != 2) {
            System.out.println("Loại sinh viên không hợp lệ.");
            return;
        }

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Họ tên: ");
        String fullname = scanner.nextLine();

        System.out.print("Tuổi: ");
        int age = getInt();

        System.out.print("Ngành học: ");
        String major = scanner.nextLine();

        System.out.print("GPA: ");
        double gpa = getDouble();

        System.out.print("Mã sinh viên: ");
        String studentId = scanner.nextLine();

        System.out.print("Số tín chỉ: ");
        int numberOfCredits = getInt();

        Student student;

        if (type == 1) {

            student = new RegularStudent(
                    id,
                    fullname,
                    age,
                    major,
                    gpa,
                    studentId,
                    numberOfCredits
            );

        } else {

            System.out.print("Phí dịch vụ: ");
            double serviceFee = getDouble();

            student = new InternationalStudent(
                    id,
                    fullname,
                    age,
                    major,
                    gpa,
                    studentId,
                    numberOfCredits,
                    serviceFee
            );
        }

        service.addStudent(student);

        System.out.println("Thêm sinh viên thành công.");
    }

    // ==================== XÓA SINH VIÊN ====================

    public void removeStudent() {

        System.out.println("\n===== XÓA SINH VIÊN =====");

        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine();

        Student student = service.findStudent(studentId);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }

        service.removeStudent(studentId);

        System.out.println("Xóa sinh viên thành công.");
    }

    // ==================== TÌM SINH VIÊN ====================

    public void findStudent() {

        System.out.println("\n===== TÌM SINH VIÊN =====");

        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine();

        Student student = service.findStudent(studentId);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }

        student.displayInfo();
    }

    // ==================== MENU DANH SÁCH ====================

    public void displayStudentMenu() {

        int choice;

        do {

            System.out.println("\n===== DANH SÁCH SINH VIÊN =====");
            System.out.println("1. Regular Student");
            System.out.println("2. International Student");
            System.out.println("3. Tất cả sinh viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            choice = getInt();

            switch (choice) {

                case 1:
                    service.displayRegularStudents();
                    break;

                case 2:
                    service.displayInternationalStudents();
                    break;

                case 3:
                    service.displayStudents();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    // ==================== THANH TOÁN HỌC PHÍ ====================

    public void payTuition() {

        System.out.println("\n===== THANH TOÁN HỌC PHÍ =====");

        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine();

        Student student = service.findStudent(studentId);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }

        System.out.println("Học phí: " + student.calculateTuition());

        System.out.print("Nhập số tiền thanh toán: ");
        double amount = getDouble();

        student.payTuition(amount);
    }
}