package MultiThread.Practice_c_Producer_Consumer.waitnotify;

public class MessageC {
    private int messageId;
    private String messageContent;

    public MessageC(int messageId, String messageContent) {
        this.messageId = messageId;
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "MessageC{" +
                "messageId=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}

