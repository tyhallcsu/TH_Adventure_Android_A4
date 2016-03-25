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


// class Item
public class Item {

  private String description;     //Item description

  public void setDesc(String d){  //set Item description
      description = d;
  }

  public String getDesc(){      //get Item description
       return description;
  }

}
