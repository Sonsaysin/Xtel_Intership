package validation;

public final class StudentValidation {

    private StudentValidation() {
        // Không cho tạo object
    }

    public static void validateStudentId(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Mã sinh viên không được để trống");
        }
    }

    public static void validateMajor(String major) {
        if (major == null || major.isBlank()) {
            throw new IllegalArgumentException("Ngành học không được để trống");
        }
    }

    public static void validateGpa(double gpa) {
        if (gpa < 0 || gpa > 4) {
            throw new IllegalArgumentException(
                    "GPA phải nằm trong khoảng từ 0 đến 4"
            );
        }
    }

    public static void validateNumberOfCredits(int numberOfCredits) {
        if (numberOfCredits <= 0) {
            throw new IllegalArgumentException(
                    "Số tín chỉ phải lớn hơn 0"
            );
        }
    }

    public static void validateServiceFee(double serviceFee) {
        if (serviceFee < 0) {
            throw new IllegalArgumentException(
                    "Phí dịch vụ không được âm"
            );
        }
    }
}
