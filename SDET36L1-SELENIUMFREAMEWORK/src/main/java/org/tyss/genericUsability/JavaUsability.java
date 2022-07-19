package org.tyss.genericUsability;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
  * This class contains java reusable methods
  * @author Anuprita Raut
  *
  */
public class JavaUsability 
{
	/**
	 * This method is used to generate the Random Number
	 * @param int
	 * @return
	 */
public int getrandomNumber()
{
	return new Random().nextInt(1000);
}

/**
 * This method is used to convert the String to long datatype
 * @param stringData
 * @return
 */

public long convertStringToLong(String stringData)
{
	return Long.parseLong(stringData);
}

/**
 * This method is used to print the values in console
 * @param value
 */

public void printStatement(String value)
{
	System.out.println(value);
}

/**
 * This method is used to split the string based on Strategy
 * @param value
 * @param strategy
 * @return
 */

public String[] SplitString(String value,String strategy)
{
	return value.split(strategy);
}

/**
 * This method is used to get current date in specified strategy
 * @param strategy
 * @return
 */

public String getCurrentDate(String strategy)
{
	return new SimpleDateFormat().format(new Date());
}

}
