package edu.utep.cs.cs4330.mypricewatcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ItemListManager itemListManager;
    private ItemAdapter itemAdapter;
    private ListView itemListView;
    private ItemList list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Item item = new Item("TestItem", "www.google.com", "Amazon");
        list = new ItemList("TestList");
        list.addItem(item);
        list.addItem(item);
        list.addItem(item);
        itemListManager = new ItemListManager();
        itemListManager.addList(list);
        itemListManager.setCurrentList(list);
        //itemListView = findViewById(R.id.itemListView);

        //listNameTextView.setText(list.getName());

        //itemAdapter = new ItemAdapter(this, R.layout.item, ItemListManager.getCurrentList().getItems());
        //itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //        Toast.makeText(getApplication(), "An item of the ListView is clicked.", Toast.LENGTH_LONG).show();
        //    }
        //});
    }

    public ItemList getList(){return ItemListManager.getCurrentList();}
    public void setList(ItemList list){
        this.list = list;
    }
    public void addToList(Item item){
        list.addItem(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            finish();
            System.exit(0);
            return true;
        }
        if (id == R.id.action_refresh) {
            return true;
        }
        if (id == R.id.action_new_list) {
            list = new ItemList("TestList2");
            itemListManager = new ItemListManager();
            itemListManager.addList(list);
            itemListManager.setCurrentList(list);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void urlOnClick(View view) {
        Toast.makeText(this, "An item of the ListView is clicked.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?tbm=isch&q=El Paso"));
        startActivity(intent);
    }
}