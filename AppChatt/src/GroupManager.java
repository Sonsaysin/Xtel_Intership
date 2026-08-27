import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GroupManager {

    private static final int MAX_USERS = 3;

    private final List<ClientHandler> activeUsers = new ArrayList<>();

    private final BlockingQueue<ClientHandler> waitingQueue =
            new LinkedBlockingQueue<>();

    // Cho client vào phòng
    public synchronized void join(ClientHandler client) {

        if (activeUsers.size() < MAX_USERS) {

            activeUsers.add(client);

            client.setInRoom(true);

            client.send("You joined the chat room.");

            broadcast(client.getUsername() + " joined the chat.");

        } else {

            waitingQueue.offer(client);

            client.send(
                    "Chat room is full. You are waiting..."
            );
        }
    }

    // Client rời phòng
    public synchronized void leave(ClientHandler client) {

        boolean removed = activeUsers.remove(client);

        if (removed) {

            client.setInRoom(false);

            broadcast(client.getUsername() + " left the chat.");

            // Có người đang chờ?
            ClientHandler next = waitingQueue.poll();

            if (next != null) {

                activeUsers.add(next);

                next.setInRoom(true);

                next.send(
                        "A slot is available. You joined the chat room."
                );

                broadcast(
                        next.getUsername() + " joined the chat."
                );
            }

        } else {

            // Client đang ở waiting queue
            waitingQueue.remove(client);
        }
    }

    // Gửi tin nhắn cho những người đang trong phòng
    public synchronized void broadcast(
            String message,
            ClientHandler sender
    ) {

        for (ClientHandler client : activeUsers) {

            if (client != sender) {
                client.send(message);
            }
        }
    }

    // Broadcast thông báo hệ thống
    public synchronized void broadcast(String message) {

        for (ClientHandler client : activeUsers) {
            client.send(message);
        }
    }

    public synchronized void showUsers(
            ClientHandler requester
    ) {

        requester.send("===== ACTIVE USERS =====");

        for (ClientHandler client : activeUsers) {
            requester.send("- " + client.getUsername());
        }

        requester.send("===== WAITING USERS =====");

        for (ClientHandler client : waitingQueue) {
            requester.send("- " + client.getUsername());
        }
    }
}