package calendar;


import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    /**
     * Generate Random Tests that tests CalDay Class.
	 * */
	private static final long TestTimeout = 20 * 1000; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random){
		String[] methodArray = new String[] {"addAppt","iterator","toString","getters"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

		return methodArray[n] ; // return the method name
	}

	 @Test
	  public void randomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 System.out.println("Start testing...");

		 try{
			 for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				 long randomseed =System.currentTimeMillis(); //10
				 Random random = new Random(randomseed);

				 //create object
				 int someDay = ValuesGenerator.getRandomIntBetween(random,1,28);
				 int someMonth = ValuesGenerator.getRandomIntBetween(random,1,11);
				 int someYear = ValuesGenerator.RandInt(random);
				 GregorianCalendar someDate = new GregorianCalendar(someYear,someMonth,someDay);
				 CalDay cd = new CalDay(someDate);
				 CalDay empty = new CalDay();
				 cd.addAppt(new Appt(5,20,someDay,someMonth,someYear,ValuesGenerator.getString(random),ValuesGenerator.getString(random)));

				 //execute a random function
				 for (int i = 0; i < NUM_TESTS; i++) {
				 	String methodName = RandomSelectMethod(random);

				 	if(methodName.equals("addAppt")){
				 		for( int j=0; j<NUM_TESTS; j++){
							Boolean b = ValuesGenerator.getBoolean(.5f,random);
							Appt randAppt;
							if(b) {
								randAppt = new Appt(ValuesGenerator.getRandomIntBetween(random, 1, 11),
										ValuesGenerator.getRandomIntBetween(random, 1, 59),
										ValuesGenerator.getRandomIntBetween(random, 1, 28),
										ValuesGenerator.getRandomIntBetween(random, 1, 11),
										ValuesGenerator.RandInt(random),
										ValuesGenerator.getString(random),
										ValuesGenerator.getString(random));
							}
							else{
								Boolean b2 = ValuesGenerator.getBoolean(.5f,random);
								randAppt = new Appt(ValuesGenerator.getRandomIntBetween(random,1,11),ValuesGenerator.getRandomIntBetween(random,1,59),someDay+1,someMonth,someYear,ValuesGenerator.getString(random),ValuesGenerator.getString(random));
								if(b2) randAppt = new Appt(ValuesGenerator.getRandomIntBetween(random,1,11),ValuesGenerator.getRandomIntBetween(random,1,59),someDay-1,someMonth,someYear,ValuesGenerator.getString(random),ValuesGenerator.getString(random));
							}
							cd.addAppt(randAppt);
				 		}
					}
					else if(methodName.equals("iterator")){
						cd.iterator();
						empty.iterator();
					}
				 	else if(methodName.equals("toString")){
				 		cd.toString();
					}
				 	else if(methodName.equals("getters")){
						LinkedList<Appt> lla = cd.getAppts();
						int sizeAppts = cd.getSizeAppts();
						int day = cd.getDay();
						int month = cd.getMonth();
						int year = cd.getYear();
						Boolean valid = cd.isValid();
					}
				 }

				 //Counting
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				 //if((iteration%10000)==0 && iteration!=0 )
					 System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 }

		 }catch(NullPointerException e){

		 }

		 System.out.println("Done testing...");
	 }


	
}
