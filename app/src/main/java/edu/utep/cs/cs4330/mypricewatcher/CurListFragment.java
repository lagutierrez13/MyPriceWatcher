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
   // private ItemListManager itemListManager;
    private ListView itemListView;
    private ItemAdapter itemAdapter;
    TextView listNameTextView;
    ItemDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ItemList list = ((MainActivity)getActivity()).getList();
        Item item = new Item("ATestItem", "www.google.com", "Amazon");
        list.addItem(item);
        Item item2 = new Item("XTestItem", "www.google.com", "Amazon");
        list.addItem(item2);
        //ListView itemListView = ((MainActivity)getActivity()).getListView();
        itemListView = view.findViewById(R.id.itemListView);
        dbHelper = new ItemDatabaseHelper(this.getContext());
        itemAdapter = new ItemAdapter(getActivity(), R.layout.item, dbHelper.allItems());

        //listNameTextView.setText(list.getName());
        //return inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ItemList list = ((MainActivity)getActivity()).getList();
        //itemListView = view.findViewById(R.id.itemListView);
        //listNameTextView = listNameTextView.findViewById(R.id.listNameTextView);
        //listNameTextView.setText(list.getName());

        itemListView.setAdapter(itemAdapter);

        registerForContextMenu(itemListView);

        view.findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CurListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.setHeaderTitle("Save Options");
        menu.add(Menu.NONE, EDIT, menu.NONE, "Edit Save");
        menu.add(Menu.NONE, DELETE, menu.NONE, "Delete Save");
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