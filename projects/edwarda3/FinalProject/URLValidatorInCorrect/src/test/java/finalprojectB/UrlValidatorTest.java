
package finalprojectB;

import junit.framework.TestCase;

import java.util.Random;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

/* FINAL PROJECT PART B
 * Alex Edwards, edwarda3
 * Jacob Dugan, duganja
 *
 */



public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
      //You can use this function to implement your manual testing
	    UrlValidator uv = new UrlValidator();
        assertTrue(uv.isValid("http://www.google.com"));
        assertTrue(uv.isValid("https://www.facebook.com"));
        assertTrue(uv.isValid("http://twitter.com"));
        assertTrue(uv.isValid("https://apple.com"));
        assertTrue(uv.isValid("ftp://oregonstate.edu"));
        assertTrue(uv.isValid("ftp://oregonstate.edu:80"));
        assertTrue(uv.isValid("http://192.168.1.1"));
        assertTrue(uv.isValid("file:///C:/Users/Alexander/"));

        assertFalse(uv.isValid("htp://oregonstate.edu"));
        assertFalse(uv.isValid("http:/oregonstate.edu"));
        assertFalse(uv.isValid("http//oregonstate.edu"));
        assertFalse(uv.isValid("htp://oregonstate.edu"));
        assertFalse(uv.isValid("http://oregonstate.edu80"));
        assertFalse(uv.isValid("http://oregonstate.edu::10"));

        //A google search
        assertTrue(uv.isValid("https://www.google.com/search?q=software&ie=utf-8&oe=utf-8&client=firefox-b-1-ab"));
   }
   
   
   public void testYourFirstPartition()
   {
	    //You can use this function to implement your First Partition testing
        //We are going to test the partition which contains numbers, i.e
        // ip addresses

       UrlValidator uv = new UrlValidator();
       assertTrue(uv.isValid("http://192.168.1.1"));
       assertTrue(uv.isValid("http://192.168.1.1:2020"));
       assertTrue(uv.isValid("192.168.1.1"));
       assertTrue(uv.isValid("192.168.1.1:2020"));
       assertTrue(uv.isValid("ftp://192.168.1.255"));
       assertTrue(uv.isValid("ftp://192.168.1.255:80"));
       assertTrue(uv.isValid("192.168.1.255/settings"));
       assertTrue(uv.isValid("192.168.1.255/settings.aspx"));
       assertTrue(uv.isValid("http://192.168.1.255/settings"));
       assertTrue(uv.isValid("http://192.168.1.255/settings.aspx"));
   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   
        //We are going to test the partition which uses queries.
        //These inputs typically have a "?" followed by a user's inputs

       UrlValidator uv = new UrlValidator();
       assertTrue(uv.isValid("https://www.google.com/search?q=atlanta&rlz=1C1CHBF_enUS729US729&oq=atlanta&aqs=chrome..69i57j69i60j5l2j69i61l2.1140j0j7&sourceid=chrome&ie=UTF-8"));
       assertTrue(uv.isValid("https://www.youtube.com/results?search_query=fire+bts"));
       assertTrue(uv.isValid("https://www.youtube.com/watch?v=ALj5MKjy2BU"));
       assertTrue(uv.isValid("https://www.youtube.com/watch?v=ALj5MKjy2BU&feature=youtu.be&t=2m12s"));
       assertTrue(uv.isValid("https://www.facebook.com/osubeavers/?hc_ref=ARQuoD2MzwRKxqfQuN2XpkO3aiwKKba5mM_GyPACdDP6sgadYD5fxzjWz4H-Dd37LvU&fref=nf"));
   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing
       UrlValidator urlVal = new UrlValidator(null, null, 1);
       //You can use this function for programming based testing
       int NUM_TESTS=100;

       System.out.println("Start testing...");

       try{
           for (int iteration = 0; iteration < NUM_TESTS; iteration++) {

               boolean expected = true;
               ResultPair[] choose = new ResultPair[4];
               Random rand = new Random();

               choose[0] = preface[rand.nextInt(5)];
               choose[1] = website[rand.nextInt(5)];
               choose[2] = path[rand.nextInt(10)];
               choose[3] = query[rand.nextInt(2)];


               String url = "";
               for(int i = 0; i < 4; i++){
                   url = url + choose[i].item;
                   expected &= choose[i].valid;
               }

               boolean result = urlVal.isValid(url);

               assertEquals(expected, result);



           }
       }catch(NullPointerException e){

       }



   }


    public ResultPair[] preface = {new ResultPair("http://", true),
            new ResultPair("https://", true),
            new ResultPair("http:/", false),
            new ResultPair("http:", false),
            new ResultPair("http/", false),
            new ResultPair(":/", false)};

    public ResultPair[] website = {new ResultPair("www.google.com", true),
            new ResultPair("oregonstate.edu", true),
            new ResultPair("youtube.com", true),
            new ResultPair("0.0.0.0", true),
            new ResultPair("192.168.1.1", true),
            new ResultPair("256.256.256.256", false)};

    public ResultPair[] path = {new ResultPair("/test1", true),
            new ResultPair("/t123", true),
            new ResultPair("/$23", true),
            new ResultPair("/..", false),
            new ResultPair("/../", false),
            new ResultPair("/test1/", true),
            new ResultPair("/#", false),
            new ResultPair("", true),
            new ResultPair("/../file", false),
            new ResultPair("/..//file", false)};

    public ResultPair[] query = {new ResultPair("?action=view", true),
            new ResultPair("?action=edit&mode=up", true),
            new ResultPair("", true)};
}
