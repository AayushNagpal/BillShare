package billshare.com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by anusha on 20/09/16.
 */
public class TimeZoneUtils {
    private static TimeZoneUtils timeZoneUtils;
    private List<String> timeZoneList;

    private TimeZoneUtils() {

    }

    public static TimeZoneUtils instance() {
        if (timeZoneUtils == null) {
            timeZoneUtils = new TimeZoneUtils();
            timeZoneUtils.setTimeZonelist();
        }
        return timeZoneUtils;
    }

    public void setTimeZonelist() {

        timeZoneList = new ArrayList<String>();
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            TimeZone tz = TimeZone.getTimeZone(id);
            long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                    - TimeUnit.HOURS.toMinutes(hours);
            // avoid -4:-30 issue
            minutes = Math.abs(minutes);

            String result = "";
            if (hours > 0) {
                result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
            } else {
                result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
            }
            timeZoneList.add(result);
        }
    }

    public List<String> getTimeZoneList() {
        return timeZoneList;
    }
}
