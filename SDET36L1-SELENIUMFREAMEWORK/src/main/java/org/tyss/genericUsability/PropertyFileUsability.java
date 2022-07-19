package org.tyss.genericUsability;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods for CSV and Property file
 * @author Anuprita Raut
 *
 */

public final class PropertyFileUsability 
{
	private Properties properties;
	/**
	 * This method is used to initialize the property file
	 * FileInputStream is a class used to create object of that class to covert the physical file to the java readable form
	 * load is a non static method of Properties class so object is created of Properties class
	 * load method is used to load the file
	 * @param filePath
	 */
	public void initialisePropertyFile(String filePath)
	{
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			properties=new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to fetch the data from the property file
	 * @param key
	 * @return 
	 * 
	 */
	public String getDataFronProperty(String key)
	{
		return properties.getProperty(key);
	}

}
