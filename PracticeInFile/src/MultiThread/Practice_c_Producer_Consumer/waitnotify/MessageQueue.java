package MultiThread.Practice_c_Producer_Consumer.waitnotify;

import MultiThread.Practice_c_Producer_Consumer.waitnotify.logger.QueueLogger;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<MessageC> messageCQueue = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(MessageC messageC)
            throws Exception {

        // Nếu Queue đầy thì Producer phải chờ
        synchronized (messageCQueue) {
            while (messageCQueue.size() == capacity) {

                QueueLogger.full();
                messageCQueue.wait();
            }

            // Thêm Message vào Queue
            QueueLogger.add(messageC, messageCQueue.size());
            messageCQueue.add(messageC);

            // Báo cho Consumer biết Queue đã có Message
            messageCQueue.notify();
        }
    }

    public MessageC take()
            throws InterruptedException {
        synchronized (messageCQueue) {
            // Nếu Queue rỗng thì Consumer phải chờ
            while (messageCQueue.isEmpty()) {
                QueueLogger.empty();

                messageCQueue.wait();
            }

            // Lấy Message ra
            QueueLogger.remove(messageC,);
            MessageC messageC = messageCQueue.poll();


            // Báo cho Producer biết Queue đã có chỗ trống
            messageCQueue.notify();

            return messageC;
        }
    }
}
