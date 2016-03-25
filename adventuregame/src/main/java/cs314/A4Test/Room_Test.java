/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.A4Test;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import cs314.A4Model.Door;
import cs314.A4Model.Item;
import cs314.A4Model.Key;
import cs314.A4Model.Player;
import cs314.A4Model.Room;
import cs314.A4Model.Treasure;
import cs314.A4Model.Wall;


///////////////////////////////////////////////////////////////
//Room.java JUnit Tests (Room_Tests)
//Junit Tests Written by: Tyler Hall
//Test Purpose:		Room_Test is to ensure Room.java adheres to the AC and works to the DoD.
//Acceptance Criteria: Story A AC 1 and 2, Story B AC 2 and 3. 100% branch coverage is achieved.
//
//
//Domain Name: 	Room: 		[Room unlocked], [Room locked], [Room w/o any Items], [Room w/ both Items]
//				Door:		[Room w/ locked Door], [Room w/ unlocked Door], [Room w/o any Doors], [Room w/ only Doors]
//				Wall:		[Room w/o any Walls], [Room w/ Wall], [Room w/o Wall]
//				Player:		[Player location], [Player w/o any Items], [Player w/ Key], [Player w/o Key], [Player w/ Treasure], [Player w/o Treasure], [Player w/ both]
//				Key:		[Room w/ Key], [Room w/o Key]
//				Treasure:	[Room w/ Treasure], [Room w/o Treasure]
//Equivalence Classes:		[Adjacent (wall/door(locked/unlocked)/room)], [Room (has Item/doesn't have any)], [Player (has Item/doesn't have any)]
//Boundary Conditions:		[empty room], [1 key, 0 treasure], [0 key, 1 treasure], [1 key, 1 treasure]... [empty], [min], [max], [min-1], [max+1], [Uninitialised]
//
//Results: (achieved 100% branch coverage of Room.java)
//  - Coverage Metrics:
//	- 178 bytecode instructions (100%)
//	- 1 class (Room)			(100%)
//	- 10 methods 				(100%)
//	- 46 lines 					(100%)
//	- 18 branches 				(100%)
//	- 19 complexity 			(100%)
//
//Test Cases:
//Four (4) Tests: 
//  - enter():		tests the enter method in Room.java, making sure the player enters the room properly
//	- exit(): 		tests the exit method in Room.java, making sure the player exits the room properly
//	- addItem(): 	tests the addItem method in Room.java, making sure the item adds to the room properly.
//	- removeItem():	tests the removeItem method in Room.java, making sure the item removes from the room properly.
//
//Defects:
  // - Number of Defects Found: 4
  // 	1) setSide(int direction, CaveSite m): 		added OutOfBounds checking where direction can only be 0-5 (not less than 0, or more than 5) 	(ex: r1.setSide(-1,r2))
  // 	2) String exit(int direction, Player p): 	added OutOfBounds checking where direction can only be 0-5 (not less than 0, or more than 5) 	(ex: r1.exit(-1,p))
  // 	3) addItem(Item theItem): 					added check if the item already exists in the room. No more duplicate Items!					(ex: r1.addItem(i); r1.addItem(i);)
  // 	4) setSide(int direction, CaveSite m): 		added checks to ensure that you cannot setSide an adjacent room to itself 						(ex: r1.setSide(0,r1))
  // - Number of Defects Fixed: 4
  // 	1) setSide() 	now returns "Invalid direction!" if direction is out of bounds.
  // 	2) exit()		now returns "Invalid direction!" if direction is out of bounds.
  // 	3) addItem()	now returns "Already in room." if the Item already exists in the room.
  // 	4) setSide() 	now returns "Cannot set room side equal to this.room." if room side is set to itself.
//
///////////////////////////////////////////////////////////////


public class Room_Test extends TestCase {

	private Room outside;
	private Room r1, r2, r3, r4, r5;
	private Key theKey;
	private Treasure theTreasure;
	private Player p;
	private Item myItem;
	private Door theDoor;


	//BB Test: addItem()
		// tests the addItem method in Room.java, making sure the item adds to the
		// room properly.
		// Input Domain: contents.add(theItem)
		// Equivalence Classes: theItem: Key, Treasure, Player
		// Boundary Conditions: empty, min, max, min-1, max+1, Uninitialised
		// Test Cases: add Key, add Treasure, add Item, add min/max items, add empty
		// items, add to empty/full room, add outside
		// Tests for: addItem() add item (contents) to the room properly.
		// Object state: Room (contents), Player (Items
		// (key,treasure,location,items)), Wall, Item (Key/Treasure), Door
		// (locked/unlocked)
	@Test public void testAddItem() {
		outside = new Room();
		r1 = new Room();
		r2 = new Room();
		theKey = new Key();
		theTreasure = new Treasure();
		assertEquals("Item: added to room.", outside.addItem(theKey));
		assertEquals("Item: added to room.", r1.addItem(theTreasure));
		assertEquals("Item: added to room.", r2.addItem(theKey));
	}
	
	//BB Test: testAddEmptyItem()
		//tests adding an uninitilized (empty) Item to the Room
		//how the output state is checked for correctness:
	@Test public void testAddEmptyItem() {
		r1 = new Room();
		r1.addItem(myItem);
		// System.out.println(r1.getRoomContents());
		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(myItem);
		// System.out.println(contents);
		assertEquals(contents, r1.getRoomContents());

	}
	
	//BB Test: testAddOneItem()
		//tests adding ONE Item to the Room
	@Test public void testAddOneItem() {
		r1 = new Room();
		myItem = new Item();
		r1.addItem(myItem);
		// System.out.println(r1.getRoomContents());
		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(myItem);
		// System.out.println(contents);
		assertEquals(contents, r1.getRoomContents());

	}

	//BB Test: testAddTwoItems()
		//tests adding TWO Items to the Room
	@Test public void testAddTwoItems() {
		r1 = new Room();
		myItem = new Item();
		Item myItem2 = new Item();
		r1.addItem(myItem);
		r1.addItem(myItem2);
		// System.out.println(r1.getRoomContents());
		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(myItem);
		contents.add(myItem2);
		// System.out.println(contents);
		assertEquals(contents, r1.getRoomContents());

	}


	//BB Test: testAddMoreThanTwoItems()
		//tests adding FIVE (more than two) Items to the Room
	@Test public void testAddMoreThanTwoItems() {
		r1 = new Room();
		myItem = new Item();
		Item myItem2 = new Item();
		Item myItem3 = new Item();
		Item myItem4 = new Item();
		Item myItem5 = new Item();
		r1.addItem(myItem);
		r1.addItem(myItem2);
		r1.addItem(myItem3);
		r1.addItem(myItem4);
		r1.addItem(myItem5);
		// System.out.println(r1.getRoomContents());
		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(myItem);
		contents.add(myItem2);
		contents.add(myItem3);
		contents.add(myItem4);
		contents.add(myItem5);
		// System.out.println(contents);
		assertEquals(contents, r1.getRoomContents());

	}

	//BB Test: testDoubleAddItem()
		//tests adding TWO (OF THE SAME) Item to the Room; Item, Key, and Treasure tested.
	@Test public void testDoubleAddItem() {
		//double add Item
		r1 = new Room();
		myItem = new Item();
		assertEquals("Item: added to room.",r1.addItem(myItem));
		assertEquals("Already in room.",r1.addItem(myItem));  //DEFECT
		//double add key
		r2 = new Room();
		theKey = new Key();
		r2.setDesc("r2");
		assertEquals("Item: added to room.",r2.addItem(theKey));
		assertEquals("Already in room.",r2.addItem(theKey));  //DEFECT
		//double add treasure
		r3 = new Room();
		theTreasure = new Treasure();
		r3.setDesc("r3");
		assertEquals("Item: added to room.",r3.addItem(theTreasure));
		assertEquals("Already in room.",r3.addItem(theTreasure));  //DEFECT

	}

	//BB Test: testRemoveItem()
		// tests the removeItem method in Room.java, making sure the item removes
		// from the room properly.
		// Input Domain: contents.remove(theItem)
		// Equivalence Classes: theItem: Key, Treasure, Player
		// Boundary Conditions: empty, min, max, min-1, max+1, Uninitialised
		// Test Cases: remove Key, remove Treasure, remove Item, remove min/max
		// items, remove empty items, remove to empty/full room, remove outside
		// Tests for: removeItem() remove item (contents) to the room properly.
		// Object state: Room (contents), Player (Items
		// (key,treasure,location,items)), Wall, Item (Key/Treasure), Door
		// (locked/unlocked)
	@Test public void testRemoveItem() {
		// ALLOCATE NEW ROOMS
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r3 = new Room();
		r3.setDesc("r3");
		r4 = new Room();
		r4.setDesc("r4");
		r5 = new Room();
		r5.setDesc("r5");
		ArrayList<Item> contents = new ArrayList<Item>(); // empty item list

		// TEST GET DESCRIPTION WITHOUT ITEMS
		assertEquals("r1" + '\n' + '\n', r1.getDesc());
		assertEquals("r2" + '\n' + '\n', r2.getDesc());
		assertEquals("r3" + '\n' + '\n', r3.getDesc());
		assertEquals("r4" + '\n' + '\n', r4.getDesc());
		assertEquals("r5" + '\n' + '\n', r5.getDesc());

		// ALLOCATE KEY AND TREASURE
		theKey = new Key();
		theKey.setDesc("TheKey.");
		theTreasure = new Treasure();
		theTreasure.setDesc("TheTreasure.");

		// TEST GET DESCRIPTION WITH ITEMS
		r1.addItem(theTreasure);
		contents.add(theTreasure);
		r1.addItem(theKey);
		contents.add(theKey);
		r1.getRoomContents();
		// System.out.println(r1.getDesc());
		// if(!r1.roomEmptyHELPER() && !contents.isEmpty()){
		assertEquals("r1" + '\n' + '\n', r1.getDesc());
		// }
		r1.removeItem(theTreasure);
		contents.remove(theTreasure);
		r1.removeItem(theKey);
		contents.remove(theKey);

		// ADD THEN REMOVE
		r1.addItem(theTreasure);
		r2.addItem(theKey);
		r1.removeItem(theTreasure);
		r2.removeItem(theKey);

		// TEST EMPTY ROOM
		// r1.roomEmptyHELPER();
		assertEquals(true, r1.roomEmpty());
		assertEquals(true, r2.roomEmpty());

		// EMPTY ROOM

		assertEquals(contents, r1.getRoomContents());
		assertEquals(contents, r2.getRoomContents());
		assertEquals(contents, r3.getRoomContents());

		// JUST TREASURE
		r1.addItem(theTreasure);
		contents.add(theTreasure);
		assertEquals(contents, r1.getRoomContents());
		contents.remove(theTreasure);

		// JUST KEY
		r2.addItem(theKey);
		contents.add(theKey);
		assertEquals(contents, r2.getRoomContents());
		contents.remove(theKey);

		// BOTH
		r3.addItem(theKey);
		contents.add(theKey);
		r3.addItem(theTreasure);
		contents.add(theTreasure);
		assertEquals(contents, r3.getRoomContents());
		contents.remove(theKey);
		contents.remove(theKey);
	}

	
	//BB Test: testRemoveEmptyItem()
		//tests ...
	@Test public void testRemoveEmptyItem() {
		r1 = new Room();
		r1.setDesc("r1");
		ArrayList<Item> contents = new ArrayList<Item>();
		r1.removeItem(myItem);
		contents.remove(myItem);
		assertEquals(contents, r1.getRoomContents());

	}

	//BB Test: testRemoveItemNotInRoom()
		//tests ...
	@Test public void testRemoveItemNotInRoom() {
		myItem = new Item();
		r1 = new Room();
		r1.setDesc("r1");
		ArrayList<Item> contents = new ArrayList<Item>();
		r1.removeItem(myItem);
		contents.remove(myItem);
		assertEquals(contents, r1.getRoomContents());
	}

	
	//BB Test: testRemoveOneItem()
		//tests ...
	@Test public void testRemoveOneItem() {
		myItem = new Item();
		r1 = new Room();
		r1.setDesc("r1");
		ArrayList<Item> contents = new ArrayList<Item>();
		r1.addItem(myItem);
		contents.add(myItem);
		r1.removeItem(myItem);
		contents.remove(myItem);
		assertEquals(contents, r1.getRoomContents());

	}

	//BB Test: testRemoveTwoItems()
		//tests ...
	@Test public void testRemoveTwoItems() {
		myItem = new Item();
		Item myItem2 = new Item();
		r1 = new Room();
		r1.setDesc("r1");
		ArrayList<Item> contents = new ArrayList<Item>();
		r1.addItem(myItem);
		contents.add(myItem);
		r1.addItem(myItem2);
		contents.add(myItem2);
		r1.removeItem(myItem);
		contents.remove(myItem);
		r1.removeItem(myItem2);
		contents.remove(myItem2);
		assertEquals(contents, r1.getRoomContents());

	}

	
	//BB Test: testRemoveKey()
		//tests ...
	@Test public void testRemoveKey() {
		theKey = new Key();
		r1 = new Room();
		r1.setDesc("r1");
		ArrayList<Item> contents = new ArrayList<Item>();
		r1.addItem(theKey);
		contents.add(theKey);
		r1.removeItem(theKey);
		contents.remove(theKey);
		assertEquals(contents, r1.getRoomContents());
	}

	
	//BB Test: testRemoveTreasure()
		//tests ...
	@Test public void testRemoveTreasure() {
		theTreasure = new Treasure();
		r1 = new Room();
		r1.setDesc("r1");
		ArrayList<Item> contents = new ArrayList<Item>();
		r1.addItem(theTreasure);
		contents.add(theTreasure);
		r1.removeItem(theTreasure);
		contents.remove(theTreasure);
		assertEquals(contents, r1.getRoomContents());

	}

	//BB Test: testEnter()
		// tests the enter method in Room.java, making sure the player enters the
		// room properly
		// Input Domain: side[direction(0-5)].enter(p)
		// Equivalence Classes: side[]: u, d, n, s, e, w
		// Boundary Conditions: empty, min, max, min-1, max+1, Uninitialised
		// Test Cases: enter wall, enter outside, enter door, enter locked door
		// Tests for: enter() set player location to enter side of room correctly.
		// Object state: Room (contents), Player (Key/No Key), Wall, Item
		// (Key/Treasure), Door (locked/unlocked)
	@Test public void testEnter() {
		// ALLOCATE NEW ROOMS
		r1 = new Room();
		r1.setDesc("r1");
		p = new Player();
		assertEquals("", r1.enter(p));
		// r1.enter(p);
	}

	
	//BB Test: testEnterWall()
		//tests ...
	@Test public void testEnterWall() {
		Wall w1 = new Wall();
		p = new Player();
		assertEquals("Ouch! That hurts.\n", w1.enter(p));
	}

	
	//BB Test: testEnterLockedDoor()
		//tests ...
	@Test public void testEnterLockedDoor() {
		p = new Player();
		theKey = new Key();
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r1.addItem(theKey);
		r1.setSide(0, r2);
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		r1.enter(p);
		// p.pickUpHELPER(theKey);
		// System.out.println(r1.exitHELPER(1, p));
		assertEquals("You don't have the key for this door!\nSorry.",
				r1.exit(1, p));

	}

	
	//BB Test: testEnterUnlockedDoor()
		//tests ...
	@Test public void testEnterUnlockedDoor() {
		p = new Player();
		theKey = new Key();
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r1.addItem(theKey);
		r1.setSide(0, r2);
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		r1.enter(p);
		p.pickUp(theKey);
		// System.out.println(r1.exitHELPER(1, p));
		assertEquals(
				"Your key works! The door creaks open\nand slams behind you after you pass through.",
				r1.exit(1, p));

	}

	
	//BB Test: testEnterUndefinedRoom()
		//tests ...
	@Test public void testEnterUndefinedRoom() {
		p = new Player();
		r1 = new Room();
		r1.setDesc("r1");
		r1.setSide(0, r2);
		// theDoor = new Door(r1,r2,theKey);
		// r1.setSide(1, theDoor);
		r1.enter(p);
		// p.pickUpHELPER(theKey);
		// p.haveItem(theKey);
		// System.out.println(p.haveItem(theKey));
		// System.out.println(r1.exitHELPER(5, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(5, p));

	}

	
	//BB Test: testEnterFullRoom()
		//tests ...
	@Test public void testEnterFullRoom() {
		p = new Player();
		theKey = new Key();
		theTreasure = new Treasure();
		myItem = new Item();
		r1 = new Room();
		r1.setDesc("r1");
		r1.addItem(theKey);
		r1.addItem(theTreasure);
		r1.addItem(myItem);
		r1.enter(p);
		p.pickUp(theKey);
		p.pickUp(theTreasure);
		p.pickUp(myItem);  //DEFECT
		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(theKey);
		contents.add(theTreasure);
		// contents.add(myItem);
		assertEquals(contents, p.getItems());

	}

	
	//BB Test: testEnterMultiplePlayersOneKeyLockedDoor()
		//tests ...
	@Test public void testEnterMultiplePlayersOneKeyLockedDoor() {
		p = new Player();
		Player p2 = new Player();
		theKey = new Key();
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r1.addItem(theKey);
		r1.setSide(0, r2);
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		r1.enter(p);
		r1.enter(p2);
		p.pickUp(theKey);
		// p.pickUpHELPER(theKey);
		// System.out.println(r1.exitHELPER(1, p));
		assertEquals(
				"Your key works! The door creaks open\nand slams behind you after you pass through.",
				r1.exit(1, p));
		assertEquals("You don't have the key for this door!\nSorry.",
				r1.exit(1, p2));
		// System.out.println(r1.exitHELPER(1, p2));

	}

	//BB Test: testExit()
		// tests the exit method in Room.java, making sure the player exits the room
		// properly
		// Input Domain: side[direction(0-5)].enter(p)
		// Equivalence Classes: side[]: u, d, n, s, e, w
		// Boundary Conditions: empty, min, max, min-1, max+1, Uninitialised
		// Test Cases: exit wall, exit outside, exit door, exit locked door
		// Tests for: exit() set player location to exit side of room correctly.
		// Object state: Room (contents), Player (Key/No Key), Wall, Item
		// (Key/Treasure), Door (locked/unlocked)
	@Test public void testExit() {
		// ALLOCATE NEW ROOMS
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r1.setSide(0, r2);
		r1.setSide(1, r2);
		r1.setSide(2, r2);
		r1.setSide(3, r2);
		r1.setSide(4, r2);
		r1.setSide(5, r2);
		p = new Player();
		// r1.exitHELPER(0, p);
		assertEquals("", r1.exit(0, p));
	}

	
	//BB Test: testExitWall()
		//tests when a player exits to a wall that the correct statement is returned.
	@Test public void testExitWall() {
		Wall w1 = new Wall();
		r1 = new Room();
		r1.setSide(5, w1);
		p = new Player();
		r1.enter(p);
		assertEquals("Ouch! That hurts.\n", r1.exit(5, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(0, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(1, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(2, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(3, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(4, p));
		assertEquals("Invalid direction!", r1.exit(6, p)); //DEFECT
		assertEquals("Invalid direction!", r1.exit(-1, p));  //DEFECT

	}

	
	//BB Test: testSetSideEqualToItself()
		//tests 
	@Test public void testSetSideEqualToItself() {
		r1 = new Room();
		r2 = new Room();
		r1.setDesc("R1:");
		p = new Player();
		// INVALID
		assertEquals("Cannot set room side equal to this.room.",
				r1.setSide(0, r1));  //DEFECT
		assertEquals("Cannot set room side equal to this.room.",
				r1.setSide(1, r1));  //DEFECT
		assertEquals("Cannot set room side equal to this.room.",
				r1.setSide(2, r1));  //DEFECT
		assertEquals("Cannot set room side equal to this.room.",
				r1.setSide(3, r1));  //DEFECT
		assertEquals("Cannot set room side equal to this.room.",
				r1.setSide(4, r1));  //DEFECT
		assertEquals("Cannot set room side equal to this.room.",
				r1.setSide(5, r1));  //DEFECT
		// VALID
		assertEquals("Side set.", r1.setSide(0, r2));
		assertEquals("Side set.", r1.setSide(1, r2));
		assertEquals("Side set.", r1.setSide(2, r2));
		assertEquals("Side set.", r1.setSide(3, r2));
		assertEquals("Side set.", r1.setSide(4, r2));
		assertEquals("Side set.", r1.setSide(5, r2));
	}

	
	//BB Test: testSetSideOutOfBounds()
		//tests ...
	@Test public void testSetSideOutOfBounds() {
		r1 = new Room();
		r2 = new Room();
		r1.setDesc("R1:");
		p = new Player();
		assertEquals("Invalid direction!", r1.setSide(-1, r2));
		assertEquals("Invalid direction!", r1.setSide(6, r2));
		assertEquals("Invalid direction!", r1.setSide(999, r2));
	}

	
	//BB Test: testExitSameRoom()
		//tests ...
	@Test public void testExitSameRoom() {
		r1 = new Room();
		p = new Player();
		assertEquals("Invalid direction!", r1.exit(6, p));
		assertEquals("Invalid direction!", r1.exit(-1, p));

	}

	
	//BB Test: testExitLockedDoor()
		//tests ...
	@Test public void testExitLockedDoor() {
		p = new Player();
		theKey = new Key();
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r1.addItem(theKey);
		r1.addItem(theKey);		
		//System.out.println(r1.getRoomContents().toString());
		//System.out.println(r1.getDesc());
		r1.setSide(0, r2);
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		r1.enter(p);
		// p.pickUpHELPER(theKey);
		assertEquals("You don't have the key for this door!\nSorry.",r1.exit(1, p));

	}

	
	//BB Test: testExitUnlockedDoor()
		//tests ...
	@Test public void testExitUnlockedDoor() {
		p = new Player();
		theKey = new Key();
		r1 = new Room();
		r1.setDesc("r1");
		r2 = new Room();
		r2.setDesc("r2");
		r1.addItem(theKey);
		r1.setSide(0, r2);
		theDoor = new Door(r1, r2, theKey);
		r1.setSide(1, theDoor);
		r1.enter(p);
		p.pickUp(theKey);
		assertEquals(
				"Your key works! The door creaks open\nand slams behind you after you pass through.",
				r1.exit(1, p));

	}

	
	//BB Test: testExitUndefinedSide()
		//tests ...
	@Test public void testExitUndefinedSide() {
		r1 = new Room();
		p = new Player();
		assertEquals("Ouch! That hurts.\n", r1.exit(0, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(1, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(2, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(3, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(4, p));
		assertEquals("Ouch! That hurts.\n", r1.exit(5, p));
	}

	
	//BB Test: testExitManyEmptyRooms()
		//tests ...
	@Test public void testExitManyEmptyRooms() {
		r1 = new Room(); r1.setDesc("R1");
		r2 = new Room(); r2.setDesc("R2");
		r3 = new Room(); r3.setDesc("R3");
		r4 = new Room(); r4.setDesc("R4");
		r5 = new Room(); r5.setDesc("R5");
		r1.setSide(0, r2);
		r2.setSide(0, r3);
		r3.setSide(0, r4);
		r4.setSide(0, r5);
		p = new Player();
		r1.enter(p);
		assertEquals("R1\n\n", p.getLoc().getDesc());
		assertEquals("", r1.exit(0, p));
		assertEquals("R2\n\n", p.getLoc().getDesc());
		assertEquals("", r2.exit(0, p));
		assertEquals("R3\n\n", p.getLoc().getDesc());
		assertEquals("", r3.exit(0, p));
		assertEquals("R4\n\n", p.getLoc().getDesc());
		assertEquals("", r4.exit(0, p));
		assertEquals("R5\n\n", p.getLoc().getDesc());
		
		
	}

	
	//BB Test: testExitFullRoomAndMoveItems()
		//tests ...
	@Test public void testExitFullRoomAndMoveItems() {
		theKey = new Key();
		theTreasure = new Treasure();
		myItem = new Item();
		
		r1 = new Room(); r1.setDesc("R1"); r1.addItem(theKey);
		r2 = new Room(); r2.setDesc("R2"); r1.addItem(theTreasure);
		r3 = new Room(); r3.setDesc("R3"); r1.addItem(myItem);
		r4 = new Room(); r4.setDesc("R4");
		r5 = new Room(); r5.setDesc("R5");

		r1.setSide(0, r2);
		r2.setSide(0, r3);
		r3.setSide(0, r4);
		r4.setSide(0, r5);
		p = new Player();
		r1.enter(p);
		
		p.pickUp(theKey);
		p.pickUp(theTreasure);
		p.pickUp(myItem);
		
		assertEquals("R1\n\n", p.getLoc().getDesc());
		assertEquals("", r1.exit(0, p));
		assertEquals("R2\n\n", p.getLoc().getDesc());
		assertEquals("", r2.exit(0, p));
		assertEquals("R3\n\n", p.getLoc().getDesc());
		assertEquals("", r3.exit(0, p));
		assertEquals("R4\n\n", p.getLoc().getDesc());
		assertEquals("", r4.exit(0, p));
		assertEquals("R5\n\n", p.getLoc().getDesc());
		
		
		assertEquals(false, r1.roomEmpty());
		assertEquals(true, r2.roomEmpty());
		assertEquals(true, r3.roomEmpty());
		assertEquals(true, r4.roomEmpty());
		assertEquals(true, r5.roomEmpty());
		
		String playerStuff = p.getItems().toString();
		
		p.drop(theKey);
		p.drop(theTreasure);
		
		assertEquals(false, r5.roomEmpty());
		
		String r5RoomStuff = r5.getRoomContents().toString();
		assertEquals(playerStuff, r5RoomStuff);
		
	}

}
