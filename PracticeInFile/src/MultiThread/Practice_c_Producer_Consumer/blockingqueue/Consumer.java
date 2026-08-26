package MultiThread.Practice_c_Producer_Consumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue<Messagec> queue;

    public Consumer(BlockingQueue<Messagec> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true){
                //Nếu Queue EMPTY thì Consumer tự động chờ

                Messagec messageC = queue.take();
                System.out.println("Consumer nhận và xử lý: " + messageC);

                System.out.println("Queue còn lại: " + queue.size());
                //Giả lập Consumer xử lý Message trong 2 giây
                Thread.sleep(2000);
                }
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer đã bị dừng.");
        }
    }
}
