import logger.ChatLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Nhận client
public class ChatServer {

    private static final int PORT = 6666;

    public static void main(String[] args) {

        GroupManager groupManager =
                new GroupManager();

        try (ServerSocket serverSocket =
                     new ServerSocket(PORT)) {

            // Server khởi động thành công
            ChatLogger.server(
                    "Server started on port " + PORT
            );

            while (true) {

                Socket socket =
                        serverSocket.accept();

                // Có client kết nối
                ChatLogger.server(
                        "Client connected: "
                                + socket.getInetAddress()
                );

                ClientHandler handler =
                        new ClientHandler(
                                socket,
                                groupManager
                        );

                handler.start();
            }

        } catch (IOException e) {

            ChatLogger.error(
                    "Failed to start server",
                    e
            );
        }
    }
}