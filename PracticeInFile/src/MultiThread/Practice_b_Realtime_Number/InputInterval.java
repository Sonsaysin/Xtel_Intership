package MultiThread.Practice_b_Realtime_Number;

import java.util.Random;

public class InputInterval implements Runnable {
    private int n;

    public InputInterval(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        Random random = new Random();

        //Lấy thời điểm bắt đầu
        long startTime = System.currentTimeMillis();

        //Lấy thời điểm kết thúc = thời điểm bắt đầu + n phút
        long endTime = startTime + n * 60 * 1000L;

        //Chạy đến khi đủ n phút
        while (System.currentTimeMillis() < endTime){
            int number = random.nextInt(100);
            System.out.println("Number random: " + number);
            try {
                Thread.sleep(n * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Chương trình đã chạy ; dừng sau " + n + "phút.");
    }
}
