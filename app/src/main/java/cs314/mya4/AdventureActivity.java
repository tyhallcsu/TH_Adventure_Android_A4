/* Team Members:
 * Colt Darien
 * Brad Wirtz
 * Tyler Hall
 */

package cs314.mya4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cs314.A4Model.AdventureGameModelFacade;
import cs314.A4Model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventureActivity extends AppCompatActivity {
  /* The game model for the game */
  private AdventureGameModelFacade model;
  /* This is the text we edit to give info to the user */
  private TextView text_Room;//= (TextView)this.findViewById(R.id.textView);

  /* This is the buttons that we will toggle visibility on */
  private ListView listView_player;// = (Button)this.findViewById(R.id.button_room_item1);
  private ListView listview_room;
  /* Listview helpers */
  private List<String> player_items_string;
  private ArrayAdapter<String> adapter_player;

  private List<String> room_items_string;
  private ArrayAdapter<String> adapter_room;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adventure);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

        /* Set up the listview */
    listView_player = (ListView) findViewById(R.id.listView_player);
    listview_room = (ListView) findViewById(R.id.listView_room);
    player_items_string = new ArrayList<String>();
    room_items_string = new ArrayList<String>();
    adapter_player = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, player_items_string);
    adapter_room = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, room_items_string);
    listView_player.setAdapter(adapter_player);
    listview_room.setAdapter(adapter_room);

        /* The buttons for items */
    listView_player.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        if(!model.drop(model.getPlayerItems().get(position))) {
          notify_player("The floor would give out");
        }
        update_view();
      }
    });
    listview_room.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        if(!model.pickUp(model.getRoomItems().get(position))) {
          notify_player("You are carrying too much");
        }
        update_view();
      }
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_adventure, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Toast toast = Toast.makeText(getApplicationContext(), "LOL NO", Toast.LENGTH_SHORT);
      toast.show();
      return true;
    }
    if (id == R.id.action_start_game) {
      Toast toast = Toast.makeText(getApplicationContext(), "Starting a new game", Toast.LENGTH_SHORT);
      toast.show();
            /* Create the model for the adventure game */
      model = new AdventureGameModelFacade();

             /* This is the buttons for inventory */
      listView_player = (ListView)this.findViewById(R.id.listView_player);
      listView_player.setEnabled(false);
      listview_room = (ListView)this.findViewById(R.id.listView_player);
      listview_room.setEnabled(false);

            /* The text view to give users info about surroundings */
      text_Room = (TextView)this.findViewById(R.id.textView);


            /* Now toggle the movement buttons to enable them */
      this.findViewById(R.id.button_north).setEnabled(true);
      this.findViewById(R.id.button_east).setEnabled(true);
      this.findViewById(R.id.button_south).setEnabled(true);
      this.findViewById(R.id.button_west).setEnabled(true);
      this.findViewById(R.id.button_up).setEnabled(true);
      this.findViewById(R.id.button_down).setEnabled(true);

            /* Start the game */
      try {
        model.startGame();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      update_view();
    }

    return super.onOptionsItemSelected(item);
  }





  /* Buttons for movement */
  public void button_up(View view) {
    String environmental_info = this.model.goUp();
    notify_player(environmental_info);
    update_view();
  }
  public void button_down(View view) {
    String environmental_info = this.model.goDown();
    notify_player(environmental_info);
    update_view();
  }
  public void button_north(View view) {
    String environmental_info = this.model.goNorth();
    notify_player(environmental_info);
    update_view();
  }
  public void button_east(View view) {
    String environmental_info = this.model.goEast();
    notify_player(environmental_info);
    update_view();
  }
  public void button_south(View view) {
    String environmental_info = this.model.goSouth();
    notify_player(environmental_info);
    update_view();
  }
  public void button_west(View view) {
    String environmental_info = this.model.goWest();
    notify_player(environmental_info);
    update_view();
  }



    /* Utility Functions */

  /* Generate toasts to convey info to user */
  private void notify_player(String info) {
    if (info.compareTo("") != 0) {
      Toast toast = Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT);
      toast.show();
    }
  }
  /* This is the method that updates the room view (text and inventory, etc) */
  private void update_view() {
        /* Make all room inventory Buttons Invisible (we went to a new room)Leave player inventory Buttons Invisible */
        /* Update items */
        /* Clear the strings list so that we can update it in loop */
    //TODO items should probably be from model facade and be const
    room_items_string.clear();
    for (Item item : model.getRoomItems()) {
      room_items_string.add(item.getDesc());
    }
    player_items_string.clear();
    for (Item item : model.getPlayerItems()) {
      player_items_string.add(item.getDesc());
    }
    adapter_player.notifyDataSetChanged();
    adapter_room.notifyDataSetChanged();
        /* Update the text */
    text_Room.setText(model.getView());
  }


}

////import android.os.Bundle;
////import android.support.design.widget.FloatingActionButton;
////import android.support.design.widget.Snackbar;
////import android.support.v7.app.AppCompatActivity;
////import android.support.v7.widget.Toolbar;
////import android.view.View;
////import android.view.Menu;
////import android.view.MenuItem;
//import android.app.Activity;
////import android.os.Bundle;
////import android.view.View;
////import android.widget.TextView;
////import android.widget.EditText;
////import android.widget.RadioButton;
////import android.widget.Toast;
////import cs314.A4Model.*;
//
//public class AdventureActivity extends Activity {
////
////    private AdventureGameModelFacade model;
////
////
////    /** Called when the activity is first created. */
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.main);
////
////        //initialize connection to model
////        model = new AdventureGameModelFacade();
////        String viewText = model.getView();
////
////        // Get initial room view, and see my items.
////        TextView myView = (TextView) findViewById(R.id.roomView);
////        myView.setText(viewText);
////
////        TextView myItems = (TextView) findViewById(R.id.myItems);
////        myItems.setText(model.getItems());
////
////
////    }
//
//  /* comment out for now */
//
// // This method is called at button click because we assigned the name to the
// 	// "On Click property" of the button
//// 	public void myClickHandler(View view) {
//// 		switch (view.getId()) {
//// 		case R.id.goUp:
//// 			//myView.setText(text);
//// 		break;
//// 		}
//// 	}
//
//
//}
//
////public class AdventureActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_adventure);
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
////    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_adventure, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
////
////        return super.onOptionsItemSelected(item);
////    }
////}
