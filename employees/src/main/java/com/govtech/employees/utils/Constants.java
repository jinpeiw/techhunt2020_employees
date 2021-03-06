/**
 * Author: Wong Jin Pei
 * This class contains constants value
 */
package com.govtech.employees.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	public static String CSV_COMMENTS_SYMBOL = "#";

	public static List<String> SORT_SYMBOLS = Arrays.asList("+", "-");
	public static List<String> SORT_PARAMS = Arrays.asList("id", "login", "name", "salary");

	public static String STRING_INSERT = "insert";
	public static String STRING_UPDATE = "update";

}
