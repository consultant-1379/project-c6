import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FileListenerDriver extends ProcessCSV {
    public static void main(String[] args) {
        System.out.println("Listening for file changes...");
        TimerTask timerTask = new FileListener(new File("./project-c6/metrics.csv")) {
            @Override
            protected void onChange(File file) throws SQLException, ClassNotFoundException {
//                new ProcessCSV().process();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, new Date(), 1000);
    }
}
