package validation;

public final class PersonValidation {

    private PersonValidation() {
        // Không cho tạo object
    }

    public static void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID không được để trống");
        }
    }

    public static void validateFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
    }

    public static void validateAge(int age) {
        if (age <= 0 || age > 150) {
            throw new IllegalArgumentException("Tuổi không hợp lệ");
        }
    }
}