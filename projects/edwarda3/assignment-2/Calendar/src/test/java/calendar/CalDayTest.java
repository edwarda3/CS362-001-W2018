package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {
	 @Test
	  public void test01()  throws Throwable  {
	 	CalDay nothing = new CalDay();

		GregorianCalendar newyears = new GregorianCalendar(2018,1,1);
		CalDay cd = new CalDay(newyears);
		assertEquals(1,cd.getDay());
		assertEquals(1,cd.getMonth());
		assertEquals(2018,cd.getYear());
	 }
	 @Test
	  public void test02()  throws Throwable  {
		 Calendar rightnow = Calendar.getInstance();
		 int thisMonth = rightnow.get(Calendar.MONTH)+1;
		 int thisYear = rightnow.get(Calendar.YEAR);
		 int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 CalDay cd = new CalDay(today);
		 Appt appt = new Appt(1,1,1,1,1,"test","Test appt");
		 Appt appt2 = new Appt(2,1,1,1,1,"test","Test appt");
		 cd.addAppt(appt);
		 cd.addAppt(appt2);
		 LinkedList<Appt> lla = new LinkedList<Appt>();
		 lla.add(appt); lla.add(appt2);
		 assertEquals(lla,cd.getAppts());
	 }
	@Test
	public void test03()  throws Throwable  {
		GregorianCalendar newyears = new GregorianCalendar(2018,1,1);
		CalDay cd = new CalDay(newyears);
		Appt appt = new Appt(2,1,1,1,1,"test","Test appt");
		Appt appt2 = new Appt(5,1,1,1,1,"test","Test appt");
		Appt appt3 = new Appt(3,1,1,1,1,"test","Test appt");
		cd.addAppt(appt);
		cd.addAppt(appt2);
		cd.addAppt(appt3);
		assertTrue(cd.toString().contains(appt.toString()));
		assertEquals(3,cd.getSizeAppts());
	}

	@Test
	public void test04() throws Throwable{
	 	GregorianCalendar newyears = new GregorianCalendar(2018,1,1);
		CalDay cd = new CalDay(newyears);
		CalDay cd2 = new CalDay();
		Iterator<?> itr = cd.iterator();
		Iterator<?> itr2 = cd2.iterator();
	}
//add more unit tests as you needed	
}
