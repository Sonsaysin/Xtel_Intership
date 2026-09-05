import logger.QueueLogger;

public class Consumer implements Runnable {

    private final MessageQueue messageQueue;

    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                // Lấy Message từ Queue
                Message message = messageQueue.take();
                Thread.sleep(200);
            }

        } catch (Exception e) {
            QueueLogger.error(e);
        }
    }
}