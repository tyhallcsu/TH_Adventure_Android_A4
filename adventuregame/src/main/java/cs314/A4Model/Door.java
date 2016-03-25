/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.A4Model;

/*  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac -d . *.java		(compiles into new directory)
     To run:     java AdventureGame	(executes main in the compiled directory)

     The main routine is AdventureGame.main */


// class Door
public class Door implements CaveSite {
	/** In this implementation doors are always locked.
      A player must have the correct key to get through
      a door.  Doors automatically lock after a player
      passes through. */

	private Key myKey; //this is the key to be found by the player.

	/** The door's location. */
	private CaveSite outSite; //this is the outSite of the CaveSite.
	private CaveSite inSite; //this is the inSite of the CaveSite.

	/** We can construct a door at the site. */
	public Door(CaveSite out, CaveSite in, Key k){ //door constructor
		outSite = out;  //out-Cave-Site
		inSite = in;  //in-CaveSite
		myKey = k;    //player-key
	}

	/** A player will need the correct key to enter. */
	public String enter(Player p){
		//System.out.println("p = " + p.getLoc());
		//System.out.println("IN = " + inSite.toString());
		//System.out.println("OUT = " + outSite.toString());
		String does_key_work = "";
		if (p.haveItem(myKey)) { //does the player have the correct key?
			does_key_work = "Your key works! The door creaks open\n"; //if so, key works! Yay!
			does_key_work += "and slams behind you after you pass through.";
			if (p.getLoc() == outSite) inSite.enter(p); //if player location equals out-CaveSite, enter in-CaveSite
			else if (p.getLoc() == inSite){ outSite.enter(p);} //if player location equals in-CaveSite, enter out-CaveSite
		}
		else {
			does_key_work = "You don't have the key for this door!\n";//if not, key doesn't work, entry denied
			does_key_work += "Sorry.";
		}
		return does_key_work;
	}

}
