import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        while (true) {
            TimeUnit.SECONDS.sleep(5);
            test.test();
        }
    }
}
