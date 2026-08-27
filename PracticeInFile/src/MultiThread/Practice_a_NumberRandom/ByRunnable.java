package MultiThread.Practice_a_NumberRandom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class RandomNumber implements Runnable {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        Random random = new Random();

        try (BufferedWriter br =
                     new BufferedWriter(
                             new FileWriter("output.txt", true))) {
            while (running) {
                int number = random.nextInt(100);

                br.write(String.valueOf(number)); //Chuyển number từ kiểu số (int) thành String, sau đó ghi chuỗi đó vào file.
                br.newLine();

                // Đẩy dữ liệu từ buffer xuống file
                br.flush();

                System.out.println("Đã ghi: " + number);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            System.out.println("Lỗi file: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Thread đã dừng!");
    }
}

public class ByRunnable {
    public static void main(String[] args) {

        RandomNumber task =
                new RandomNumber();

        Thread thread = new Thread(task);

        // Chuyển Thread từ NEW → RUNNABLE
        thread.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Gõ stop để dừng chương trình:"
        );

        while (true) {

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("stop")) {

                task.stop();

                break;
            }
        }

        scanner.close();

        System.out.println("Đang dừng...");
    }
}
