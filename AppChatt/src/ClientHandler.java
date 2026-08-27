import java.io.*;
import java.net.Socket;

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
            );

            writer = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

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

    private void handleMessage(String message) {

        if (message.equalsIgnoreCase("/leave")) {

            try {
                socket.close();
            } catch (IOException ignored) {
            }

            return;
        }

        if (message.equalsIgnoreCase("/users")) {

            groupManager.showUsers(this);

            return;
        }

        // Chỉ người trong phòng mới được chat
        if (!inRoom) {

            send(
                    "You are waiting. You cannot chat yet."
            );

            return;
        }

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