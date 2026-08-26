package MultiThread.Practice_c_Producer_Consumer.waitnotify;

public class MessageC {
    private int n;
    private String content;

    public MessageC(int n, String content) {
        this.n = n;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "n=" + n +
                ", content='" + content + '\'' +
                '}';
    }
}

