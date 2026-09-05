import logger.QueueLogger;

public class Producer implements Runnable{
    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {

        int messageId = 1;

        try {

            while (true) {

                Message messageC = new Message(
                        messageId,
                        "Message number " + messageId
                );

                // Đưa Message vào Queue
                messageQueue.put(messageC);

                messageId++;
                Thread.sleep(200);

            }

        } catch (Exception e) {
            QueueLogger.error(e);
        }
    }
}