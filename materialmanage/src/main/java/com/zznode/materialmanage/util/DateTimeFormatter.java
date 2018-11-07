package com.zznode.materialmanage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 日期时间格式化
 * @author yanjisheng
 *
 */
@Component
public class DateTimeFormatter implements Converter<String, Date>{
	
	private static SimpleDateFormat onlyDate = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat onlyTime = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String toDate(Date date){
		return onlyDate.format(date);
	}
	
	public static String toTime(Date date){
		return onlyTime.format(date);
	}
	
	public static String toDateTime(Date date){
		return dateTime.format(date);
	}
	
	public static Date fromDateString(String dateString) throws ParseException{
		return onlyDate.parse(dateString);
	}
	
	public static Date fromTimeString(String timeString) throws ParseException{
		return onlyTime.parse(timeString);
	}
	
	public static Date fromDateTimeString(String dateTimeString) throws ParseException{
		return dateTime.parse(dateTimeString);
	}

	@Override
	public Date convert(String source) {
		Date date = null;
		try {
			date = dateTime.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			date = onlyDate.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			date = onlyTime.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
