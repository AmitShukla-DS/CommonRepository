package com.lnt.ems.evse.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Utility class for data time operations
 */
public class DateTimeUtil {

//	public static Timestamp convertJsonDateToTimeStamp(String jsonDate) throws ParseException {
//		
//	
//		String substring = jsonDate.substring(Math.max(jsonDate.length() - 2, 0));
//		String reqJsonDate=jsonDate.replace(substring,"");
//		long batch_date = Long.parseLong(reqJsonDate);
//		Date date = new Date(batch_date);
//		DateFormat gmtFormat = new SimpleDateFormat();
//		TimeZone gmtTime = TimeZone.getTimeZone("GMT");
//		gmtFormat.setTimeZone(gmtTime);
//		SimpleDateFormat gmtTimeFormater = new SimpleDateFormat("dd/MM/yy hh:mm aa");
//		Date gmtDate = gmtTimeFormater.parse(gmtFormat.format(date));
//		Timestamp timestamp = new Timestamp(gmtDate.getTime());
//		return timestamp;
//
//	}

    //Samrat

    /**
     * Method to convert string date to timestamp
     */
    public static Timestamp convertJsonDateToTimeStamp(String stringDate) throws ParseException {
        //date format - MMDDYYYYhhmmss
        int month = Integer.parseInt(stringDate.substring(1, 2));
        int day = Integer.parseInt(stringDate.substring(2, 4));
        int year = Integer.parseInt(stringDate.substring(4, 8));
        int hour = Integer.parseInt(stringDate.substring(8, 10));
        int minute = Integer.parseInt(stringDate.substring(10, 12));
        int second = Integer.parseInt(stringDate.substring(12, 14));
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    public static LocalDateTime timestampToLocalDateTime(Object timestamp) {
        return ((Timestamp) timestamp).toLocalDateTime();
    }

    public static Timestamp localDateTimeToTimestamp(Object localDateTime) {
        return Timestamp.valueOf((LocalDateTime) localDateTime);
    }

    /**
     * Utility method to convert String to Timestamp
     *
     * @param stringDateTime
     * @return Timestamp timestamp
     * @throws ParseException
     */
    public Timestamp convertStringToTimestamp(String stringDateTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(stringDateTime);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        return timestamp;
    }

    /**
     * Utility method to convert Timestamp to String.
     *
     * @param Timestamp timestamp
     * @return
     */
    public String convertTimestampToString(Timestamp timestamp) {
        Date dt = new Date();
        dt.setTime(timestamp.getTime());
        String formatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
        return formatedDate;
    }
}