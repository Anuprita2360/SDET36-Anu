package javaPractice;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DataConverter2 {

	public static void main(String[] args) 
	{
		String s="JanuAry";
		String s1=s.substring(0, 1).toUpperCase()+s.substring(1).toLowerCase();
		System.out.println(s1);
		int d = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(s1).get(ChronoField.MONTH_OF_YEAR);
        System.out.println(d);
		//Converting Dates
	}

}
