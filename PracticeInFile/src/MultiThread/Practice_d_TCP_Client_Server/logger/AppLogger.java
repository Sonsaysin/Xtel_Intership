//package MultiThread.Practice_d_TCP_Client_Server.logger;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardOpenOption;
//import java.time.LocalDateTime;
//
//public class AppLogger {
//    /**
//     *private static final Path LOG_FILE =
//     *         Path.of("src/MultiThread/Practice_d_TCP_Client_Server/logs/error.log");
//     */
//
//    private static final Path LOG_FILE = Path.of("logs", "error.log");
//    public static void logError(String message, Exception e) {
//        try {
//            Files.createDirectories(LOG_FILE.getParent());
//
//            try (var writer = Files.newBufferedWriter(
//                    LOG_FILE,
//                    StandardOpenOption.CREATE,
//                    StandardOpenOption.APPEND)) {
//                StringWriter stackTrace = new StringWriter();
//                e.printStackTrace(new PrintWriter(stackTrace));
//
//                writer.write("[" + LocalDateTime.now() + "] ERROR: " + message);
//                writer.newLine();
//                writer.write(stackTrace.toString());
//                writer.newLine();
//            }
//
//        } catch (IOException logException) {
//            System.out.println(
//                    "Không thể ghi log: "
//                            + logException.getMessage()
//            );
//        }
//    }
//}


package MultiThread.Practice_d_TCP_Client_Server.logger;

import java.io.IOException;
import java.util.logging.*;

public class AppLogger {

    private static final Logger LOGGER =
            Logger.getLogger(AppLogger.class.getName());

    static {
        try {
            FileHandler fileHandler =
                    new FileHandler("logs/error.log", true);

            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);

            LOGGER.setLevel(Level.ALL);

            // Không ghi log ra ConsoleHandler mặc định
            LOGGER.setUseParentHandlers(false);

        } catch (IOException e) {
            System.err.println(
                    "Không thể khởi tạo Logger: "
                            + e.getMessage()
            );
        }
    }

    public static void logError(
            String message,
            Exception e) {

        LOGGER.log(
                Level.SEVERE,
                message,
                e
        );
    }
}
