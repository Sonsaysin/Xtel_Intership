import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private List<Course> courses = new ArrayList<>();

    void addCourse(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter Id: ");
            String id = br.readLine();

            System.out.println("Enter title: ");
            String title = br.readLine();

            System.out.println("Enter DurationHours: ");
            float durationHours = Float.parseFloat(br.readLine());

            System.out.println("Enter fee: ");
            float fee = Float.parseFloat(br.readLine());

            System.out.printf("Enter tags: (separated by comma) ");
            String tagInput = br.readLine();
            List<String> tags = new ArrayList<>();
            String[]  tagArr = tagInput.split(",");
            for (String tag : tagArr){
                String cleanTag = tag.trim();

                if (!cleanTag.isEmpty()){
                    tags.add(cleanTag);
                }
            }

            Course course = new Course(id,title,durationHours,fee,tags);
            courses.add(course);
            System.out.printf("Course added successfully !!!!");

        }catch (NumberFormatException e){
            System.out.printf("Duration hour and fee must be valid numbers");
        }catch (IllegalArgumentException e){
            System.out.printf("Error: " + e.getMessage());
        }catch (IOException ex){
            System.out.printf("Error reading input: " + ex.getMessage());
        }
    }

    void saveCoursesToFile(){
        if (courses.isEmpty()){
            System.out.printf("No course to save!");
            return;
        }

        File file = new File("src/courses_data.bat");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for (Course course : courses){
                String tags = String.join("|", course.getTags());

                bw.write(course.getId() + " | " +
                        course.getTitle() + " | " +
                        course.getDurationHours() + " | " +
                        course.getFee() + " | " +
                        tags
                );

                bw.newLine();
            }
            System.out.println("Courses saved successfully! ");
        }catch (Exception e){
            System.out.printf("Error: " + e.getMessage());
        }
    }

    void readAndDisplay(){
        File file = new File("src/courses_data.bat");

        if (!file.exists()) {

            System.out.println(
                    "File not found: courses_data.bat"
            );

            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            System.out.println("===== COURSRE LIST =====");

            while ((line = br.readLine()) != null){
                if (line .trim().isEmpty()){
                    continue;
                }

                String[] data = line.split(",", 5);

                if (data.length != 5){
                    System.out.println("Invalid data: " + line);
                    continue;
                }
                System.out.println("--------------------------------------------------");
                System.out.println("ID: " + data[0]);
                System.out.println("Title: " + data[1]);
                System.out.println("Duration: " + data[2]);
                System.out.println("Fee: " + data[3]);
                System.out.println("Tags: " + data[4].replace("|",","));
                System.out.println("--------------------------------------------------");
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    void searchCoursesToFile() {
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Enter tag to search: ");

            String searchTag = br.readLine().trim();

            boolean found = false;

            for (Course course : courses) {

                List<String> tags = course.getTags();

                for (String tag : tags) {

                    if (tag.equalsIgnoreCase(searchTag)) {

                        System.out.println("-------------------------");

                        System.out.println("ID: " + course.getId());

                        System.out.println("Title: " + course.getTitle());

                        System.out.println("Duration Hours: " + course.getDurationHours());

                        System.out.println("Fee: $" + course.getFee());

                        System.out.println("Tags: " + course.getTags());

                        found = true;

                        // Course already found
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("NOT_FOUND");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error reading input: " + e.getMessage()
            );
        }
    }
}
