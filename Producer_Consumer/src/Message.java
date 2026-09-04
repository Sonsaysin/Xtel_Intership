public class Message {
    private int messageId;
    private String messageContent;

    public Message(int messageId, String messageContent) {
        this.messageId = messageId;
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}
