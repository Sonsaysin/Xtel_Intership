
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private static final int PORT = 6666  ;

    public static void main(String[] args) {

        GroupManager groupManager =
                new GroupManager();

        try (ServerSocket serverSocket =
                     new ServerSocket(PORT)) {

            System.out.println(
                    "Server started on port " + PORT
            );

            while (true) {

                Socket socket =
                        serverSocket.accept();

                System.out.println(
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

            e.printStackTrace();
        }
    }
}