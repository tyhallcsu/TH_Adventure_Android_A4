/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.A4Model;


/*   Adventure Game  Program Code
     Copyright (c) 1999-2012 James M. Bieman
     The Adventure game is based on the "Colossal Cave Adventure" originally
     designed by Will Crowther and implemented by Will Crowther
     and Don Wood in Fortran in 1975 and 1976.

     To compile: javac -d . *.java		(compiles into new directory)
     To run:     java AdventureGame	(executes main in the compiled directory)

     The main routine is AdventureGame.main*/


//class Adventure
public class Adventure {

	public Adventure() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Room entrance;

	protected Room createAdventure(){
		// The outside: 
		Room  outside = new Room();
		outside.setDesc(
				"You are standing outside, on the edge of a cliff;\n" +
						" A creek runs alongside the cliff.\n" + 
				"a cave opens straight down (outside).");

		// Room 1:
		Room r1 = new Room();
		r1.setDesc(
				"R1: The darkness is pierced by a bright light overhead.\n"
						+ "There is a narrow, dark passage to the east (r1)." );

		// Room 2:
		Room r2 = new Room();
		r2.setDesc(
				"R2: You are in a gloomy oval shaped room with grey walls.\n" + 
						"There is a dim light to the west, and a narrow\n" +
				"dark hole to the east only about 18 inches high (r2).");

		// Room 3:
		Room r3 = new Room();
		r3.setDesc("R3: You really need your flashlight here. \n"+
				"There is a wide passage that quickly narrows\n"
				+"to the west, a bright opening to the east,\n"
				+ "and a deep hole that appears to have no bottom\n"
				+ "in the middle of the room (r3).");

		// Room 4:
		Room r4 = new Room();
		r4.setDesc("R4: There is what looks like a giant grizzly bear\n"
				+ "skull in a corner.  A passage leads to the west,\n"
				+ "another one to the north, and a slippery route\n"
				+ "goes down steeply. You can hear the shrieks of bats (r4).");

		// Room 5:
		Room r5 = new Room();
		r5.setDesc("R5: There is a dim light from above and the shrieks\n"
				+ "are clearly coming from a passageway to the west (r5).");

		// Room 6:
		Room r6 = new Room();
		r6.setDesc("R6: The ceiling is full of bats.\n"
				+ "You should put your hat on your head (r6).");

		// Create a key and put it in r6:
		Key theKey = new Key();
		theKey.setDesc("A shiny gold key.");
		r6.addItem(theKey);

		// Room 7:
		Room r7 = new Room();
		r7.setDesc("R7: This room is very damp. There are puddles on the floor\n" +
				"and a steady dripping from above (r7).");

		// Room 8:
		Room r8 = new Room();
		r8.setDesc("R8: A lizard scampers past you, or is it a snake?\n" +
				"a narrow passage runs to the east and an even narrower one\n" +
				"runs to the west (r8).");

		// Room 9:
		Room r9 = new Room();
		r9.setDesc("R9: It is pitch black.\n You are likely to be eaten by a Grue (r9).");

		// Room 10:
		Room r10 = new Room();
		r10.setDesc("R10: It looks like someone has been here.\n" +
				"There is a pile of candy wrappers on the floor,\n" +
				"and maybe something else. \n" +
				"Wait, there is a trap door on the floor,\n" +
				"but it is locked (r10).");

		// Room 11:
		Room r11 = new Room();
		r11.setDesc("R11: This room is very dark. You can just barely see (r11).");
		Treasure theTreasure = new Treasure();
		theTreasure.setDesc("A bag filled with gold bars.");
		r11.addItem(theTreasure);

		// Lets connect them:
		/* Connect Room */
		entrance = outside;//set the location to "outside"
		//Outside is above r1
		outside.setSide(5,r1);
		r1.setSide(4,outside);
		//r1 is west of r2
		r1.setSide(2,r2);
		r2.setSide(3,r1);
		//r2 is west of r3
		r2.setSide(2,r3);
		r3.setSide(3,r2);
		//r3 is west of r4
		r3.setSide(2,r4);
		r4.setSide(3,r3);
		//r3 is above r5
		r3.setSide(5,r5);
		r5.setSide(4,r3);
		//r4 is above r7
		r4.setSide(5,r7);
		r7.setSide(4,r4);
		//r4 is below r8
		r4.setSide(0,r8);
		r8.setSide(1,r4);
		//r5 is west of r6
		r5.setSide(3,r6);
		r6.setSide(2,r5);
		//r8 is east of r9
		r8.setSide(3,r9);
		r9.setSide(2,r8);
		//r8 is west of r10
		r8.setSide(2,r10);
		r10.setSide(3,r8);
		/* We add a door between r10 and r11:
    The door can only be opened by the key (found in r6)*/
		Door theDoor = new Door(r10,r11,theKey);
		//the door is on the south side of r10, connected to north side of r10
		r10.setSide(5,theDoor);
		r11.setSide(4,theDoor);

		// Now return the entrance so that the game knows where to start
		return entrance;

	}
}
