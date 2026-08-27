package MultiThread.Practice_a_NumberRandom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ByLamdba {
    // Biến dùng để điều khiển chương trình có tiếp tục chạy hay không
    private static  volatile boolean running = true;

    public static void main(String[] args) {
        Runnable task = () -> {
            Random random = new Random();
            try(BufferedWriter bw =
                        new BufferedWriter(
                                new FileWriter("output.txt",true))){
                while (running){
                    int number = random.nextInt(100);

                    bw.write(String.valueOf(number));
                    bw.newLine();

                    bw.flush();// đẩy toàn bộ dữ liệu còn nằm trong buffer xuống file

                    System.out.println("Đã ghi: "  + number);
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Lỗi file: " + e.getMessage());
            }

            System.out.println("Thread đã dừng !");
        };

        Thread thread = new Thread(task);

        thread.run();
        Scanner sc = new Scanner(System.in);
        System.out.println("Gõ stop để dừng lại ");
        while(true){
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("Stop")){
                running = false;
                break;
            }
        }
        sc.close();
        System.out.println("Đang dừng ...");

    }

}
