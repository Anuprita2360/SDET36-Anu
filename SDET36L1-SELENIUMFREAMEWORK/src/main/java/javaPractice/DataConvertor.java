package javaPractice;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DataConvertor {

	public static void main(String[] args) 
	{
		String s="04-07-2022";    //dd_MM_yyyy
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//Month month = LocalDate.parse(s, dtf).getMonth();
		//System.out.println(month.toString());
		
		LocalDate month = LocalDate.parse(s, dtf);
		//System.out.println(month.getMonth());
	}

}
