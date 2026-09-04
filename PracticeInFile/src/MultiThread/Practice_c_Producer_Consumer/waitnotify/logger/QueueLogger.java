package MultiThread.Practice_c_Producer_Consumer.waitnotify.logger;

import java.util.logging.Logger;

public class QueueLogger {

    private static final Logger logger =
            Logger.getLogger(QueueLogger.class.getName());

    public static void full() {
        logger.info("[QUEUE] FULL -> Producer đang chờ...");
    }

    public static void empty() {
        logger.info("[QUEUE] EMPTY -> Consumer đang chờ...");
    }

    public static void add(Object message, int size) {
        logger.info(
                "[QUEUE] ADD -> " + message
                        + " | Size: " + size
        );
    }

    public static void remove(Object message, int size) {
        logger.info(
                "[QUEUE] REMOVE -> " + message
                        + " | Size: " + size
        );
    }

    public static void error(Exception e) {
        logger.severe(
                "[QUEUE] ERROR -> "
                        + e.getClass().getSimpleName()
                        + ": "
                        + e.getMessage()
        );
    }
}


