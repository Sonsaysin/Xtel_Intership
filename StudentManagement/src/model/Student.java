package model;

import interfaces.Payable;
import validation.StudentValidation;

public abstract class Student extends Person implements Payable {

    private String studentId;
    private String major;
    private double gpa;
    private double paidAmount;

    public Student(String id, String fullname, int age,
                   String major, double gpa, String studentId) {

        super(id, fullname, age);

        setStudentId(studentId);
        setMajor(major);
        setGpa(gpa);
    }

    // ==================== GETTER / SETTER ====================

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        StudentValidation.validateStudentId(studentId);
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        StudentValidation.validateMajor(major);
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        StudentValidation.validateGpa(gpa);
        this.gpa = gpa;
    }

    // ==================== BEHAVIOR ====================

    // Hành vi chung của mọi sinh viên
    public void study() {
        System.out.println(getFullname() + " đang học");
    }

    // Mỗi loại sinh viên có loại khác nhau
    public abstract String getStudentType();

    // ==================== PAYMENT ====================

    // Logic thanh toán chung cho mọi sinh viên
    @Override
    public void payTuition(double amount) {

        if (amount <= 0) {
            System.out.println("Số tiền không hợp lệ.");
            return;
        }

        paidAmount += amount;

        double tuition = calculateTuition();
        double remaining = tuition - paidAmount;

        if (remaining > 0) {

            System.out.println("Đã thanh toán: " + paidAmount);
            System.out.println("Còn thiếu: " + remaining);

        } else if (remaining == 0) {

            System.out.println("Đã thanh toán đủ học phí.");

        } else {

            System.out.println("Đã thanh toán đủ.");
            System.out.println("Tiền dư: " + (-remaining));
        }
    }

    // ==================== DISPLAY ====================

    public void displayInfo() {

        System.out.println("ID: " + getId());
        System.out.println("Họ tên: " + getFullname());
        System.out.println("Tuổi: " + getAge());
        System.out.println("Mã sinh viên: " + studentId);
        System.out.println("Ngành học: " + major);
        System.out.println("GPA: " + gpa);
        System.out.println("Loại sinh viên: " + getStudentType());
        System.out.println("Học phí: " + calculateTuition());
    }
}