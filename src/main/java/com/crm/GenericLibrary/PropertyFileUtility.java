package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class will read data from property file and return value to the user
 * @author Ashwini
 *
 */
public class PropertyFileUtility {
	
	/**
	 * This method will read data from property file for the given key by user and return value to the user
	 * @param Key
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromPropertyFile(String Key) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.FilePath);
		Properties pLib = new Properties();
		pLib.load(fis);
		String value = pLib.getProperty(Key);
		return value;
	}

}
