import logger.QueueLogger;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<Message> messageQueue = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(Message message)
            throws Exception {

        // Nếu Queue đầy thì Producer phải chờ
        synchronized (messageQueue) {
            while (messageQueue.size() == capacity) {

                QueueLogger.full();

                messageQueue.wait();
            }

            // Thêm Message vào Queue
            messageQueue.add(message);

            QueueLogger.add(message, messageQueue.size());

            // Báo cho Consumer biết Queue đã có Message
            messageQueue.notify();
        }
    }

    public Message take()
            throws Exception {
        synchronized (messageQueue) {
            // Nếu Queue rỗng thì Consumer phải chờ
            while (messageQueue.isEmpty()) {
                QueueLogger.empty();

                messageQueue.wait();
            }

            // Lấy Message ra
            Message message = messageQueue.poll();

            QueueLogger.remove(message, messageQueue.size());

            // Báo cho Producer biết Queue đã có chỗ trống
            messageQueue.notify();

            return message;
        }
    }
}
