package com.search.engine.readInput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.search.engine.exception.ExceptionMessage;
import com.sun.javafx.fxml.expression.Expression;

public class ReadInput {

	private String regexQuery ="(?=^([^()]*\\([^()]*\\))*[^()]*$)^[ A-Za-z0-9|&()]*$";
	private String regexIndex = "^[a-zA-Z0-9]+$";
	private Map<String, String> map;

	public void print() {

		System.out.println(" -------------------------------------------------------------------------");
		System.out.println("/                           SEARCH ENGINE                                /");
		System.out.println(" -------------------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);
		System.out.print("\n  How many commands do you want to execute? \n");
		String seq = sc.nextLine();
		boolean read = true;

		map = new HashMap<>();

		while (read) {
			if (isNumeric(seq)) {
				Integer s = Integer.parseInt(seq);
				for (int i = 1; i <= s; i++) {
					String command = sc.nextLine();

					if (!validate(command))
						s++;

				}
				read = false;
				System.out.println("\n  ------------------------------END OF INPUT----------------------------");
				System.out.println("\n\n  ---------------------------------RESULT-------------------------------");
			} else {
				System.out.println("\n  WRONG INPUT - (should be a number)");
				seq = sc.nextLine();
			}
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validate(String command) {
		String[] split = command.split(" ");

		int count = 0;
		if (!split[0].equalsIgnoreCase("index") && !split[0].equalsIgnoreCase("query")) {

			System.out.println(" \n  index error " + split[0] + ExceptionMessage.NON_VALID_COMMAND);
			return false;

		} else if (split[0] != null && split[0].equalsIgnoreCase("index") && split.length < 3) {

			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_INDEX);
			return false;

		} else if (split[0] != null && split[0].equalsIgnoreCase("index") && !this.isNumeric(split[1])) {

			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_INDEX);
			return false;
		} else if (split[0] != null && split[0].equalsIgnoreCase("index")) {

			String token = "";
			for (int i = 2; i <= split.length - 1; i++) {

				if (!split[i].matches(regexIndex)) {
					System.out.println(ExceptionMessage.NON_CORRECT_USAGE_INDEX);
					return false;
				}
				token += (split[i] + " ");
			}

			map.put(split[1], token.trim());
		} else if (split[0] != null && split[0].equalsIgnoreCase("query") && split.length != 2) {
			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_QUERY);
		}else if(!split[1].matches(regexQuery)) {
			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_QUERY);
		}else if(split[1].startsWith("|") || split[1].startsWith("&") || split[1].endsWith("|") || split[1].endsWith("&")) {
			System.out.println(ExceptionMessage.QUERY_NOT_CORRECT);
		}else {
			count++;
			map.put(split[0]+count, split[1]);
		}
		return true;
	}

	public Map<String, String> getMap() {
		return map;
	}
}
