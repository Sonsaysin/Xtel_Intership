import java.io.*;
import java.net.Socket;
// Xử lý client
public class ClientHandler extends Thread {

    private final Socket socket;
    private final GroupManager groupManager;

    private BufferedReader reader;
    private PrintWriter writer;

    private String username;

    private boolean inRoom = false;

    public ClientHandler(
            Socket socket,
            GroupManager groupManager
    ) {
        this.socket = socket;
        this.groupManager = groupManager;
    }

    @Override
    public void run() {

        try {

            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            ); // nhận client

            writer = new PrintWriter(
                    socket.getOutputStream(),
                    true
            ); // gửi dữ liệu từ server về client

            // Nhận username
            writer.println("Enter username:");

            username = reader.readLine();

            if (username == null || username.isBlank()) {
                return;
            }

            // Cho vào group
            groupManager.join(this);

            String message;

            while ((message = reader.readLine()) != null) {

                handleMessage(message);

            }

        } catch (IOException e) {

            System.out.println(
                    username + " disconnected."
            );

        } finally {

            groupManager.leave(this);

            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
    // Xử lý message và các command từ Client
    private void handleMessage(String message) {

        // Client yêu cầu rời phòng
        if (message.equalsIgnoreCase("/leave")) {

            try {
                socket.close();
            } catch (IOException ignored) {
            }

            return;
        }

        // Client yêu cầu xem danh sách người dùng
        if (message.equalsIgnoreCase("/users")) {

            groupManager.showUsers(this);

            return;
        }

        // Client chưa được vào phòng thì không được chat
        if (!inRoom) {

            send(
                    "You are waiting. You cannot chat yet."
            );

            return;
        }

        // Gửi message đến các Client khác trong phòng
        groupManager.broadcast(
                "[" + username + "] " + message,
                this
        );
    }

    public void send(String message) {

        if (writer != null) {
            writer.println(message);
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isInRoom() {
        return inRoom;
    }

    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }
}