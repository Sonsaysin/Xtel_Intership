import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {

    private final List<Course> courses = new ArrayList<>();

    // =========================================================
    // FILE PATH
    // =========================================================

    private final Path dataDir = Path.of("data");
    private final Path file = dataDir.resolve("courses_data.txt");

    private final BufferedReader inputReader;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CourseManagement(BufferedReader inputReader) {

        this.inputReader = inputReader;

        // Khi chương trình chạy:
        // Đọc dữ liệu cũ từ file vào List
        loadCoursesFromFile();
    }


    // =========================================================
    // ADD COURSE
    // =========================================================

    void addCourse() {

        CourseLogger.add(
                "Start adding course."
        );

        try {

            System.out.println("Enter Id: ");
            String id =
                    inputReader.readLine().trim();

            System.out.println("Enter title: ");
            String title =
                    inputReader.readLine().trim();

            System.out.println("Enter DurationHours: ");
            float durationHours =
                    Float.parseFloat(
                            inputReader.readLine()
                    );

            System.out.println("Enter fee: ");
            float fee =
                    Float.parseFloat(
                            inputReader.readLine()
                    );

            System.out.println(
                    "Enter tags: (separated by comma or |)"
            );

            String tagInput =
                    inputReader.readLine();

            // =========================================
            // XỬ LÝ TAG
            // Nhận cả:
            // SQLcore, SQLadvence
            // SQLcore | SQLadvence
            // SQLcore|SQLadvence
            // =========================================

            List<String> tags =
                    parseTags(tagInput);

            Course course =
                    new Course(
                            id,
                            title,
                            durationHours,
                            fee,
                            tags
                    );

            // Thêm vào List
            courses.add(course);

            // Lưu ngay xuống file
            saveCoursesToFile();

            CourseLogger.add(
                    "Added course: ID=" +
                            course.getId()
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
    // PARSE TAGS
    // =========================================================

    private List<String> parseTags(String tagInput) {

        List<String> tags =
                new ArrayList<>();

        if (tagInput == null ||
                tagInput.trim().isEmpty()) {

            return tags;
        }

        /*
         * Nhận cả dấu "," và "|"
         *
         * Ví dụ:
         *
         * Java, Spring, SQL
         *
         * hoặc:
         *
         * Java | Spring | SQL
         */

        String[] tagArr =
                tagInput.split("\\s*[|,]\\s*");

        for (String tag : tagArr) {

            String cleanTag =
                    tag.trim();

            if (!cleanTag.isEmpty()) {

                tags.add(cleanTag);
            }
        }

        return tags;
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

            // Tạo thư mục data nếu chưa tồn tại
            Files.createDirectories(
                    dataDir
            );

            /*
             * Ghi lại toàn bộ List courses.
             *
             * File cũ sẽ bị ghi đè.
             *
             * Nhưng không mất dữ liệu vì:
             *
             * file cũ
             *      ↓
             * loadCoursesFromFile()
             *      ↓
             * courses
             *      ↓
             * add/update/delete
             *      ↓
             * saveCoursesToFile()
             */

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

                    // List<String> → String
                    String tags =
                            String.join(
                                    "|",
                                    course.getTags()
                            );

                    bw.write(
                            course.getId() +
                                    " | " +
                                    course.getTitle() +
                                    " | " +
                                    course.getDurationHours() +
                                    " | " +
                                    course.getFee() +
                                    " | " +
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

            // save_course.txt + error.txt
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
    // LOAD COURSES FROM FILE
    // =========================================================

    void loadCoursesFromFile() {

        if (!Files.exists(file)) {

            return;
        }

        try (BufferedReader br =
                     Files.newBufferedReader(file)) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                /*
                 * File format:
                 *
                 * ID | Title | Duration | Fee | Tags
                 *
                 * Ví dụ:
                 *
                 * 003 | SQL Programming | 6000.0 |
                 * 50000000.0 | SQLcore|SQLadvence
                 */

                String[] data =
                        line.split(
                                "\\s*\\|\\s*",
                                5
                        );

                if (data.length != 5) {

                    CourseLogger.read(
                            "Invalid course data: " +
                                    line
                    );

                    continue;
                }

                // Xử lý tags
                List<String> tags =
                        parseFileTags(data[4]);

                Course course =
                        new Course(
                                data[0],
                                data[1],
                                Float.parseFloat(data[2]),
                                Float.parseFloat(data[3]),
                                tags
                        );

                courses.add(course);
            }

            CourseLogger.read(
                    "Loaded " +
                            courses.size() +
                            " courses from file."
            );

        } catch (Exception e) {

            CourseLogger.readError(
                    "Failed to load courses from file.",
                    e
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =========================================================
    // PARSE TAGS FROM FILE
    // =========================================================

    private List<String> parseFileTags(
            String tagData) {

        List<String> tags =
                new ArrayList<>();

        if (tagData == null ||
                tagData.trim().isEmpty()) {

            return tags;
        }

        String[] tagArr =
                tagData.split("\\|");

        for (String tag : tagArr) {

            String cleanTag =
                    tag.trim();

            if (!cleanTag.isEmpty()) {

                tags.add(cleanTag);
            }
        }

        return tags;
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
                            "Invalid data: " +
                                    line
                    );

                    CourseLogger.read(
                            "Invalid course data: " +
                                    line
                    );

                    continue;
                }

                // Đọc tags và trim
                List<String> tags =
                        parseFileTags(data[4]);

                Course course =
                        new Course(
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
    // SEARCH COURSE BY TAG
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
                        "File not found: " +
                                file
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

                    // Đọc tag và trim
                    List<String> tags =
                            parseFileTags(data[4]);

                    for (String tag : tags) {

                        if (tag.equalsIgnoreCase(
                                searchTag
                        )) {

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

                            System.out.println(course);

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