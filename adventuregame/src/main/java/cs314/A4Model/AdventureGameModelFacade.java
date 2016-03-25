/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.A4Model;

import java.io.IOException;
import java.util.ArrayList;

public class AdventureGameModelFacade {
	
	//private AdventureGameModelFacade game;
	//Create the cave for the player to adventure in
	public Adventure theCave;
	//Create the Player
	public Player thePlayer;
	
	// some private fields to reference current location,
	// its description, what I'm carrying, etc.
	//
	// These methods and fields are left as exercises.
	public AdventureGameModelFacade() { // we initialize
		//this.game = new AdventureGame();
//		this.theCave = new Adventure();
//		this.thePlayer = new Player();
	}
	
	
	/* "this" after here is not needed, but is for clarity */
	public void startGame() throws IOException{
		//Create the player
		this.thePlayer = new Player();
		//Create the adventure (cave in this case)
		this.theCave = new Adventure();
		//Set the starting of the adventure to start room of cave
		Room startRm = theCave.createAdventure();
		//Make sure that the player is in startroom
		this.thePlayer.setLoc(startRm);
	}
	
	//Directions are integer values as follows N:0 S:1 E:2 W:3 U:4 D:5
	public String goUp(){
		return this.thePlayer.go(4);
	}

	public String goDown(){
		return thePlayer.go(5);
	}

	public String goNorth(){
		return thePlayer.go(0);
	}

	public String goSouth(){
		return thePlayer.go(1);
	}

	public String goEast(){
		return thePlayer.go(2);
	}

	public String goWest(){
		return thePlayer.go(3);
	}

	// You need to finish these getView and getItems methods.
	public String getView(){
		//get the players loc (room) and return the rooms description
		return thePlayer.getLoc().getDesc();
	}

	public String getItems(){
		//get the items and return them
		return thePlayer.getItems().toString();
	}

	public ArrayList<Item> getPlayerItems(){
		return thePlayer.getItems();
	}
	public ArrayList<Item> getRoomItems(){
		return thePlayer.myLoc.getRoomContents();
	}
	// Surely you will need other methods to deal with
	// picking up and dropping things.
	public boolean drop(Item item){
		return thePlayer.drop(item);
	}
	public boolean pickUp(Item item){
		return thePlayer.pickUp(item);
	}
}
