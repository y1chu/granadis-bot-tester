import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

public class TestingCode {
    public static void main(String[] args) throws InterruptedException {

        new Timer().schedule(new NewsletterTask(), 0, 1000);

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
        }
    }
    public static class NewsletterTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Email sent at: "
                    + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
                    ZoneId.systemDefault()));
        }
    }

}
