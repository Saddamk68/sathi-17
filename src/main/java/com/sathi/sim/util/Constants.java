package com.sathi.sim.util;

public final class Constants {

	private Constants() {
	}

	public static final String STATUS_FAILED = "FAILED";

	public static final String STATUS_ERROR = "ERROR";

	public static final String STATUS_SUCCESS = "SUCCESS";

	public static final String EXCPT_MSG_STUDENT_DET_NOT_FOUND_FOR_ID = "Student details not found for the given studentId : {}";

	public static final String ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_ID = "Student details not found for the given studentId : %s";
	
	public static final String EXCPT_MSG_DATE_CONVERSION_ISSUE = "Please enter valid date yyyy-MM-dd : {}";

	public static final String ERR_MSG_DATE_CONVERSION_ISSUE = "Please enter valid date yyyy-MM-dd : %s";

	public static final String ERR_MSG_PAYMENT_DETAIL = "Error occured while fetching payment details";	

	public static final String ERR_SAVING_PAYMENT_DET = "Error while saving payment details";
	
	public static final String ERR_MSG_PAYMENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID = "Payment details not found for given student id : %s";

	public static final String EXCPT_MSG_PAYMENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID = "Payment details not found for given student id : {}";
	
	public static final String ERR_MSG_PAYMENT_DET_NOT_FOUND_FOR_GIVEN_DATE = "Payment details not found for given date : %s";

	public static final String EXCPT_MSG_PAYMENT_DET_NOT_FOUND_FOR_GIVEN_DATE = "Payment details not found for given date : {}";

	public static final String ERR_SAVING_FEE_DET = "Error while saving fee details";
	
	public static final String ERR_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID = "Fee details not found for given student id : %s";

	public static final String EXCPT_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID = "Fee details not found for given student id : {}";

	public static final String ERR_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_REMAINING_AMT = "Fee details not found for given remaining amount : %s";

	public static final String ERR_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_DATE = "Fee details not found for given date : %s";

}
