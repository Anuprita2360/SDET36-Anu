package javaPractice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Collection_1 {

	public static void main(String[] args) 
	{
		Set<String> set = new HashSet<String>();
		set.add("hii");
		set.add("hello");
		set.add("welcome");
		set.add("banglore");
		   Iterator itr =set.iterator();
		/*	System.out.println(itr.next());
			System.out.println(itr.next());
			System.out.println(itr.next());
			System.out.println(itr.next());*/
		
	/*	while(itr.hasNext())
		{
			System.out.println(itr.next());
			System.out.println(itr.next());
			System.out.println(itr.next());
			System.out.println(itr.next());
		}*/
		   
		System.out.println(itr.next());
		System.out.println(itr.next());
		itr.remove();
	}

}
