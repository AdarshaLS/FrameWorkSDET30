package com.crm.GenericLibrary;

import java.util.Date;
import java.util.Random;

/**
 * This class will consists of generic methods with respect to Java
 * @author Ashwini
 *
 */
public class JavaUtility {
	
	/**
	 * This method will generate random number and return it to the user
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran = new Random();
		int random = ran.nextInt(1000);
		return random;
	}
	
	/**
	 * This method will generate system date and return date in format
	 * @return
	 */
	public String getSystemDateFormat()
	{
		Date d = new Date();
		String d1 = d.toString();
		
		String[] date = d1.split(" ");
		
		String mon = date[1];
		String day = date[2];
		String year = date[5];
		
		String Dateformat = day+" "+mon+" "+year;
		return Dateformat;
	}
}
