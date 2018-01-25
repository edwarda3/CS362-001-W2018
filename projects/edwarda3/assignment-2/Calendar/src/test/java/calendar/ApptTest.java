package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=2;
		 int startYear=2018;
		 String title="Birthday";
		 String description="This is my birthday";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(1,2,3,4,5,"test","test desc");
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(1, appt.getStartHour());
		 assertEquals(2, appt.getStartMinute());
		 assertEquals(3, appt.getStartDay());
		 assertEquals(4, appt.getStartMonth());
		 assertEquals(5, appt.getStartYear());
		 assertEquals("test", appt.getTitle());
		 assertEquals("test desc", appt.getDescription());

		 appt.setStartMonth(13);
		 appt.setStartDay(0);
		 appt.setStartMinute(70);
		 appt.setStartHour(24);
		 appt.setStartYear(10);
		 appt.setTitle("newtitle");
		 appt.setDescription("new desc");

		 assertFalse(appt.getValid());
		 assertEquals(24, appt.getStartHour());
		 assertEquals(70, appt.getStartMinute());
		 assertEquals(0, appt.getStartDay());
		 assertEquals(13, appt.getStartMonth());
		 assertEquals(10, appt.getStartYear());
		 assertEquals("newtitle", appt.getTitle());
		 assertEquals("new desc", appt.getDescription());

		 appt.setTitle(null);
		 appt.setDescription(null);
		 appt.setRecurrence(null,Appt.RECUR_BY_WEEKLY,2,Appt.RECUR_NUMBER_FOREVER);

		 Appt wrongMinute = new Appt(1,0,15,5,2018,"Wrong Min","asdsa");
		 Appt wrongDay = new Appt(1,40,50,5,2018,"Wrong Day","asdsa");
		 Appt wrongMonth = new Appt(1,40,10,22,2018,"Wrong Month","asdsa");
		 assertFalse(wrongDay.getValid());
		 assertFalse(wrongMonth.getValid());
	 }
	@Test
	public void test03()  throws Throwable  {
		Appt appt = new Appt(1,1,1,1,1,"test","Test appt");

		int[] recurDaysArr={2,3,4};
		appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
		assertEquals(Appt.RECUR_BY_WEEKLY,appt.getRecurBy());
		assertEquals(recurDaysArr,appt.getRecurDays());
		assertEquals(2,appt.getRecurIncrement());
		assertEquals(Appt.RECUR_NUMBER_FOREVER,appt.getRecurNumber());
	}
	@Test
	public void testString()  throws Throwable  {
		Appt appt = new Appt(15,30,1,1,1,"test","Test appt");
		Appt apptmidnight = new Appt(0,30,1,1,1,"test","Test appt");
		Appt invalidAppt = new Appt(0,0,0,0,0,"","");

		assertEquals("\t1/1/1 at 3:30pm ,test, Test appt\n", appt.toString());
		assertEquals("\t1/1/1 at 12:30am ,test, Test appt\n", apptmidnight.toString());
		assertNull(invalidAppt.toString());
	}
	@Test
	public void testCompare()  throws Throwable  {
		Appt appt = new Appt(1,1,1,1,1,"test","Test appt");
		Appt appt2 = new Appt(1,1,1,1,1,"test","Test appt");

		assertEquals(0, appt.compareTo(appt2));
	}
//add more unit tests as you needed
	
}
