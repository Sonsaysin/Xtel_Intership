public class Main {
    public static void main(String[] args) {
        //Queue tối đa 5 message
        MessageQueue messageQueue =
                new MessageQueue(5);

        Runnable producer =
                new Producer(messageQueue);

        Runnable consumer =
                new Consumer(messageQueue);

        Thread producerThread =
                new Thread(producer, "Producer-Thread");

        Thread consumerThread =
                new Thread(consumer, "Consumer-Thread");

        producerThread.start();
        consumerThread.start();
    }
}