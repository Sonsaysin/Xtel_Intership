package service;

import model.InternationalStudent;
import model.RegularStudent;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    // Thêm sinh viên
    public void addStudent(Student student) {
        students.add(student);
    }

    // Xóa sinh viên theo mã
    public void removeStudent(String studentId) {
        students.removeIf(student ->
                student.getStudentId().equals(studentId)
        );
    }

    // Tìm sinh viên theo mã
    public Student findStudent(String studentId) {

        for (Student student : students) {

            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    // Hiển thị danh sách sinh viên
    public void displayStudents() {

        for (Student student : students) {
            student.displayInfo();
            System.out.println("--------------------");
        }
    }

    // Thanh toán học phí
    public void payTuition(String studentId, double amount) {

        Student student = findStudent(studentId);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }

        student.payTuition(amount);
    }

    public void displayRegularStudents() {

        boolean found = false;

        for (Student student : students) {

            if (student instanceof RegularStudent) {
                student.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có Regular Student.");
        }
    }

    public void displayInternationalStudents() {

        boolean found = false;

        for (Student student : students) {

            if (student instanceof InternationalStudent) {
                student.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có International Student.");
        }
    }
}