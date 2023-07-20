package com.api.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
	public static LocalDate getDateFromTimeStamp(String timeStamp){
		Date date = new Date(Long.parseLong(timeStamp)*1000);
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

}
