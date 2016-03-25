/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.A4Model;

import java.util.ArrayList;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac -d . *.java		(compiles into new directory)
     To run:     java AdventureGame	(executes main in the compiled directory)

     The main routine is AdventureGame.main

 **/


//class Player
public class Player {

	protected Room myLoc;             //player location

	private ArrayList<Item> myItems = new ArrayList<Item>();//Items the player is holding
	

	//private int itemCount = 0;        //number of Items the player is holding

	//protected void setRoom(Room r){        //set room
		//myLoc = r;
	//} 
	//Duplicate Method

	public String look() {          //get decription of player's current location
		return myLoc.getDesc();
	}

	public String go(int direction){      //direction for the player to go
		//Now that we are in a new area, we nood to see what is here
		return myLoc.exit(direction,this);
	}

	public boolean pickUp(Item item){         //Item for the player to pickup and remove from room
		if (!handsFull()){

			myItems.add(item);
			myLoc.removeItem(item);
			return true;
		}
		return false;
	}
	
//	public boolean pickUpHELPER(Item item){
//		return pickUp(item);
//	}

	public boolean haveItem(Item itemToFind){ //check if the player already has the item
		return myItems.contains(itemToFind);		
	}

	public boolean drop(Item item){      //drop item the player is holding return fals if they do not have that item.
		if (myItems.contains(item)){
			myItems.remove(item);
			myLoc.addItem(item);
			return true;
		}else{
			return false;
		}
	}
	public ArrayList<Item> getItems(){
		return myItems;
	}

	public void setLoc(Room r){myLoc = r;}  //set player location

	public Room getLoc(){return myLoc;}     //set room location to player current location

	public String showMyThings(){       //show the items, description, and item count player is holding.
		String outString = "";
		for (int n = 0; n < myItems.size() ; n++)
			outString = outString + Integer.toString(n+1) + ": " 
					+ myItems.get(n).getDesc() + " ";
		return outString;
	}

	private boolean handsFull(){return myItems.size()==2;}  //player has full items

//	public boolean handsEmpty(){return itemCount==0;} //player has no items

	//private int numItemsCarried(){return myItems.size();}   //get number of items player is holding

}//end class Player
