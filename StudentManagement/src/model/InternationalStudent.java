package model;

import validation.StudentValidation;

public class InternationalStudent extends Student {

    private int numberOfCredits;
    private double serviceFee;

    public InternationalStudent(String id, String fullname, int age, String major, double gpa, String studentId, int numberOfCredits, double serviceFee) {
        super(id, fullname, age, major, gpa, studentId);
        setNumberOfCredits(numberOfCredits);
        setServiceFee(serviceFee);
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        StudentValidation.validateNumberOfCredits(numberOfCredits);
        this.numberOfCredits = numberOfCredits;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        StudentValidation.validateServiceFee(serviceFee);
        this.serviceFee = serviceFee;
    }

    @Override
    public double calculateTuition() {
        return numberOfCredits * 1500000 + serviceFee;
    }

    @Override
    public String getStudentType() {
        return "International Student";
    }


}
