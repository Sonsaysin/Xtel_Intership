import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();
        try(BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(System.in))){
            while (true){
                System.out.println();
                System.out.println("============== MENU ==============");
                System.out.println("1. Add Course");
                System.out.println("2. Save Courses to File");
                System.out.println("3. Read and Display Courses");
                System.out.println("4. Search Course by Tags");
                System.out.println("5. Exit");
                System.out.println("==================================");

                System.out.println("Choice: ");
                String choice = br.readLine();

                switch (choice){
                    case "1" -> courseManagement.addCourse();
                    case "2" -> courseManagement.saveCoursesToFile();
                    case "3" -> courseManagement.readAndDisplay();
                    case "4" -> courseManagement.searchCoursesToFile();
                    case "5" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice !!!!!!!!");
                }
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}