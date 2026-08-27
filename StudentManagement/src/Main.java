import i18n.Message;
import service.StudentService;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Tiếng Việt");
        System.out.println("2. English");
        System.out.print("Chọn ngôn ngữ / Choose language: ");

        int language = Integer.parseInt(scanner.nextLine());

        if (language == 2) {
            Message.setLocale(Locale.US);
        } else {
            Message.setLocale(new Locale("vi","VN"));
        }

        StudentService service = new StudentService();
        StudentInput input = new StudentInput(service);

        int choice;

        do {
            System.out.println("\n" + Message.get("app.title"));
            System.out.println(Message.get("menu.add"));
            System.out.println(Message.get("menu.remove"));
            System.out.println(Message.get("menu.find"));
            System.out.println(Message.get("menu.display"));
            System.out.println(Message.get("menu.pay"));
            System.out.println(Message.get("menu.exit"));

            System.out.print(Message.get("prompt.choice"));

            choice = input.getInt();

            switch (choice) {
                case 1 -> input.addStudent();
                case 2 -> input.removeStudent();
                case 3 -> input.findStudent();
                case 4 -> input.displayStudentMenu();
                case 5 -> input.payTuition();
                case 0 -> System.out.println(Message.get("message.exit"));
                default -> System.out.println(
                        Message.get("message.invalid.choice")
                );
            }

        } while (choice != 0);
    }
}