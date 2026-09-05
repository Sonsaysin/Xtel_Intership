package logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ChatLogger {

    private static final Logger serverLogger =
            Logger.getLogger("serverLogger");

    private static final Logger chatLogger =
            Logger.getLogger("chatLogger");

    private static final Logger errorLogger =
            Logger.getLogger("errorLogger");

    static {
        try {
            Files.createDirectories(Path.of("logs"));

            // =========================
            // SERVER
            // =========================
            FileHandler serverHandler =
                    new FileHandler("logs/server.log", true);

            serverHandler.setFormatter(new SimpleFormatter());

            serverLogger.setUseParentHandlers(false);
            serverLogger.addHandler(serverHandler);

            // =========================
            // CHAT
            // =========================
            FileHandler chatHandler =
                    new FileHandler("logs/chat.log", true);

            chatHandler.setFormatter(new SimpleFormatter());

            chatLogger.setUseParentHandlers(false);
            chatLogger.addHandler(chatHandler);

            // =========================
            // ERROR
            // =========================
            FileHandler errorHandler =
                    new FileHandler("logs/error.log", true);

            errorHandler.setFormatter(new SimpleFormatter());

            errorLogger.setUseParentHandlers(false);
            errorLogger.addHandler(errorHandler);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Không thể tạo file log",
                    e
            );
        }
    }

    // SERVER
    public static void server(String message) {
        serverLogger.info(message);
    }

    // CHAT
    public static void chat(String message) {
        chatLogger.info(message);
    }

    // ERROR
    public static void error(String message, Exception e) {
        errorLogger.log(
                Level.SEVERE,
                message,
                e
        );
    }
}