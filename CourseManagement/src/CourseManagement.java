import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {

    private final List<Course> courses = new ArrayList<>();

    // Đường dẫn dùng chung
    private final Path dataDir = Path.of("data");
    private final Path file = dataDir.resolve("courses_data.txt");

    private final BufferedReader inputReader;

    public CourseManagement(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }


    // =========================================================
    // ADD COURSE
    // =========================================================

    void addCourse() {

        CourseLogger.add("Start adding course.");

        try {

            System.out.println("Enter Id: ");
            String id = inputReader.readLine();

            System.out.println("Enter title: ");
            String title = inputReader.readLine();

            System.out.println("Enter DurationHours: ");
            float durationHours =
                    Float.parseFloat(inputReader.readLine());

            System.out.println("Enter fee: ");
            float fee =
                    Float.parseFloat(inputReader.readLine());

            System.out.println(
                    "Enter tags: (separated by comma) "
            );

            String tagInput = inputReader.readLine();

            List<String> tags = new ArrayList<>();

            String[] tagArr = tagInput.split(",");

            for (String tag : tagArr) {

                String cleanTag = tag.trim();

                if (!cleanTag.isEmpty()) {
                    tags.add(cleanTag);
                }
            }

            Course course = new Course(
                    id,
                    title,
                    durationHours,
                    fee,
                    tags
            );

            courses.add(course);

            CourseLogger.add(
                    "Added course: ID=" + course.getId()
            );

            System.out.println(
                    "Course added successfully."
            );

        } catch (Exception e) {

            // Ghi vào add_course.txt + error.txt
            CourseLogger.addError(
                    "Failed to add course.",
                    e
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =========================================================
    // SAVE COURSES
    // =========================================================

    void saveCoursesToFile() {

        CourseLogger.save(
                "Start saving courses."
        );

        if (courses.isEmpty()) {

            CourseLogger.save(
                    "No courses to save."
            );

            System.out.println(
                    "No course to save!"
            );

            return;
        }

        try {

            Files.createDirectories(dataDir);

            try (BufferedWriter bw =
                         Files.newBufferedWriter(file)) {

                for (Course course : courses) {

                    if (course.getTags() == null ||
                            course.getTags().isEmpty()) {

                        System.out.println(
                                "Course " +
                                        course.getId() +
                                        " has no tags!"
                        );

                        CourseLogger.save(
                                "WARNING: Course ID=" +
                                        course.getId() +
                                        " has no tags and was skipped."
                        );

                        continue;
                    }

                    String tags =
                            String.join(
                                    "|",
                                    course.getTags()
                            );

                    bw.write(
                            course.getId() + " | " +
                                    course.getTitle() + " | " +
                                    course.getDurationHours() + " | " +
                                    course.getFee() + " | " +
                                    tags
                    );

                    bw.newLine();
                }
            }

            CourseLogger.save(
                    "Saved " +
                            courses.size() +
                            " courses to " +
                            file
            );

            System.out.println(
                    "Courses saved successfully!"
            );

        } catch (Exception e) {

            // Ghi vào save_course.txt + error.txt
            CourseLogger.saveError(
                    "Failed to save courses to file: " +
                            file,
                    e
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =========================================================
    // READ AND DISPLAY
    // =========================================================

    void readAndDisplay() {

        CourseLogger.read(
                "Start reading courses from file."
        );

        if (!Files.exists(file)) {

            CourseLogger.read(
                    "Read failed. File not found: " +
                            file
            );

            System.out.println(
                    "File not found: " + file
            );

            return;
        }

        try (BufferedReader br =
                     Files.newBufferedReader(file)) {

            String line;

            System.out.println(
                    "===== COURSE LIST ====="
            );

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data =
                        line.split(
                                "\\s*\\|\\s*",
                                5
                        );

                if (data.length != 5) {

                    System.out.println(
                            "Invalid data: " + line
                    );

                    CourseLogger.read(
                            "Invalid course data: " +
                                    line
                    );

                    continue;
                }

                List<String> tags =
                        List.of(
                                data[4].split("\\|")
                        );

                Course course = new Course(
                        data[0],
                        data[1],
                        Float.parseFloat(data[2]),
                        Float.parseFloat(data[3]),
                        tags
                );

                CourseLogger.read(
                        "Read course from file: ID=" +
                                course.getId()
                );

                System.out.println(course);
            }

        } catch (Exception e) {

            // Ghi vào read_course.txt + error.txt
            CourseLogger.readError(
                    "Failed to read courses from file: " +
                            file,
                    e
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =========================================================
    // SEARCH COURSE
    // =========================================================

    void searchCoursesToFile() {

        CourseLogger.search(
                "Start searching course."
        );

        try {

            System.out.print(
                    "Enter tag to search: "
            );

            String searchTag =
                    inputReader.readLine().trim();

            CourseLogger.search(
                    "Searching for tag=" +
                            searchTag
            );

            if (!Files.exists(file)) {

                System.out.println(
                        "File not found: " + file
                );

                CourseLogger.search(
                        "Search failed. File not found: " +
                                file
                );

                return;
            }

            boolean found = false;

            try (BufferedReader fileReader =
                         Files.newBufferedReader(file)) {

                String line;

                while ((line =
                        fileReader.readLine()) != null) {

                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] data =
                            line.split(
                                    "\\s*\\|\\s*",
                                    5
                            );

                    if (data.length != 5) {

                        CourseLogger.search(
                                "Invalid data skipped: " +
                                        line
                        );

                        continue;
                    }

                    List<String> tags =
                            List.of(
                                    data[4].split("\\|")
                            );

                    for (String tag : tags) {

                        if (tag.equalsIgnoreCase(
                                searchTag)) {

                            Course course =
                                    new Course(
                                            data[0],
                                            data[1],
                                            Float.parseFloat(
                                                    data[2]
                                            ),
                                            Float.parseFloat(
                                                    data[3]
                                            ),
                                            tags
                                    );

                            System.out.println(
                                    course
                            );

                            CourseLogger.search(
                                    "Found course ID=" +
                                            course.getId() +
                                            " with tag=" +
                                            searchTag
                            );

                            found = true;

                            break;
                        }
                    }
                }
            }

            if (!found) {

                System.out.println(
                        "NOT_FOUND"
                );

                CourseLogger.search(
                        "No course found with tag=" +
                                searchTag
                );
            }

        } catch (Exception e) {

            // Ghi vào search_course.txt + error.txt
            CourseLogger.searchError(
                    "Error while searching course from file.",
                    e
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}





