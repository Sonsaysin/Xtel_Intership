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

                // Xử lý: ở bài này là in ra màn hình
                System.out.println(
                        "[CONSUMER] Đang xử lý: " + messageC
                );
            }

        } catch (Exception e) {
            QueueLogger.error(e);
            System.out.println("Consumer đã dừng.");
        }
    }
}