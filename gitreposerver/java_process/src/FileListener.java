import java.io.*;
import java.sql.SQLException;
import java.util.TimerTask;

public abstract class FileListener extends TimerTask {
    private File file;
    private long timeStamp;

    public FileListener(File file) {
        this.file = file;
        this.timeStamp = file.lastModified();
    }

    @Override
    public void run() {
        long timeStamp = file.lastModified();

        if (this.timeStamp != timeStamp) {
            this.timeStamp = timeStamp;

            try {
                onChange(file);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void onChange(File file) throws SQLException, ClassNotFoundException;

}
