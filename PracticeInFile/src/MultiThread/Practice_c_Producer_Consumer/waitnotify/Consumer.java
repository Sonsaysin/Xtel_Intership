package MultiThread.Practice_c_Producer_Consumer.waitnotify;

import MultiThread.Practice_c_Producer_Consumer.waitnotify.logger.QueueLogger;

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
                MessageC messageC = messageQueue.take();

                Thread.sleep(100);
            }

        } catch (Exception e) {
            QueueLogger.error(e);
        }
    }
}