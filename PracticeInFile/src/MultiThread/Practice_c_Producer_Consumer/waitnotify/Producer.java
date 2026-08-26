package MultiThread.Practice_c_Producer_Consumer.waitnotify;

public class Producer implements Runnable {

    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {

        int id = 1;

        try {

            while (true) {

                MessageC messageC = new MessageC(
                        id,
                        "Message number " + id
                );

                // Đưa Message vào Queue
                messageQueue.put(messageC);

                id++;

                // Producer định kỳ tạo Message
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println("Producer đã dừng.");
        }
    }
}