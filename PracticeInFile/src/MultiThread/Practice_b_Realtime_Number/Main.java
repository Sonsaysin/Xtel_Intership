package MultiThread.Practice_b_Realtime_Number;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new InputInterval(1));
        thread.start();
    }
}
