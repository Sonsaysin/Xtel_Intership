package model;


import validation.StudentValidation;

public class RegularStudent extends Student {

    private int numberOfCredits;

    public RegularStudent(String id, String fullname, int age, String major, double gpa, String studentId, int numberOfCredits) {
        super(id, fullname, age, major, gpa, studentId);
        setNumberOfCredits(numberOfCredits);
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        StudentValidation.validateNumberOfCredits(numberOfCredits);
        this.numberOfCredits = numberOfCredits;
    }

    @Override
    public double calculateTuition() {
        return numberOfCredits * 1000000;
    }

    @Override
    public String getStudentType() {
        return "Regular Student";
    }

}
