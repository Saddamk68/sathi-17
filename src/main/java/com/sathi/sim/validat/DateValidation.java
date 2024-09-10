package com.sathi.sim.validat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateValidation {

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	static {
		// to ensure strict data parsing
		simpleDateFormat.setLenient(false);
	}

	public static LocalDateTime stringToLocalDateTimeConv(String dateStr) {
		return LocalDateTime.parse(dateStr, dateTimeformatter);
	}

	public static Boolean isValidLocalDateTime(String date) {
		try {
			stringToLocalDateTimeConv(date);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static LocalDate stringToLocalDateConv(String dateStr) {
		return LocalDate.parse(dateStr, dateformatter);
	}

	public static Boolean isValidLocalDate(String date) {
		try {
			stringToLocalDateConv(date);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static Date stringToDateConv(String dateStr) throws ParseException {
		return simpleDateFormat.parse(dateStr);
	}

	public static Boolean isValidDate(String date) {
		try {
			stringToDateConv(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
