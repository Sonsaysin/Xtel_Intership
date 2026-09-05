package logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class QueueLogger {

    private static final Logger fullAddLogger =
            Logger.getLogger("fullAddLogger");

    private static final Logger emptyRemoveLogger =
            Logger.getLogger("emptyRemoveLogger");

    private static final Logger errorLogger =
            Logger.getLogger("errorLogger");

    static {
        try {
            // Tạo thư mục logs nếu chưa tồn tại
            Files.createDirectories(Path.of("logs"));

            // =========================
            // FULL + ADD
            // =========================
            FileHandler fullAddHandler =
                    new FileHandler("logs/full_add.txt", true);

            fullAddHandler.setFormatter(new SimpleFormatter());

            fullAddLogger.setUseParentHandlers(false);
            fullAddLogger.addHandler(fullAddHandler);

            // =========================
            // EMPTY + REMOVE
            // =========================
            FileHandler emptyRemoveHandler =
                    new FileHandler("logs/empty_remove.txt", true);

            emptyRemoveHandler.setFormatter(new SimpleFormatter());

            emptyRemoveLogger.setUseParentHandlers(false);
            emptyRemoveLogger.addHandler(emptyRemoveHandler);

            // =========================
            // ERROR
            // =========================
            FileHandler errorHandler =
                    new FileHandler("logs/error.txt", true);

            errorHandler.setFormatter(new SimpleFormatter());

            errorLogger.setUseParentHandlers(false);
            errorLogger.addHandler(errorHandler);

        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo file log", e);
        }
    }

    // Queue FULL
    public static void full() {
        fullAddLogger.info(
                "[QUEUE] FULL -> Producer đang chờ..."
        );
    }

    // Producer ADD message
    public static void add(Object message, int size) {
        fullAddLogger.info(
                "[PRODUCER] ADD -> "
                        + message
                        + " | Queue size: "
                        + size
        );
    }

    // Queue EMPTY
    public static void empty() {
        emptyRemoveLogger.info(
                "[QUEUE] EMPTY -> Consumer đang chờ..."
        );
    }

    // Consumer REMOVE message
    public static void remove(Object message, int size) {
        emptyRemoveLogger.info(
                "[CONSUMER] REMOVE -> "
                        + message
                        + " | Queue size: "
                        + size
        );
    }

    // ERROR
    public static void error(Exception e) {
        errorLogger.log(
                Level.SEVERE,
                "[QUEUE] ERROR",
                e
        );
    }
}




