package edu.utep.cs.cs4330.mypricewatcher;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quit:
                finish();
                System.exit(0);
                return true;
            case R.id.action_refresh:
                //update list view with the updated items
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sortListClick(View view) {
        ItemList.sortByItemName();
        Toast.makeText(this, "List Sorted.", Toast.LENGTH_SHORT).show();
    }
}