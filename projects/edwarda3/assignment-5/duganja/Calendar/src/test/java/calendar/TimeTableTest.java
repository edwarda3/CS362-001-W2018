package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	  public void test01()  throws Throwable  {
		 GregorianCalendar newyears = new GregorianCalendar(2018,1,1);
		 GregorianCalendar newyears2 = new GregorianCalendar(2018,2,1);
		 TimeTable tt = new TimeTable();
		 Appt appt = new Appt(1,1,2,1,2018,"test","Test appt");
		 Appt appt2 = new Appt(2,1,3,1,2018,"test2","Test2 appt");
		 Appt appt3 = new Appt(2,1,3,1,2017,"test3","Test3 appt");
		 int[] rdays = {2,3,4};
		 appt2.setRecurrence(rdays,Appt.RECUR_BY_WEEKLY,2,Appt.RECUR_NUMBER_FOREVER);
		 LinkedList<Appt> lla = new LinkedList<Appt>();
		 lla.add(appt); lla.add(appt2); lla.add(appt3);
		 LinkedList<CalDay> cd = new LinkedList<CalDay>();
		 cd = tt.getApptRange(lla,newyears,newyears2);
		 assertTrue(cd.getFirst().isValid());

	 }

	 @Test
	public void test02() throws Throwable{
		 TimeTable tt = new TimeTable();
		 Appt appt = new Appt(1,1,2,1,2018,"test","Test appt");
		 Appt appt2 = new Appt(2,1,3,1,2018,"test2","Test2 appt");
		 Appt appt3 = new Appt(2,1,3,1,2017,"test3","Test3 appt");
		 int[] rdays = {2,3,4};
		 appt2.setRecurrence(rdays,Appt.RECUR_BY_WEEKLY,2,Appt.RECUR_NUMBER_FOREVER);
		 LinkedList<Appt> lla = new LinkedList<Appt>();
		 lla.add(appt); lla.add(appt2); lla.add(appt3);

		 assertNull(tt.deleteAppt(null, null));

		 Appt invalidAppt = new Appt(-1,1,1,1,1,"invalid","This is an invalid object");
		 assertNull(tt.deleteAppt(lla,invalidAppt));

		 assertNotNull(tt.deleteAppt(lla,appt));

		 LinkedList<Appt> lla2 = new LinkedList<Appt>();
		 lla2.add(appt); lla2.add(appt3);
		 int[] pv = {0,1};
		 assertNotNull(tt.permute(lla2,pv));
	 }
//add more unit tests as you needed
}
