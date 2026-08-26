package MultiThread.Practice_d_TCP_Client_Server.client;

import MultiThread.Practice_d_TCP_Client_Server.common.Message;
import MultiThread.Practice_d_TCP_Client_Server.common.RandomMessageGenerator;
import MultiThread.Practice_d_TCP_Client_Server.config.ConfigLoader;
import MultiThread.Practice_d_TCP_Client_Server.logger.AppLogger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader();
        RandomMessageGenerator generator = new RandomMessageGenerator();

        String host = configLoader.getServerIp();
        int port = configLoader.getServerPort();
        try(Socket socket = new Socket(host,port)){
            System.out.println("Client đã kết nối tới Server!");
            System.out.println("Server: " + host + ":" + port);

            OutputStream outputStream = socket.getOutputStream();

            Random random = new Random();
            int id = 1;

            while (true) {

                Message message = generator.generate(id);

                String data = message.getContent();

                outputStream.write(
                        (data + "\n").getBytes(StandardCharsets.UTF_8)
                );

                outputStream.flush();

                System.out.println("Client gửi: " + data);

                id++;

                Thread.sleep(2000);
            }
        } catch (IOException e) {

            System.out.println("Lỗi khi kết nối hoặc gửi dữ liệu!");
            e.printStackTrace();
            AppLogger.logError("Lỗi khi kết nối hoặc gửi dữ liệu", e);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            System.out.println("Client bị gián đoạn!");
            AppLogger.logError("Lỗi kết nối hoặc gửi dữ liệu",e);
        }
    }
}
