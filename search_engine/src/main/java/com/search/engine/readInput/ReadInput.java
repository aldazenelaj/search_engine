package com.search.engine.readInput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.search.engine.exception.ExceptionMessage;

public class ReadInput {

	private String regex = "^[a-zA-Z0-9]+$";
	private Map<Integer, String> map ;
	public void print() {

		System.out.println(" -------------------------------------------------------------------------");
		System.out.println("/                           SEARCH ENGINE                                /");
		System.out.println(" -------------------------------------------------------------------------");
		Scanner sc= new Scanner(System.in); 
		System.out.print("  How many commands do you want to execute? ");
		String seq= sc.nextLine();
		boolean read=true;
		
	    map = new HashMap<>();
		
		while(read) {
		if(isNumeric(seq) ) {
			Integer s = Integer.parseInt(seq);
		for(int i =1;i<=s; i++) {
			String command= sc.nextLine();
			if(validate(command)) {
				//
			}else {
				s++;
			}
		}
		read = false;
		System.out.println("\n  ------------------------------END OF INPUT----------------------------");
		}
		else {
		System.out.println("\n  WRONG INPUT - (should be a number)");
		seq= sc.nextLine();
		}
		}
	}
	
	private static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	private  boolean validate(String command) {
		String[] split = command.split(" ");
		
		if(!split[0].equalsIgnoreCase("index") && !split[0].equalsIgnoreCase("query")) {
			
			System.out.println(" \n  index error "+split[0]+ExceptionMessage.NON_VALID_COMMAND);
			return false;
			
		}
		else if(split[0] != null && split[0].equalsIgnoreCase("index") && split.length <3) {
			
			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_INDEX);
			return false;
			
		}else if(split[0] != null && split[0].equalsIgnoreCase("index") && !this.isNumeric(split[1])) {
			
			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_INDEX);
			return false;
		}else if(split[0] != null && split[0].equalsIgnoreCase("index")) {
			
			Pattern pattern = Pattern.compile(regex);
			String token="";
			for (int i =2;i<=split.length-1;i++)
			{
			  Matcher matcher = pattern.matcher(split[i]);
			  if(!matcher.matches()) {
				  System.out.println(ExceptionMessage.NON_CORRECT_USAGE_INDEX);
				return false;
			  }	
			  token+=(split[i]+" ");
			}
			
			map.put(Integer.parseInt(split[1]), token.trim());
		}
		else if(split[0]!= null && split[0].equalsIgnoreCase("query") && split.length !=2) {
			System.out.println(ExceptionMessage.NON_CORRECT_USAGE_QUERY);
		}
		return true ;
	}

	public Map<Integer, String> getMap() {
		return map;
	}
}
