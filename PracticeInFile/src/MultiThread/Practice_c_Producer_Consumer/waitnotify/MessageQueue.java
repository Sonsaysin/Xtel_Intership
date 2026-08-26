package MultiThread.Practice_c_Producer_Consumer.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<MessageC> queue = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(MessageC messageC)
            throws InterruptedException {

        // Nếu Queue đầy thì Producer phải chờ
        while (queue.size() == capacity) {

            System.out.println("[QUEUE] FULL -> Producer đang chờ...");

            wait();
        }

        // Thêm Message vào Queue
        queue.offer(messageC);

        System.out.println(
                "[PRODUCER] Đã thêm: " + messageC
                        + " | Queue size: " + queue.size()
        );

        // Báo cho Consumer biết Queue đã có Message
        notifyAll();
    }

    public synchronized MessageC take()
            throws InterruptedException {

        // Nếu Queue rỗng thì Consumer phải chờ
        while (queue.isEmpty()) {

            System.out.println("[QUEUE] EMPTY -> Consumer đang chờ...");

            wait();
        }

        // Lấy Message ra
        MessageC messageC = queue.poll();

        System.out.println(
                "[CONSUMER] Đã lấy: " + messageC
                        + " | Queue size: " + queue.size()
        );

        // Báo cho Producer biết Queue đã có chỗ trống
        notifyAll();

        return messageC;
    }
}