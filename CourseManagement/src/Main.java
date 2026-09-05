import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(System.in))) {

            CourseManagement courseManagement =
                    new CourseManagement(reader);

            while (true) {

                System.out.println("\n===== COURSE MANAGEMENT =====");
                System.out.println("1. Add course");
                System.out.println("2. Read and display courses");
                System.out.println("3. Search course by tag");
                System.out.println("0. Exit");
                System.out.print("Choose: ");

                String choice = reader.readLine();

                switch (choice) {

                    case "1":
                        courseManagement.addCourse();
                        break;

                    case "2":
                        courseManagement.readAndDisplay();
                        break;

                    case "3":
                        courseManagement.searchCoursesToFile();
                        break;

                    case "0":
                        System.out.println("Program exited.");
                        return;

                    default:
                        System.out.println(
                                "Invalid choice!"
                        );
                }
            }

        } catch (Exception e) {

            CourseLogger.error(
                    "Unexpected error in Main.",
                    e
            );

            System.out.println(
                    "Application error: " +
                            e.getMessage()
            );
        }
    }
}

