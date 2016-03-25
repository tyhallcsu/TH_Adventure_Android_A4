/* Team Members:


 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 * Player_Test.java Test Written By: Tyler Hall
 */

package cs314.A4Test;


//import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.Test;

import cs314.A4Model.Key;
import cs314.A4Model.Player;
import cs314.A4Model.Room;
import cs314.A4Model.Treasure;


/////////////////////////////////////////////////////////////////////////////////
//Tests:
//	Methods BB tested: 3
//	Methods WB tested: 4
//	
//	testLook(): 
//		
//		-tests the state of the description of the current room and ensures it is equivalent to the output of look()
//	
//	testGo(): 
//		
//		BB:
//			Input Domain: Player object's state after go() is called
//			Equivalence class 1: player is told to go() to an adjacent room
//			Equivalence class 2: player is told to go() back to original room
//			Boundary condition: Player enters wall
//		WB:
//			No branches in go()
//	
//	testPickup():
//		
//		BB:
//			Input domain: State of player's inventory after pickUp() is called
//			Equivalence class 1: player's inventory not full
//				Equivalence class 1a: player is holding no items
//				Equivalence class 1b: player has 1 Item
//			Boundary condition: Full Inventory
//		WB:	
//			2 Branches:
//				1. Player's inventory is not full (tested by equivalence class 1)
//				2. Player's inventory is full (tested by Boundary condition)
//				
//	testDrop():
//		
//		BB:
//			Input Domain: State of player's inventory after drop() is called
//			Equivalence class 1: player inventory not empty
//				Equivalence class 1a: player inventory full
//				Equivalence class 1b: player has 1 item
//			Boundary condition: Drop item not in a player's inventory	
//			Boundary condition: Player inventory empty
//		WB: 
//			2 Branches:
//				1.Player has item requested to drop (traveresed by equivalence class 1)
//				2.Player does not have item requested (traveresed by boundary conditions)
//
//	testShowMyItems():
//		
//		WB: 
//			2 Branches:
//				1. Playes inventory is empty (tested through asserts on the string testEmpty)
//				2. Player is holding at least one item (tested through asserts on the string test)
//
//Defects:
//
//	1.Player can be initialized without a location, meaning that when for example pickUp() is called, it will attempt to call a method of an uninitialized Room myLoc and crash.
//	Possible fix: add constructor that requires myLoc be initialized
//				
////////////////////////////////////////////////////////////////////////////////

public class Player_Test extends TestCase {
	
	//Player class is initialized with an empty arraylist for inventory items, and a Room object to track location

	@Test
	public void testLook() {
		
		//ensures look returns proper room description
		
		Player player = new Player();
		Room room1 = new Room();
		
		room1.setDesc("a room with very roomy qualities");
		
		player.setLoc(room1);
		
		String test = player.look();
		
		assertEquals(test, room1.getDesc());
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGo() {
		
		//Initializing objects used in testing
		
		Player player = new Player();
		Room room1 = new Room();
		Room room2 = new Room();
		room1.setSide(1, room2);
		room2.setSide(4, room1);
		player.setLoc(room1);
		
		//Black Box
		//Input Domain: Player state after go() is called
		//Equivalence class 1: player is told to go() to an adjacent room
		
		player.go(1);
		//asserts that player ends up in room 2
		assertEquals(player.getLoc(), room2);
		
		//Equivalence class 2: player i told to go() back to original room
		
		player.go(4);
		assertEquals(player.getLoc(), room1);
		
		//Boundary condition: Player enters wall
		
		player.go(2);
		assertEquals(player.getLoc(), room1);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testPickUp() {
			
		Player player = new Player();
		Room room = new Room();
		Key k1 = new Key();
		Key k2 = new Key();
		Treasure t = new Treasure();
		
		//Input domain: State of player's inventory after pickUp() is called
		//Equivalence class 1: player is holding no items
		//WB: Traverses branch of passing the conditional
		player.setLoc(room);
		player.pickUp(k1);
		assertTrue(player.haveItem(k1));
		assertEquals(player.getItems().size(), 1);
		
		//Equivalence class 2: player has 1 Item
		
		player.pickUp(t);
		assertTrue(player.haveItem(t));
		assertEquals(player.getItems().size(), 2);
		
		//Boundary condition: Full Inventory
		//WB: Traverses branch of failing the conditional
		player.pickUp(k2);
		assertFalse(player.haveItem(k2));
		assertEquals(player.getItems().size(), 2);
		
		//WB: Achieves full Branch coverage of pickUp()
		
		
	}


	@Test
	public void testDrop() {
		
		Player player = new Player();
		Room room = new Room();
		Key k = new Key();
		Treasure t = new Treasure();
		
		player.setLoc(room);
		player.pickUp(k);
		player.pickUp(t);
		
		//Input Domain: State of player's inventory after drop() is called
		//Equivalence class 1: player inventory full
		
		player.drop(k);
		assertFalse(player.haveItem(k));
		assertEquals(player.getItems().size(), 1);
		
		//Boundary case: Drop item not in a player's inventory
		
		player.drop(k);
		assertEquals(player.getItems().size(), 1);
		
		//Equivalence class 2: player has 1 item
		
		player.drop(t);
		assertFalse(player.haveItem(t));
		assertEquals(player.getItems().size(), 0);
		
		//Boundary case: Player inventory empty
		
		player.drop(t);
		assertEquals(player.getItems().size(), 0);
		
		//WB: achieves full branch coverage of drop()
	}


	@Test
	public void testShowMyThings() {
		//Tests with empty inventory and inventory with items to achieve full branch coverage
		Player player = new Player();
		Room room = new Room();
		player.setLoc(room);
		Key k = new Key();
		
		String testEmpty = player.showMyThings();

		player.pickUp(k);
		String test = player.showMyThings();
		
		assertTrue(testEmpty.length() == 0);
		assertTrue(test.length() > 0);
		
		
		//fail("Not yet implemented");
	}

}
