package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/*
    Author: Luis Gutierrez
    Class: CS4330
 */
public class MainActivity extends AppCompatActivity {
    // Buttons for pop-up menu
    private ImageButton chooseListButton;
    private ImageButton filterItemsButton;
    private ImageButton sortItemsButton;
    private ListView itemListView;

    private ItemListManager itemListManager;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseListButton = findViewById(R.id.chooseListButton);
        filterItemsButton = findViewById(R.id.filterItemsButton);
        sortItemsButton = findViewById(R.id.sortItemsButton);

        Item item = new Item("TestItem", "www.google.com", "Amazon");
        ItemList list = new ItemList("TestList");
        list.addItem(item);
        list.addItem(item);
        itemListManager = new ItemListManager();
        itemListManager.addList(list);
        itemListManager.setCurrentList(list);

        itemAdapter = new ItemAdapter(this, R.layout.item, ItemListManager.getCurrentList().getItems());

        itemListView = findViewById(R.id.itemListView);

        itemListView.setAdapter(itemAdapter);
        itemListView.setOnItemClickListener((parent, view, position, id) -> {
            Item itemClicked = (Item) parent.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, itemClicked.getName(), Toast.LENGTH_SHORT).show();
        });

        // Restore state if previous state exists
        if(savedInstanceState != null){

        }else{

        }

        // App launched due to sharing create new item in current list
        String action = getIntent().getAction();
        String type = getIntent().getType();
        if(Intent.ACTION_SEND.equalsIgnoreCase(action) && ("text/plain".equals(type))){

        }

//        initializeUI();
    }



//    public void initializeUI(){
//
//    }

//    public void updatePriceUI(){
//
//    }

    //region Overrides

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            toggleActionBar();
//        }
//        return true;
//    }
//
//    private void toggleActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            if (actionBar.isShowing()) {
//                actionBar.hide();
//            } else {
//                actionBar.show();
//            }
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_refresh:
                // refresh all currentList items
                return true;
            case R.id.action_new_list:
                // create new list
                return true;
            case R.id.action_quit:
                MainActivity.this.finish();
                System.exit(0);
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    //endregion
}