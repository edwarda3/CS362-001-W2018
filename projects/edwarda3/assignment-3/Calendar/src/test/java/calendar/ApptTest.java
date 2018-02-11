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
		assertTrue(appt.isRecurring());
	}
	@Test
	public void testString()  throws Throwable  {
		Appt appt = new Appt(15,30,1,1,1,"test","Test appt");
		Appt apptmidnight = new Appt(0,30,1,1,1,"test","Test appt");
		Appt apptnoon = new Appt(12,0,1,1,1,"test","Test appt");
		Appt invalidAppt = new Appt(0,0,0,0,0,"","");

		for(int i=0; i<23; i++){
			Appt apptLoop = new Appt(i,30,1,1,1,"test","Test appt");
			String meridian = (apptLoop.getStartHour()>11? "pm" : "am");
			int expectedHour = (apptLoop.getStartHour()>11? apptLoop.getStartHour()-12 : apptLoop.getStartHour());
			expectedHour = expectedHour==0 ? 12 : expectedHour;

			assertEquals("\t1/1/1 at "+expectedHour+":30"+meridian+" ,test, Test appt\n", apptLoop.toString());
		}
		assertEquals("\t1/1/1 at 3:30pm ,test, Test appt\n", appt.toString());
		assertEquals("\t1/1/1 at 12:0pm ,test, Test appt\n", apptnoon.toString());
		assertEquals("\t1/1/1 at 12:30am ,test, Test appt\n", apptmidnight.toString());
		assertNull(invalidAppt.toString());
	}
	@Test
	public void testCompare()  throws Throwable  {
		Appt appt = new Appt(1,1,1,1,1,"test","Test appt");
		Appt appt2 = new Appt(2,2,2,2,2,"test","Test appt");

		assertEquals(-5, appt.compareTo(appt2));
		assertNotNull(appt.getRecurDays());
		assertFalse(appt.isRecurring());
		Appt appthour1 = new Appt(0,1,1,1,1,"","");
		Appt appthour2 = new Appt(23,1,1,1,1,"","");
		Appt apptmin1 = new Appt(1,0,1,1,1,"","");
		Appt apptmin2 = new Appt(1,59,1,1,1,"","");
		Appt apptday1 = new Appt(1,1,1,1,1,"","");
		Appt apptday2 = new Appt(1,1,31,1,1,"","");
		Appt apptmonth1 = new Appt(1,1,1,1,1,"","");
		Appt apptmonth2 = new Appt(1,1,1,12,1,"","");
		assertTrue(appthour1.getValid());
		assertTrue(appthour2.getValid());
		assertTrue(apptday1.getValid());
		assertTrue(apptday2.getValid());
		assertTrue(apptmin1.getValid());
		assertTrue(apptmin2.getValid());
		assertTrue(apptmonth1.getValid());
		assertTrue(apptmonth2.getValid());

		for(int i=1; i<13; i++){
			Appt apptStart = new Appt(1,1,1,i,1,"","");
			assertTrue(apptStart.getValid());
		}


		Appt apptGet1 = new Appt(1,1,1,1,1,"test","Test appt");
		apptGet1.setStartHour(100);
		assertFalse(apptGet1.getValid());
		Appt apptGet2 = new Appt(1,1,1,1,1,"test","Test appt");
		apptGet2.setStartMinute(100);
		assertFalse(apptGet2.getValid());
		Appt apptGet3 = new Appt(1,1,1,1,1,"test","Test appt");
		apptGet3.setStartDay(100);
		assertFalse(apptGet3.getValid());
		Appt apptGet4 = new Appt(1,1,1,1,1,"test","Test appt");
		apptGet4.setStartMonth(100);
		assertFalse(apptGet4.getValid());
	}

//add more unit tests as you needed
	
}
