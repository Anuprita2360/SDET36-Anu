package javaPractice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DifferenceBetDates {

	static Date d1;
	static Date d2;
	public static void main(String[] args) 
	{
		
		String s1="27-12-1996 02:11:20";
		String s2="19-02-2000 07:15:50";
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	try {
		 d1 = sdf.parse(s1);
	
		d2 = sdf.parse(s2);

	long timeDiff=d2.getTime()-d1.getTime();
	
	long dayDiff=(timeDiff/(1000*60*60*24))%365;
	
	long yearsDiff=(timeDiff/(1000l*60*60*24*365));
	
	long secondsDiff=(timeDiff/1000)%60;
	
	long minutesDiff=(timeDiff/(1000*60))%60;
	
	long hoursDiff=(timeDiff/(1000*60*60))%24;
	
	System.out.println("Differene Between Dates is :" +yearsDiff+" years  "+dayDiff+" days  "+hoursDiff+" hours  "+minutesDiff+" minutes  "+secondsDiff+" seconds");
	}
	catch(ParseException p)
	{
		p.printStackTrace();
	}

}
}
