/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 * AllTests.java Test Written By: Tyler Hall
 */

/* NOTE1: 
 * Your tests should bypass the program's interface. 
 * The tests should not run the GUI code. 
 * The tests should create instances of the classes under test and set them up so that different configurations can be tested.
 * 
 * NOTE2:
 * Use the EclEmma Eclipse plugin coverage tool to track test coverage.
 * 
 * NOTE3:
 * Create coverage reports.
 * record these numbers in the overview.txt file. 
 * See Split User Story A, AC 5 for specific items that must be included in the coverage numbers reported.
 * 
 * NOTE4:
 * Make sure to test under a variety of Adventure Game cave configurations such as caves with several Treasure and Key objects, and several doors in a room.
 */

// http://www.tutorialspoint.com/junit/junit_suite_test.htm
package cs314.A4Test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import org.junit.runner.RunWith;
//import org.junit.runners.Suite;

//import junit.framework.JUnit4TestAdapter;
//This section declares all of the test classes in the program.
//@RunWith (Suite.class)
//@Suite.SuiteClasses ({ cs314_A4Test.class }) // Add test classes here.

@RunWith(Suite.class)
@Suite.SuiteClasses({
		Door_Test.class,
		Player_Test.class,
		Room_Test.class

})
public class AllTests {
	
	//wall = new Wall_Junit_Test();
	public static Test suite(){
		//Class[] testClasses = new Class[]{Door_Test.class, Player_Test.class, Room_Test.class};
		//TestSuite suite= new TestSuite(testClasses);
		TestSuite suite = new TestSuite(Suite.class);
		suite.addTestSuite(Door_Test.class);
		suite.addTestSuite(Player_Test.class);
		suite.addTestSuite(Room_Test.class);
		return suite;	
	}
	
//	public static Test suite2(){
//		TestSuite suite = new TestSuite(Door_Test.class);
//		suite.addTestSuite(Door_Test.class);	
//		return suite;	
//	}
	
	// Execution begins at main(). In this test class, we will execute
	// a text test runner that will tell you if any of your tests fail.
//	public void main (String[] args){
//		junit.textui.TestRunner.run (suite());
//
//		
//	}

	// The suite() method is helpful when using JUnit 3 Test Runners or Ant.
//	public static junit.framework.Test suite(){
//		return new JUnit4TestAdapter (AllTests.class);
//	}

}
