package com.sathi.sim.util;

public final class RegxPattern {

	private RegxPattern() {
	}

	public static final String NUMERIC_CHECK_PATTERN = "-?\\d+(\\.\\d+)?";

	public static final String INTEGER_CHECK_PATTERN = "^([0-9]+$)";

	public static final String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String DATE_PATTERN = "yyyy-MM-dd";

	public static final String TIME_PATTERN = "HH:mm";

	public static final String CAPITAL_CHAR_PATTERN = "^([A-Z])+$";
	
	public static final String ONLY_ALPHABET_PATTERN = "^[a-zA-Z]*$";

	public static final String ONLY_HYPHEN_PATTERN = "^([a-zA-Z\\s\\d-])+$";

	public static final String PHONE_NUMBER_PATTERN = "^[\\d() +-]+$";
	
	public static final String EMAIL_ADDRESS_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"

}
