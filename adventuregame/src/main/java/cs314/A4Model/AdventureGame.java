/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.A4Model;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac -d . *.java		(compiles into new directory)
     To run:     java AdventureGame	(executes main in the compiled directory)
     The main routine is AdventureGame.main
     The AdventureGame is a Java implementation of the old text based
     adventure game from long ago.  The design was adapted from
     one in Gamma, Helm, Johnson, Vlissides (The Gang of Four),
     "Design Patterns: Elements of Reusable Object-Oriented Software",
     Addison-Wesley, 1997.

     To really be consistent with the old game we would need a
     much larger cave system with a hundred or so rooms, and a 
     more "understanding" user interface.

     The old game just put you near the cave, displayed the "view"
     as text, and offered no instructions.  If you gave a command that
     it understood, you could proceed.  If your command could not
     be interpreted, nothing would happen.  Rooms were never identified
     precisely; your only clues came from the descriptions.  You would
     have to remember or create your own map of the cave system to 
     find your way around.  Sometimes you could not return exactly
     the way you came.  An exit to the east may not enter the west
     side of the "adjacent room"; the passage might curve.

     Perhaps, this implementation can evolve to be closer to
     the original game, or even go beyond it. 

     Jim Bieman
     September 1999.

     Updated Feb 2016 
      - Changed compile and run requirements to match cs314_A4Model package (BW)
      - Changed theCave and thePlayer to public to solve warnings (TH)
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/** class AdventureGame: 
    - main() method starts adventure by calling startQuest()
    - startQuest() creates a new cave and player object
    - startQuest() deals with i/o, outputting instructions to the console for the player and responding to player input
    - contains methods for picking up and dropping items from the players inventory (choosePickupItem() and chooseDropItem()) 
 */

//class AdventureGame

public class AdventureGame{

	private AdventureGameModelFacade model;

	/* Make Buttons for gameplay invisible until tImport junit.framework.*he game starts */




	/** Our system-wide internal representation of directions
      is integers.  Here, we convert input string directions
      into integers.  Internally, we use integers 0-9 as
      directions throughout the program.  This is a bit of
      a cludge, but is simpler for now than creating a Direction
      class.  I use this cludge because Java in 1999 did not have
      an enumerated data type.  */
	//get information for the direction the player wants to go
	//Let them input it in plain text, then convert it to a number 
	private void goDirection(String input){
		char d = input.charAt(0);//TODO this is really bad
		switch(d){
		case 'n': case 'N': System.out.println(model.goNorth());break;
		case 's': case 'S': System.out.println(model.goSouth());break;
		case 'e': case 'E': System.out.println(model.goEast());break;
		case 'w': case 'W': System.out.println(model.goWest());break;
		case 'u': case 'U': System.out.println(model.goUp());break;
		case 'd': case 'D': System.out.println(model.goDown());break;

		// The default case should let users know they made a mistake. This option should never happen (due to other error checks, but it is better to be safe)
		default: System.out.println("Error: Incorrect input");  break;
		}
	}


	/** choosePickupItem determines the specific item
      that a player wants to pick up.   */
	private Item chooseItem(BufferedReader keyB, ArrayList<Item> items)
			throws IOException{
		//Set all of the variables that will be used later
		//Prepare is unnecessary but leaves code more readable (blank would be fine instead if the contents becomes a problem ever)
		String inputString = "prepare";
		//set the item number to -1 
		int theChoice = -1;
		//Loop until the player chooses an existing item, TODO or decides to exit this option
		do {
			//Print the items in the room
			System.out.println("");//add some space
			System.out.println("Enter q to exit\nThese items are available:"); 
			for (int i = 0; i < items.size() ; i++){
				System.out.println((i+1) + ": " + items.get(i).getDesc());
			}
			System.out.println("");//add some space
			//Prompt the user decide which item
			//TODO use names instead of numbers?
			System.out.print("Enter the number of the item: ");
			inputString = keyB.readLine();
			System.out.println('\n'); 
			if (inputString.charAt(0) == 'Q' || inputString.charAt(0) == 'q'){//User changed their mind (probably because nothing is available
				return null;
			}
			//if the input is blank, avoid a crash by filling it with a whitespace
			if (inputString.equals("")) inputString = " ";{
				try  {
					theChoice = Integer.parseInt(inputString);
				} catch (NumberFormatException e) {//if the input was not a number, do not crash, just warn user
					System.out.println("Invalid input."); 
				}
			}
			//This should really not pass all the time, I did this on purpose so that we can see what a non passing one is like
			
			//If the input is an invalid number, ask again
			if (theChoice <= 0 || theChoice > items.size())
				System.out.print("That item is not available.\n");
		} while (theChoice <= 0 || theChoice > items.size()); 
		return items.get(theChoice - 1);
	}

	protected void startQuest() throws IOException{ //allocate new instance of Player, Adventure, and define start room
		System.out.println("Welcome to the Adventure Game,\n"
				+ "which is inspired by an old game called the Colossal Cave Adventure.\n"
				+ "Java implementation Copyright (c) 1999 - 2012 by James M. Bieman\n" + "\n\n\n---\n\n\n");
		//Start the game (create player and adventure, etc
		this.model = new AdventureGameModelFacade();
		this.model.startGame();
		handleGameEvents();
	}//end startQuest


	private void handleGameEvents() throws IOException{
		/** Create the keyboard to control the game; we only need one */
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String inputString = " ";

		//		/* The main query user, get command, interpret, execute cycle. */ 
		//loop until the game exits
		while (inputString.charAt(0)!='q') {
			//output the environment so that the player knows their person and surroundings
			System.out.println(model.getView());

			//output the information about the players items
			System.out.println("You are carrying: ");
			for (int i = 0; i < model.getPlayerItems().size(); ++i)
				System.out.println(model.getPlayerItems().get(i).getDesc() + '\n');
			
			System.out.println("");//add some space
			//Let user know what is in the room
			System.out.println("The room has: ");
			for (int i = 0; i < model.getRoomItems().size(); ++i)
				System.out.println(model.getRoomItems().get(i).getDesc() + '\n');
			
			System.out.println("");//add some space

			//querey the player for which way they want to go
			System.out.println("Which way (n,s,e,w,u,d)," + " or grab (g) or toss (t) an item," + " or quit (q)?" + '\n');

			//wait(200);
			inputString = keyboard.readLine(); 
			System.out.println('\n');

			//avoid a crash if input is blank
			if (inputString.equals("")) inputString = " ";

			//grab the first char (so that they can enter "north", etc if they wish
			char key = inputString.charAt(0);

			//find out what the user input
			switch (key){
			// if they chose any direction, find out which one TODO better method of this
			case 'n': case 'N': case 's': case 'S':
			case 'e': case 'E': case 'w': case 'W':
			case 'u': case 'U': case 'd': case 'D': 
				goDirection(inputString); 
				break;

				// if they chose to grab an item, grab it
			case 'g': case 'G':
				Item itemToGrab = chooseItem(keyboard, model.getRoomItems());
				if (itemToGrab != null)//grab the item
					if (!model.pickUp(itemToGrab))//if this failed, there was nothing to grab, or 
						System.out.println("Your hands are full!");
				break;
				
				// did they want to drop an Item
			case 't': case 'T':
				Item itemToDrop = chooseItem(keyboard, model.getPlayerItems());
				if (itemToDrop != null)//grab the item if they did not quit
					if (!model.drop(itemToDrop))//if this failed, there was nothing to grab, or 
						System.out.println("There would be no room to walk if you placed that here!");
				break;
				//Default case (this should take care of all input, including no need for default in convertDirection)
			case 'q': case 'Q':
				break;// this case will just exit the loop
			default: 
				System.out.println("Invalid input.\n");
				break;
			} //end switch()
		}//end while() 
	}


	public static void main(String args[])
			throws IOException{
		AdventureGame theGame = new AdventureGame();
		theGame.startQuest();

		//theGame.setDefaultLookAndFeelDecorated(true);
		//AdventureGame theGame = new AdventureGame();


	}
}

