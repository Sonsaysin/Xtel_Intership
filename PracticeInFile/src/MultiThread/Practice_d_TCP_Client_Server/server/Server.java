package MultiThread.Practice_d_TCP_Client_Server.server;

import MultiThread.Practice_d_TCP_Client_Server.config.ConfigLoader;
import MultiThread.Practice_d_TCP_Client_Server.logger.AppLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader();
        int port = configLoader.getServerPort();

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server đang chạy ở port :" + port);
            System.out.println("Đang chờ Client kết nối.... ");

            Socket clientSocket = serverSocket.accept();
            // Lấy và hiển thị địa chỉ IP của Client vừa kết nối
            System.out.println("Client đã két nối: " + clientSocket.getInetAddress());

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;

            while((message = reader.readLine()) != null){
                System.out.println("Server nhận: " + message);
            }
        }catch (IOException e){
            System.out.println("Lỗi khi khởi động hoặc xử lý kết nối server!");
            e.printStackTrace();
            AppLogger.logError("Lỗi khi khởi động hoặc xử lý kết nối server", e);
        }
    }

}
