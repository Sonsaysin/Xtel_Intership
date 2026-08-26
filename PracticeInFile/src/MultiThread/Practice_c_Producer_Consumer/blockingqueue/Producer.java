package MultiThread.Practice_c_Producer_Consumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Messagec> queue;

    public Producer(BlockingQueue<Messagec> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int id = 1;

        try {
            while (true){
                Messagec messageC = new Messagec(id,"Message number"+ id);

                System.out.println("Producer tạo:" + messageC);

                //Nếu Queue FULL thì Producer sẽ tự động chờ
                queue.put(messageC);

                System.out.println("Producer đã đưa Message vào Queue. Queue size:" + queue.size());

                id++;

                // Định kì giây tạo ra 1 message mới
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.out.println("Producer đã bị dừng.");
        }
    }
}
