package javaPractice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Substractdate {

	public static void main(String[] args) 
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String Date1="30/12/1994";
		String Date2="27/12/1996";
		LocalDate d1 = LocalDate.parse(Date1, formatter);
		LocalDate d2 = LocalDate.parse(Date2, formatter);
		long DaysBetween = ChronoUnit.DAYS.between(d1, d2);
		
		if(DaysBetween > -1)
		{
		System.out.println(DaysBetween);
		}
	}

}
