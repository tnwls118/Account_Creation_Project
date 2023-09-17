import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

    public static String time() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
        return format.format(date);
    }
}
