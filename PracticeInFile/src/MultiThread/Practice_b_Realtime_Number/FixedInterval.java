package MultiThread.Practice_b_Realtime_Number;

import java.util.Random;

public class FixedInterval implements Runnable {

    // Cứ 2 giây chạy một lần
    private final long interval = 2;

    // Chạy trong 1 phút
    private final long duration = 1;

    @Override
    public void run() {

        Random random = new Random();

        // Thời điểm kết thúc
        long endTime =
                System.currentTimeMillis()
                        + duration * 60 * 1000;

        while (System.currentTimeMillis() < endTime) {

            // Tạo số random từ 0 -> 99
            int number = random.nextInt(100);

            System.out.println(
                    "Random number: " + number
            );

            try {

                Thread.sleep(interval * 1000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        "Thread bị ngắt!"
                );

                break;
            }
        }

        System.out.println(
                "Chương trình đã kết thúc!"
        );
    }

    public static void main(String[] args) {

        FixedInterval task = new FixedInterval();

        Thread thread = new Thread(task);

        thread.start();
    }
}
