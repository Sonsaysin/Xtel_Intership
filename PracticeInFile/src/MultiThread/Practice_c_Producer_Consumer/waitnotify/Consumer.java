package MultiThread.Practice_c_Producer_Consumer.waitnotify;

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

                // Giả lập xử lý mất 2 giây
                Thread.sleep(2000);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println("Consumer đã dừng.");
        }
    }
}