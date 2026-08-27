import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
//kết nối tới Server, gửi dữ liệu lên Server và nhận dữ liệu từ Server.
    private static final String HOST = "localhost";
    private static final int PORT = 6666;

    public static void main(String[] args) {

        try (
                Socket socket =
                        new Socket(HOST, PORT);

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()
                                )
                        );

                PrintWriter writer =
                        new PrintWriter(
                                socket.getOutputStream(),
                                true
                        );

                Scanner scanner =
                        new Scanner(System.in)
        ) {

            // Thread nhận message
            Thread receiver = new Thread(() -> {

                try {

                    String message;

                    while ((message =
                            reader.readLine()) != null) {

                        System.out.println(message);
                    }

                } catch (IOException e) {

                    System.out.println(
                            "Disconnected from server."
                    );
                }
            });

            receiver.start();

            // Nhập username
            String username = scanner.nextLine();

            writer.println(username);

            // Gửi message
            while (true) {

                String message =
                        scanner.nextLine();

                writer.println(message);

                if (message.equalsIgnoreCase("/leave")) {
                    break;
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}