package MultiThread.Practice_c_Producer_Consumer.waitnotify;

import MultiThread.Practice_c_Producer_Consumer.waitnotify.logger.QueueLogger;

public class Producer implements Runnable {

    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {

        int messageId = 1;

        try {

            while (true) {

                MessageC messageC = new MessageC(
                        messageId,
                        "Message number " + messageId
                );

                // Đưa Message vào Queue
                messageQueue.put(messageC);

                messageId++;

            }

        } catch (Exception e) {
            QueueLogger.error(e);
            System.out.println("Producer đã dừng.");
        }
    }
}