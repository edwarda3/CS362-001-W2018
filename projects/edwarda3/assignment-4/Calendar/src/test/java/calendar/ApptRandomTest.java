package calendar;

import java.util.Calendar;
import java.util.Random;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;


import javax.xml.bind.ValidationEvent;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
	private static final long TestTimeout = 20 * 1000; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setStartHour","setStartMinute","setStartDay","setStartMonth","setStartYear","setTitle","setDescription","setRecurrence","toString","compareTo"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
	}
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
     */
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
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random,1,11);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random,1,59);
				 int startDay=ValuesGenerator.getRandomIntBetween(random,1,28);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startYear=ValuesGenerator.RandInt(random);
				 String title="Untitled Event";
				 String description="No description given.";
				 //Construct a new Appointment object with the initial data	 
				 Appt appt = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
			if(!appt.getValid())continue;

			//execute a random function
			for (int i = 0; i < NUM_TESTS; i++) {
				String methodName = ApptRandomTest.RandomSelectMethod(random);

				if (methodName.equals("setTitle")) {
					String newTitle = (String) ValuesGenerator.getString(random);
					appt.setTitle(newTitle);
				}
				else if (methodName.equals("setDescription")) {
					String newDesc = (String) ValuesGenerator.getString(random);
					appt.setDescription(newDesc);
				}
				else if (methodName.equals("setStartHour")) {
					Boolean validNum = (Boolean) ValuesGenerator.getBoolean(.5f,random);
					appt.setStartHour(validNum ? ValuesGenerator.getRandomIntBetween(random,1,11) : ValuesGenerator.RandInt(random));
				}
				else if (methodName.equals("setStartMinute")) {
					Boolean validNum = (Boolean) ValuesGenerator.getBoolean(.5f,random);
					appt.setStartMinute(validNum ? ValuesGenerator.getRandomIntBetween(random,1,59) : ValuesGenerator.RandInt(random));
				}
				else if (methodName.equals("setStartDay")) {
					Boolean validNum = (Boolean) ValuesGenerator.getBoolean(.5f,random);
					appt.setStartDay(validNum ? ValuesGenerator.getRandomIntBetween(random,1,28) : ValuesGenerator.RandInt(random));
				}
				else if (methodName.equals("setStartMonth")) {
					Boolean validNum = (Boolean) ValuesGenerator.getBoolean(.5f,random);
					appt.setStartMonth(validNum ? ValuesGenerator.getRandomIntBetween(random,1,11) : ValuesGenerator.RandInt(random));
				}
				else if (methodName.equals("setStartYear")) {
					appt.setStartYear(ValuesGenerator.RandInt(random));
				}
				else if (methodName.equals("compareTo")) {
					Boolean dup = ValuesGenerator.getBoolean(.5f,random);
					Appt appt2;
					if(dup){
						appt2 = appt;
					}else{
						appt2 = new Appt(	ValuesGenerator.getRandomIntBetween(random,1,11),
											ValuesGenerator.getRandomIntBetween(random,1,59),
											ValuesGenerator.getRandomIntBetween(random,1,28),
											ValuesGenerator.getRandomIntBetween(random,1,11),
											ValuesGenerator.RandInt(random),
											ValuesGenerator.getString(random),
											ValuesGenerator.getString(random));
					}
					appt.compareTo(appt2);
				}
				else if (methodName.equals("toString")) {
					String str = appt.toString();
				}
				else if (methodName.equals("setRecurrence")) {
					int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
					int[] recurDays = ValuesGenerator.getBoolean(.5f,random) ? ValuesGenerator.generateRandomArray(random, sizeArray) : null;
					int recur = ApptRandomTest.RandomSelectRecur(random);
					int recurIncrement = ValuesGenerator.RandInt(random);
					int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
					appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
					int rNum = appt.getRecurNumber();
					int rBy = appt.getRecurBy();
					int [] rDays = appt.getRecurDays();
					Boolean rec = appt.isRecurring();
					int rInc = appt.getRecurIncrement();
				}
			}

			//Counting
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			if((iteration%10000)==0 && iteration!=0 )
				System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}

		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	
}
