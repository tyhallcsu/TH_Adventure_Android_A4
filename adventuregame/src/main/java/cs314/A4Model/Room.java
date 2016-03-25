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

     Update August 2010: refactored Vector contents into ArrayList<Item> contents.
      This gets rid of the use of obsolete Vector and makes it type safe.
     Updated Feb 2016 
     - Removed unused imports (TH)

 **/

//imports

import java.util.ArrayList;
import java.util.ListIterator;

/** class Room: Implementation of CaveSite, forms the basis for all in game locations
  - Can have 6 "sides" which are represented by the cardinal directions + 'up' and 'down'
  - Sides can be walls, doors, or even other rooms
  - Rooms can store a description which is printed to the console when entered (These are set up in the Adventure class)
  - Rooms can contain items, either placed at game start or by the player removing an item from their inventory
 */

// class Room
public class Room implements CaveSite {

	private String description;                 //holds description of the room

	private CaveSite[] side = new CaveSite[6];        //allocate new CaveSite

	private ArrayList<Item> contents = new ArrayList<Item>(); //allocate room contents

	public Room() {                          //define the room walls
		side[0] = new Wall();
		side[1] = new Wall();
		side[2] = new Wall();
		side[3] = new Wall();
		side[4] = new Wall();
		side[5] = new Wall();
	}

	public void setDesc(String d){              //set room description
		description = d;
	}
	
//	public String setDescTESTER(String d){
//		setDesc(d);
//		return d;
//	}

	public String setSide(int direction, CaveSite m){       //set room side
		if (this.equals(m)){
			return "Cannot set room side equal to this.room.";
		}
		if(direction < 0){
			return "Invalid direction!";
		}
		if(direction > 5){
			return "Invalid direction!";
		}
		else{
		side[direction] = m;
		return "Side set.";
		}

	}
	
//	public String setSideHELPER(int direction, CaveSite m){
//		if (this.equals(m)){
//			return "Cannot set room side equal to this.room.";
//		}
//		if(direction < 0){
//			return "Invalid direction!";
//		}
//		if(direction > 5){
//			return "Invalid direction!";
//		}
//		else{
//			setSide(direction,m);
//			return "Side set.";
//			
//		}
//	}

	public String addItem(Item theItem){            //add item (contents) to the room
		ListIterator<Item> roomContents = contents.listIterator();
		boolean alreadyExists = false;
		while(roomContents.hasNext()){
			if (theItem.equals(roomContents.next())){
				alreadyExists = true;
				
			}
			if (alreadyExists){
				//System.out.println("Already in room.");
				return "Already in room.";
			}
		}
		
		contents.add(theItem);
		//System.out.println(theItem.toString());
		return "Item: added to room.";
	}

	public void removeItem(Item theItem){           //remove item (contents) from the room
		contents.remove(theItem);
	}
	
//	public void removeItemHELPER(Item theItem){
//		removeItem(theItem);
//	}

	public boolean roomEmpty(){                 //check if the room is empty of items (contents)
		return contents.isEmpty();
	}
	
//	public boolean roomEmptyHELPER(){
//		return roomEmpty();
//	}

	public ArrayList<Item> getRoomContents(){              //get the contents of the room and allocate array with room items (contents)
		return contents;
	}


	public String enter(Player p) {               //set player location to entered room
		p.setLoc(this);
		return "";//we did not hit a wall, so no need to return anything, but wall might
	}

	public String exit(int direction, Player p){        //set player location to exit side of room
		if (direction < 0){
			return "Invalid direction!";
		}
		if (direction > 5){
			return "Invalid direction!";
		}
		return side[direction].enter(p);
	}
	
//	public String exitHELPER(int direction, Player p){
//		return exit(direction, p);
//	}

	public String getDesc(){//get description of room and room contents, then print them
		ListIterator<Item> roomContents = contents.listIterator(); 
		String contentString = "";
		while(roomContents.hasNext()){
			contentString = 
			contentString + (roomContents.next()).getDesc() + " ";
		}

		return description + '\n' + '\n';
	}

}

