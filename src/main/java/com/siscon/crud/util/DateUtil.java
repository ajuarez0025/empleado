package com.siscon.crud.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Class DateUtil.
 * 
 * @author ajuarez
 */
public final class DateUtil {

	/** The Constant FORMATTER. */
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/**
	 * Instantiates a new date util.
	 */
	private DateUtil() {

	}

	/**
	 * To local date.
	 *
	 * @param date the date
	 * @return the local date
	 */
	public static LocalDate toLocalDate(String date) {
		return LocalDate.parse(date, FORMATTER);
	}

	/**
	 * To string.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String toString(LocalDate date) {
		return date.format(DateUtil.FORMATTER);
	}
}
