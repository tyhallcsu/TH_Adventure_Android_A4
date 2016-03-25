/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 * Door_Test.java Test Written By: Tyler Hall
 */

package cs314.A4Test;
///////////////////////////////////////////////////////////////
//Door.java JUnit Tests (Door_Tests)
//Junit Tests Written by: Colt Darien
//Test Purpose:		Door_Test is to ensure Door.java adheres to the AC and works to the DoD.
//Acceptance Criteria: Story A AC 1 and 2, Story B AC 2 and 3. 100% branch coverage is achieved.
//
//
//Domain Name: 	
//Room: 	[Room unlocked], [Room locked], [Room w/o any Items], [Room w/ both Items]
//Door:		[Room w/ locked Door], [Room w/ unlocked Door], [Room w/o any Doors], [Room w/ only Doors]
//Player:	[Player location], [Player w/o any Items], [Player w/ Key], [Player w/o Key], [Player w/ Treasure], [Player w/o Treasure], [Player w/ both]
//Key:		[Room w/ Key], [Room w/o Key]
//Equivalence Classes:		[Adjacent (wall/door(locked/unlocked)/room)], [Room (has Item/doesn't have any)], [Player (has Key/doesn't have Key)]
//Boundary Conditions:		[empty room], [1 key, 0 treasure], [0 key, 1 treasure], [1 key, 1 treasure]... [empty], [min], [max], [min-1], [max+1], [Uninitialised]
//
//****Door_Test.java: (achieved 100% branch coverage of Door.java)
//- 4 of Tests: 
//-testEnterLockedDoorWithKey()
//- testEnterLockedDoorWithOutKey()
//- testEnterLockedDoorWithWrongKey()
//- testEnterLockedDoorWithMultiplePlayersAndMultipleKey
//- Coverage Metrics: (100%)
//	- 65 bytecode instructions 	(100%)
//	- 1 class					(100%)
//- 2 methods 				(100%)
//- 15 lines 					(100%)
//- 6 branches 				(100%)
//- 5 complexity 				(100%)
//- Number of Defects Found: 0
//- Number of Defects Fixed: 0
//
///////////////////////////////////////////////////////////////

import junit.framework.TestCase;

//import org.junit.Test;

//import org.testng.annotations.Test;

import org.junit.Test;

import cs314.A4Model.Door;
import cs314.A4Model.Key;
import cs314.A4Model.Player;
import cs314.A4Model.Room;

public class Door_Test extends TestCase{
	
	private Key theKey; //this is the key to be found by the player.
	/** The door's location. */
	private Player p;
	private Room r1, r2, r3;
	private Door theDoor;
	
	@Test
	public void testEnterLockedDoorWithKey() {
		//allocate new objects
		r1 = new Room(); r1.setDesc("R1");
		r2 = new Room(); r2.setDesc("R2");
		theKey = new Key();
		p = new Player();
		//create room
		r1.setSide(1, r2);
		//add item to room
		r1.addItem(theKey);
		//add door between rooms
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		//theDoor.setSide(1, r2);
		//enter player to room
		r1.enter(p);
		//player has key for the door
		p.pickUp(theKey);
		
		assertEquals("R1\n\n",p.getLoc().getDesc());
		assertEquals("Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p));
		assertEquals("R2\n\n",p.getLoc().getDesc());
		
	}

	@Test public void testEnterLockedDoorWithOutKey() {
		//allocate new objects
		r1 = new Room(); r1.setDesc("R1");
		r2 = new Room(); r2.setDesc("R2");
		theKey = new Key();
		p = new Player();
		//create room
		r1.setSide(1, r2);
		//add item to room
		r1.addItem(theKey);
		//add door between rooms
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		//theDoor.setSide(1, r2);
		//enter player to room
		r1.enter(p);
		//player DOESN'T have key for the door
			//p.pickUp(theKey);
		assertEquals("R1\n\n",p.getLoc().getDesc());
		assertEquals("You don't have the key for this door!\nSorry.",theDoor.enter(p));
		assertEquals("R1\n\n",p.getLoc().getDesc());
	}
	
	@Test public void testEnterLockedDoorWithWrongKey() {
		//allocate new objects
		r1 = new Room(); r1.setDesc("R1");
		r2 = new Room(); r2.setDesc("R2");
		theKey = new Key();
		Key wrongKey = new Key();
		p = new Player();
		//create room
		r1.setSide(1, r2);
		//add item to room
		r1.addItem(theKey);
		//add door between rooms
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		//theDoor.setSide(1, r2);
		//enter player to room
		r1.enter(p);
		//player DOESN'T have key for the door (wrong key)
		p.pickUp(wrongKey);
		assertEquals("R1\n\n",p.getLoc().getDesc());
		assertEquals("You don't have the key for this door!\nSorry.",theDoor.enter(p));
		assertEquals("R1\n\n",p.getLoc().getDesc());
	}
	
	@Test public void testEnterLockedDoorWithTwoPlayersAndTwoKeys() {
		//allocate new objects
		r1 = new Room(); r1.setDesc("R1");
		r2 = new Room(); r2.setDesc("R2");
		r3 = new Room(); r3.setDesc("R3");
		//Room outside = new Room();
		//outside.setSide(0, r1);
		theKey = new Key();
		//theKey = new Key();
		p = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		theDoor = new Door(r1, r2, theKey);
		//create room
		r1.setSide(1, theDoor);
		r2.setSide(2, theDoor);
		//add item to room
		r1.addItem(theKey);
		r2.addItem(theKey);
		r3.addItem(theKey);
		//add door between rooms
		
		//r1.setSide(1, theDoor);
		//theDoor.setSide(1, r2);
		//enter player to room
		p.setLoc(r2);
		p2.setLoc(r1);
		p3.setLoc(r3);
		
		//r1.enter(p);
		//r2.enter(p2);
		//System.out.println(p.getLoc());
		//player DOESN'T have key for the door (wrong key)
		p.pickUp(theKey);
		p2.pickUp(theKey);
		p3.pickUp(theKey);
		
		
		assertEquals("R2\n\n",p.getLoc().getDesc());
		assertEquals("Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p));
		assertEquals("R1\n\n",p.getLoc().getDesc());
		assertEquals("Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p));
		assertEquals("R2\n\n",p.getLoc().getDesc());
		
		assertEquals("R1\n\n",p2.getLoc().getDesc());
		assertEquals("Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p2));
		assertEquals("R2\n\n",p2.getLoc().getDesc());
		assertEquals("Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p2));
		assertEquals("R1\n\n",p2.getLoc().getDesc());

		assertEquals("Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p3));
		assertEquals("R3\n\n",p3.getLoc().getDesc());
		
		
		
//		assertEquals((String) "R2\n\n",p2.getLoc().getDesc());
//		assertEquals((String)"Your key works! The door creaks open\nand slams behind you after you pass through.",theDoor.enter(p2));
//		assertEquals((String) "R1\n\n",p2.getLoc().getDesc());
		//System.out.println(theDoor.enter(p2));
		//assertEquals((String) "R1\n\n",p2.getLoc().getDesc());
	}

}