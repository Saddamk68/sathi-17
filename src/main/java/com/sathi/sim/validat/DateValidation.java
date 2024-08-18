package com.sathi.sim.validat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import com.sathi.sim.exception.InvalidDateException;
import com.sathi.sim.util.Constants;

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

	public static LocalDateTime stringToLocalDateTimeConv(String dateStr) throws InvalidDateException {
		try {
			return LocalDateTime.parse(dateStr, dateTimeformatter);
		} catch (DateTimeParseException e) {
			throw new InvalidDateException(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, dateStr));
		}
	}

	public static Boolean isValidLocalDateTime(String date) throws InvalidDateException {
		try {
			stringToLocalDateTimeConv(date);
			return true;
		} catch (InvalidDateException e) {
			throw new InvalidDateException(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, date));
		}
	}

	public static LocalDate stringToLocalDateConv(String dateStr) throws InvalidDateException {
		try {
			return LocalDate.parse(dateStr, dateformatter);
		} catch (DateTimeParseException e) {
			throw new InvalidDateException(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, dateStr));
		}
	}

	public static Boolean isValidLocalDate(String date) throws InvalidDateException {
		try {
			stringToLocalDateConv(date);
			return true;
		} catch (InvalidDateException e) {
			throw new InvalidDateException(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, date));
		}
	}

	public static Date stringToDateConv(String dateStr) throws InvalidDateException {
		try {
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new InvalidDateException(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, dateStr));
		}
	}

	public static Boolean isValidDate(String date) throws InvalidDateException {
		try {
			stringToDateConv(date);
			return true;
		} catch (InvalidDateException e) {
			throw new InvalidDateException(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, date));
		}
	}

}
