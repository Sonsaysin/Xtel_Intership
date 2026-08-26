package MultiThread.Practice_c_Producer_Consumer.blockingqueue;

public class Messagec {
    private int n;
    private String content;

    public Messagec(int n, String content) {
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
