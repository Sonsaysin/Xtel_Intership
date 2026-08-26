import service.StudentService;

public class Main {

    public static void main(String[] args) {

        StudentService service = new StudentService();
        StudentInput input = new StudentInput(service);

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Xóa sinh viên");
            System.out.println("3. Tìm sinh viên");
            System.out.println("4. Danh sách sinh viên");
            System.out.println("5. Thanh toán học phí");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            choice = input.getInt();

            switch (choice) {
                case 1 -> input.addStudent();
                case 2 -> input.removeStudent();
                case 3 -> input.findStudent();
                case 4 -> input.displayStudentMenu();
                case 5 -> input.payTuition();
                case 0 -> System.out.println("Thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }
}