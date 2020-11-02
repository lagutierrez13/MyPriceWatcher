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
    private ItemDatabaseHelper dbHelper;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new ItemDatabaseHelper(this);
        itemAdapter = new ItemAdapter(this, R.layout.item, dbHelper.allItems());
    }

    public ItemDatabaseHelper getDbHelper(){
        return dbHelper;
    }

    public ItemAdapter getItemAdapter() {
        return itemAdapter;
    }

    public void urlOnClick(View view) {
        Toast.makeText(this, "An item of the ListView is clicked.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?tbm=isch&q=El Paso"));
        startActivity(intent);
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

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_quit:
                finish();
                System.exit(0);
                return true;
            case R.id.action_refresh:
                //update list view with the updated items
                return true;
//          case R.id.action_new_list:
//                list = new ItemList("TestList2");
//                itemListManager.addList(list);
//                itemListManager.setCurrentList(list);
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sortListClick(View view) {
        ItemList.sortByItemName();
        Toast.makeText(this, "List Sorted.", Toast.LENGTH_SHORT).show();
    }
}