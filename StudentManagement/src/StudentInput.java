import model.InternationalStudent;
import model.RegularStudent;
import model.Student;
import service.StudentService;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StudentInput {

    private final Scanner scanner = new Scanner(System.in);
    private final StudentService service;
    private final ResourceBundle messages;

    public StudentInput(StudentService service) {
        this.service = service;

        messages = ResourceBundle.getBundle(
                "resource.messages",
                new Locale("vi","VN")
        );
    }

    // ==================== NHẬP SỐ NGUYÊN ====================

    public int getInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(
                        messages.getString("input.integer")
                );
            }
        }
    }

    // ==================== NHẬP SỐ THỰC ====================

    public double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(
                        messages.getString("input.number")
                );
            }
        }
    }

    // ==================== THÊM SINH VIÊN ====================

    public void addStudent() {

        System.out.println(
                messages.getString("student.add.title")
        );

        System.out.println(
                messages.getString("student.type.regular")
        );

        System.out.println(
                messages.getString("student.type.international")
        );

        System.out.print(
                messages.getString("student.type.choose")
        );

        int type = getInt();

        if (type != 1 && type != 2) {
            System.out.println(
                    messages.getString("student.type.invalid")
            );
            return;
        }

        System.out.print(
                messages.getString("input.id")
        );
        String id = scanner.nextLine();

        System.out.print(
                messages.getString("input.fullname")
        );
        String fullname = scanner.nextLine();

        System.out.print(
                messages.getString("input.age")
        );
        int age = getInt();

        System.out.print(
                messages.getString("input.major")
        );
        String major = scanner.nextLine();

        System.out.print(
                messages.getString("input.gpa")
        );
        double gpa = getDouble();

        System.out.print(
                messages.getString("input.studentId")
        );
        String studentId = scanner.nextLine();

        System.out.print(
                messages.getString("input.credits")
        );
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

            System.out.print(
                    messages.getString("input.serviceFee")
            );

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

        System.out.println(
                messages.getString("student.add.success")
        );
    }

    // ==================== XÓA SINH VIÊN ====================

    public void removeStudent() {

        System.out.println(
                messages.getString("student.remove.title")
        );

        System.out.print(
                messages.getString("input.studentId")
        );

        String studentId = scanner.nextLine();

        Student student = service.findStudent(studentId);

        if (student == null) {
            System.out.println(
                    messages.getString("student.notFound")
            );
            return;
        }

        service.removeStudent(studentId);

        System.out.println(
                messages.getString("student.remove.success")
        );
    }

    // ==================== TÌM SINH VIÊN ====================

    public void findStudent() {

        System.out.println(
                messages.getString("student.find.title")
        );

        System.out.print(
                messages.getString("input.studentId")
        );

        String studentId = scanner.nextLine();

        Student student = service.findStudent(studentId);

        if (student == null) {
            System.out.println(
                    messages.getString("student.notFound")
            );
            return;
        }

        student.displayInfo();
    }

    // ==================== MENU DANH SÁCH ====================

    public void displayStudentMenu() {

        int choice;

        do {

            System.out.println(
                    messages.getString("student.list.title")
            );

            System.out.println(
                    messages.getString("student.type.regular")
            );

            System.out.println(
                    messages.getString("student.type.international")
            );

            System.out.println(
                    messages.getString("student.list.all")
            );

            System.out.println(
                    messages.getString("menu.back")
            );

            System.out.print(
                    messages.getString("menu.choose")
            );

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
                    System.out.println(
                            messages.getString("menu.invalid")
                    );
            }

        } while (choice != 0);
    }

    // ==================== THANH TOÁN HỌC PHÍ ====================

    public void payTuition() {

        System.out.println(
                messages.getString("tuition.title")
        );

        System.out.print(
                messages.getString("input.studentId")
        );

        String studentId = scanner.nextLine();

        Student student = service.findStudent(studentId);

        if (student == null) {
            System.out.println(
                    messages.getString("student.notFound")
            );
            return;
        }

        System.out.println(
                messages.getString("tuition.amount")
                        + student.calculateTuition()
        );

        System.out.print(
                messages.getString("tuition.pay.amount")
        );

        double amount = getDouble();

        student.payTuition(amount);
    }
}