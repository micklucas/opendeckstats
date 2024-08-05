package net.oi.swccg.gemp.util;

import java.util.Calendar;
import java.util.Date;

public class OpenDeckStatsUtil {
    
    /**
     * Determine an adjusted date in time
     * @param date the base date to adjust
     * @param adjustment the number of days to adjust the date by
     * @return the adjusted date
     */
    public static Date adjustDate(Date date, Integer adjustment) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, adjustment);
        
        return calendar.getTime();
    }
}
