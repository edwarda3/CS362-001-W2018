package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {

	/**
	 * Generate Random Tests that tests CalDay Class.
	 * */
	private static final long TestTimeout = 1;// * 1000; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random){
		String[] methodArray = new String[] {"addAppt","getApptRange","deleteAppt","permute"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

		return methodArray[n]; // return the method name
	}

	@Test
	public void randomtest()  throws Throwable  {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");

		try{
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				//create object
				TimeTable tt = new TimeTable();
				GregorianCalendar newyears = new GregorianCalendar(2010,1,1);
				GregorianCalendar newyears2 = new GregorianCalendar(2050,1,1);
				Appt appt = new Appt(1,1,2,1,2018,"test","Test appt");
				Appt appt2 = new Appt(2,1,3,1,2018,"test2","Test2 appt");
				Appt appt3 = new Appt(2,1,3,1,2017,"test3","Test3 appt");
				int[] rdays = {2,3,4};
				appt2.setRecurrence(rdays,Appt.RECUR_BY_WEEKLY,2,Appt.RECUR_NUMBER_FOREVER);
				LinkedList<Appt> lla = new LinkedList<Appt>();
				lla.add(appt); lla.add(appt2); lla.add(appt3);

				//execute a random function
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = TimeTableRandomTest.RandomSelectMethod(random);
					System.out.println(methodName);
					if(methodName.equals("addAppt ")){
						Appt randAppt = new Appt(ValuesGenerator.getRandomIntBetween(random, 1, 11),
								ValuesGenerator.getRandomIntBetween(random, 1, 59),
								ValuesGenerator.getRandomIntBetween(random, 1, 28),
								ValuesGenerator.getRandomIntBetween(random, 1, 11),
								ValuesGenerator.getRandomIntBetween(random, 2000, 2060),
								ValuesGenerator.getString(random),
								ValuesGenerator.getString(random));
						lla.add(randAppt);
					}
					else if(methodName.equals("getApptRange")){
						LinkedList<CalDay> cd = tt.getApptRange(lla,newyears,newyears2);
					}
					else if(methodName.equals("deleteAppts")){
						int cases = ValuesGenerator.getRandomIntBetween(random,0,2);
						if( cases == 0) {
							tt.deleteAppt(lla, appt);
							lla.add(appt);
						} else if (cases==1){
							tt.deleteAppt(lla, null);
						} else {
							Appt apptnew = new Appt(0,0,0,0,0,"","");
							tt.deleteAppt(lla,apptnew);
						}
					}
					/*else if(methodName.equals("permute")){
						LinkedList<Appt> lla2 = new LinkedList<Appt>();
						lla2.add(appt); lla2.add(appt3);
						int[] pv = {0,1};
						tt.permute(lla2,pv);
					}*/
				}

				//Counting
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				//if((iteration%10000)==0 && iteration!=0 )
				//	System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}

		}catch(NullPointerException e){

		}

		System.out.println("Done testing...");
	}


	
}
