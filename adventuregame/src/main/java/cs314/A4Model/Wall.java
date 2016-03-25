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
				    
**/


// class Wall
public class Wall implements CaveSite{

 public String enter(Player p){
	 return "Ouch! That hurts.\n";
// 	System.out.println("Ouch! That hurts."); //player hits a wall, print message.
 }

}

