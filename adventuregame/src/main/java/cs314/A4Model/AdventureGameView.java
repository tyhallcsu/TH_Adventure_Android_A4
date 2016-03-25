//
//
///* Team Members:
//* Colt Darien
// * Brad Wirtz
// * Tyler Hall
// */
//
//import BreezySwing.GBFrame;
//
//
//package java.cs314_A4Model;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import TurtleGraphics.StandardPen;
//
//import BreezySwing.*;
//import AdventureGameModelFacade;
//
//
//// HOTO DEPENDENCY: http://www.skholingua.com/blog/how-to-add-dependency-jar-in-android-studio
//public class AdventureGameView extends GBFrame {
//
//	//private static final long serialVersionUID = 1L;
//
//	//Create the game
//	private AdventureGameModelFacade model;
//
//
//	// Window objects --------------------------------------
//	JLabel welcomeLabel =
//			addLabel("Welcome to the Adventure Game \n" +
//					"(inspired by an old game called the Colossal Cave Adventure).\n" +
//					" Java implementation Copyright (c) 1999-2012 by James M. Bieman\n",
//					1,1,5,1);
//
//	// Hide the quit button
//	JMenuItem start_game = addMenuItem("Game","New Game");
//	JMenuItem quit_game = addMenuItem("Game","Quit");
//
//	JLabel viewLabel = addLabel ("Your View: ",2,1,1,1);
//	JTextArea viewArea = addTextArea("Start",3,1,4,3);
//
//	//JTextArea carryingArea = addTextArea("Nothing",7,1,4,3);
//
//	JLabel separator1 = addLabel
//			("---------------------------------------------------------------------------------------------"
//					, 10,1,4,1);
//
//
//	JLabel choiceLabel    = addLabel
//			("Select Start game from the \"Game\" menu to start" ,11,1,5,1);
//
////	JButton grabButton = addButton ("Grab an item", 12, 5,1,1);
////	JButton dropButton = addButton ("Drop an item", 13, 5,1,1);
//
//	JButton northButton = addButton ("North", 12,2,1,1);
//	JButton southButton = addButton ("South", 15,2,1,1);
//	JButton eastButton = addButton ("East ",   13,3,1,1);
//	JButton westButton = addButton ("West ",   13,1,1,1);
//	JButton upButton = addButton ("   Up   ", 13,2,1,1);
//	JButton downButton = addButton ("Down ", 14,2,1,1);
//
//	JLabel carryingLabel = addLabel ("You are carying nothing: ",6,1,1,1);
//	JLabel roomLabel = addLabel ("The room is empty: ",8,1,1,1);
//	JButton roomItem1 = addButton ("", 9,1,1,1);
//	JButton roomItem2 = addButton ("", 9,2,1,1);//These should be below the room label
//	JButton inventoryItem1 = addButton ("", 7,1,1,1);
//	JButton inventoryItem2 = addButton ("", 7,2,1,1);
//
//
//	// Constructor-----------------------------------------------
//
//	public AdventureGameView(){
//		setTitle ("Adventure Game");
//		model = new AdventureGameModelFacade();
//
//		viewArea.setEditable (false);
//		//carryingArea.setEditable (false);
//		//displayCurrentInfo();
//	}
//	/* Set everything as visible when the player starts the game */
//	public void menuItemSelected(JMenuItem menuItem){
//		if (menuItem == start_game) {
//			try {
//				model.startGame();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			choiceLabel.setText("Choose a direction, pick-up, or drop an item");
//			//Enable all of the buttons that the player will need
//			actionButtonsVisibility(true);
//			//Let the user know what is happengin
//			displayCurrentInfo("\n");
//		}
//		if (menuItem == quit_game) {
//			//outputArea.append(String.valueOf("Quitting..." + "\n"));
//			System.exit(0);
//		}
//	}
//
//	// buttonClicked method--------------------------------------
//
//	public void buttonClicked (JButton buttonObj){
//		// Used to tell the user what happened (not what is around them)
//		//Like hitting a wall or something
//		String environmental_info = "\n";
//		if (buttonObj == upButton)
//			environmental_info = this.model.goUp();
//
//		else if (buttonObj == downButton)
//			environmental_info = model.goDown();
//
//		else if (buttonObj == northButton)
//			environmental_info = model.goNorth();
//
//		else if (buttonObj == southButton)
//			environmental_info = model.goSouth();
//
//		else if (buttonObj == eastButton)
//			environmental_info = model.goEast();
//
//		else if (buttonObj == westButton)
//			environmental_info = model.goWest();
//
////		else if (buttonObj == grabButton)
////			environmental_info = grab();
////
////		else if (buttonObj == dropButton)
////			environmental_info = drop();
//		else if (buttonObj == inventoryItem1){
//			//Pick up the item
//			model.drop(model.getPlayerItems().get(0));
//		}
//		else if (buttonObj == inventoryItem2){
//			//pick up the item
//			model.drop(model.getPlayerItems().get(1));
//		}
//		else if (buttonObj == roomItem1){
//			model.pickUp(model.getRoomItems().get(0));
//		}
//		else if (buttonObj == roomItem2){
//			model.pickUp(model.getRoomItems().get(1));
//		}
//		//If we got aditional info, make sure we return it
//			displayCurrentInfo(environmental_info);
//		//if not, still need to let the user know what is going on around them
//	}
//
//
//	// Private methods-------------------------------------------
//	// there is aditional info so that we can keep things short
//	private void displayCurrentInfo(String aditional_information){
//		ArrayList<Item> player_items = model.getPlayerItems();
//		ArrayList<Item> room_items = model.getRoomItems();
//		viewArea.setText(aditional_information + model.getView());
//		if (player_items.size() == 2) {//we have two items
//			inventoryItem1.setText(player_items.get(0).getDesc());
//			inventoryItem1.setVisible(true);
//			inventoryItem2.setText(player_items.get(1).getDesc());
//			inventoryItem2.setVisible(true);
//			carryingLabel.setText("You are carrying:");
//		}else if(player_items.size() == 1){//we have only 1
//			inventoryItem1.setText(player_items.get(0).getDesc());
//			inventoryItem1.setVisible(true);
//			//only one item, so set this to invisible
//			inventoryItem2.setVisible(false);
//			carryingLabel.setText("You are carrying:");
//		}else{
//			//we have none
//			inventoryItem1.setVisible(false);
//			inventoryItem2.setVisible(false);//Should never be needed, but this will make extra sure it is not visible
//			carryingLabel.setText("You are carrying nothing");
//		}
//		//Same as above but for room
//		if (room_items.size() == 2) {//we have two items
//			roomItem1.setText(room_items.get(0).getDesc());
//			roomItem1.setVisible(true);
//			roomItem2.setText(room_items.get(1).getDesc());
//			roomItem2.setVisible(true);
//			roomLabel.setText("The room has:");
//		}else if(room_items.size() == 1){//we have only 1
//			roomItem1.setText(room_items.get(0).getDesc());
//			roomItem1.setVisible(true);
//			roomItem2.setVisible(false);
//			roomLabel.setText("The room has:");
//		}else{
//			//we have none
//			roomItem1.setVisible(false);
//			roomItem2.setVisible(false);
//			roomLabel.setText("The room is empty");
//		}
//	}
//
//	private void actionButtonsVisibility(boolean visible){
//		/* Make everything that the use does not need at first invisible */
//		/* Make the buttons and labels show up now */
//		northButton.setVisible(visible);
//		eastButton.setVisible(visible);
//		westButton.setVisible(visible);
//		upButton.setVisible(visible);
//		downButton.setVisible(visible);
//		southButton.setVisible(visible);
//		// action controls buttons
//		//view.actionControlsLabel.setVisible(visible);
////		grabButton.setVisible(visible);
////		dropButton.setVisible(visible);
//
//		//Labels and such should also be set to visible
//		viewArea.setVisible(visible);
//		viewLabel.setVisible(visible);
//		//carryingArea.setVisible(visible);
//		carryingLabel.setVisible(visible);
//		roomLabel.setVisible(visible);
//	}
//	public static void main (String[] args){
//		AdventureGameView view = new AdventureGameView();
//		view.setSize (800, 600); /* was 400, 250  */
//		view.actionButtonsVisibility(false);
//		//Make the item buttons invisible (player does not start off with buttons)
//		view.inventoryItem1.setVisible(false);
//		view.inventoryItem2.setVisible(false);
//		view.roomItem1.setVisible(false);
//		view.roomItem2.setVisible(false);
//		view.setVisible(true);
//	}
//}
//
//
// */