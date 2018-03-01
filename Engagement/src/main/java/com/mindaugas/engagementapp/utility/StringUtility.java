package com.mindaugas.engagementapp.utility;

public class StringUtility {
	public static String toCommaSeperatedString(Object[] items) {
		StringBuilder stringBuilder = new StringBuilder();

		for (Object item : items) {
			stringBuilder.append(item).append(",");

		}

		if (stringBuilder.length() > 0) {

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}

		return stringBuilder.toString();
	}

}
