package com.sathi.sim.validat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sathi.sim.exception.InvalidDateException;
import com.sathi.sim.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateValidation {

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	static {
		// to ensure strict data parsing
		simpleDateFormat.setLenient(false);
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
