package MultiThread.Practice_c_Producer_Consumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {

        // Tạo Queue có giới hạn tối đa 5 Message
        BlockingQueue<Messagec> queue =
                new ArrayBlockingQueue<>(5);

        // Tạo Producer và Consumer
        Runnable producer = new Producer(queue);
        Runnable consumer = new Consumer(queue);

        // Tạo Thread
        Thread producerThread =
                new Thread(producer, "Producer-Thread");

        Thread consumerThread =
                new Thread(consumer, "Consumer-Thread");

        // Chạy Thread
        producerThread.start();
        consumerThread.start();
    }
}
