package edu.utep.cs.cs4330.mypricewatcher;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CurListFragment extends Fragment {
    private static final int EDIT = 0, DELETE = 1;
    private ListView itemListView;
    private ItemAdapter itemAdapter;
    ItemDatabaseHelper dbHelper;
    TextView listNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        itemListView = view.findViewById(R.id.itemListView);
        dbHelper = new ItemDatabaseHelper(this.getContext());
        itemAdapter = new ItemAdapter(getActivity(), R.layout.item, dbHelper.allItems());
        itemListView.setAdapter(itemAdapter);
        registerForContextMenu(itemListView);
        view.findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CurListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        return view;
    }


//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        itemListView.setAdapter(itemAdapter);
//        registerForContextMenu(itemListView);
//        view.findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CurListFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        if(view.getId() == R.id.itemListView){
            ListView lv = (ListView) view;
            AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Item item = (Item) lv.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
            menu.add(Menu.NONE, EDIT, menu.NONE, "Edit");
            menu.add(Menu.NONE, DELETE, menu.NONE, "Delete");
        }
//        super.onCreateContextMenu(menu, view, menuInfo);
//        menu.setHeaderTitle("Item Options");

    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case EDIT:
                // TODO: Add save edit code
                break;
            case DELETE:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                dbHelper.delete(item.getItemId());
                itemAdapter.notifyDataSetChanged();
                itemAdapter.remove(itemAdapter.getItem(info.position));
                break;
        }

        return super.onContextItemSelected(item);
    }

}