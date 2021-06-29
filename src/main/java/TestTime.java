import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTime extends TimerTask{

    public static void main(String[] args) {
        TimerTask timerTask = new TestTime();
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(timerTask, 0, 10*100);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Timer task started at: "+ new Date());
        completeTask();
        System.out.println("Timer task finished at: "+ new Date());
    }

    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
