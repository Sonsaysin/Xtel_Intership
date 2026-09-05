import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CourseLogger {

    // Thư mục chứa các file log
    private static final Path LOG_DIR = Path.of("logs");

    // Logger cho từng chức năng
    private static final Logger ADD_LOGGER =
            createLogger("add_course", "logs/add_course.txt");

    private static final Logger SEARCH_LOGGER =
            createLogger("search_course", "logs/search_course.txt");

    private static final Logger SAVE_LOGGER =
            createLogger("save_course", "logs/save_course.txt");

    private static final Logger READ_LOGGER =
            createLogger("read_course", "logs/read_course.txt");

    // Logger tổng hợp tất cả lỗi
    private static final Logger ERROR_LOGGER =
            createLogger("error", "logs/error.txt");


    // =========================================================
    // TẠO LOGGER
    // =========================================================

    private static Logger createLogger(
            String loggerName,
            String fileName) {

        Logger logger = Logger.getLogger(loggerName);

        try {
            // Tạo thư mục logs nếu chưa tồn tại
            Files.createDirectories(LOG_DIR);

            // true = ghi tiếp vào file, không xóa log cũ
            FileHandler fileHandler =
                    new FileHandler(fileName, true);

            // Định dạng log
            fileHandler.setFormatter(
                    new SimpleFormatter()
            );

            // Gắn FileHandler vào Logger
            logger.addHandler(fileHandler);

            // Không in log ra Console
            logger.setUseParentHandlers(false);

        } catch (IOException e) {

            System.out.println(
                    "Cannot create logger: "
                            + e.getMessage()
            );
        }

        return logger;
    }


    // =========================================================
    // LOG HOẠT ĐỘNG BÌNH THƯỜNG
    // =========================================================

    public static void add(String message) {
        ADD_LOGGER.info(message);
    }

    public static void search(String message) {
        SEARCH_LOGGER.info(message);
    }

    public static void save(String message) {
        SAVE_LOGGER.info(message);
    }

    public static void read(String message) {
        READ_LOGGER.info(message);
    }


    // =========================================================
    // LOG ERROR
    // =========================================================

    /*
     * Ghi lỗi vào:
     *
     * 1. Logger của chức năng
     * 2. error.txt
     *
     * Ví dụ:
     *
     * addError(...)
     *
     * -> add_course.txt
     * -> error.txt
     */

    private static void logError(
            Logger functionLogger,
            String message,
            Exception e) {

        // Ghi lỗi vào log của chức năng
        functionLogger.log(
                Level.SEVERE,
                message,
                e
        );

        // Ghi lỗi vào file error.txt
        ERROR_LOGGER.log(
                Level.SEVERE,
                message,
                e
        );
    }


    // =========================================================
    // ERROR CỦA TỪNG CHỨC NĂNG
    // =========================================================

    public static void addError(
            String message,
            Exception e) {

        logError(
                ADD_LOGGER,
                message,
                e
        );
    }

    public static void searchError(
            String message,
            Exception e) {

        logError(
                SEARCH_LOGGER,
                message,
                e
        );
    }


    public static void saveError(
            String message,
            Exception e) {

        logError(
                SAVE_LOGGER,
                message,
                e
        );
    }


    public static void readError(
            String message,
            Exception e) {

        logError(
                READ_LOGGER,
                message,
                e
        );
    }


    // =========================================================
    // ERROR CHUNG
    // =========================================================

    /*
     * Dùng cho những lỗi không thuộc riêng
     * một chức năng cụ thể.
     *
     * Lỗi này chỉ ghi vào error.txt.
     */

    public static void error(
            String message,
            Exception e) {

        ERROR_LOGGER.log(
                Level.SEVERE,
                message,
                e
        );
    }
}

