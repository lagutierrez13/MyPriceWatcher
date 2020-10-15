package edu.utep.cs.cs4330.mypricewatcher;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CurListFragment extends Fragment {
    private static final int EDIT = 0, DELETE = 1;
   // private ItemListManager itemListManager;
    private ListView itemListView;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ItemList list = ((MainActivity)getActivity()).getList();
        //ListView itemListView = ((MainActivity)getActivity()).getListView();
        itemListView = view.findViewById(R.id.itemListView);

        itemAdapter = new ItemAdapter(getActivity(), R.layout.item, ItemListManager.getCurrentList().getItems());

        //return inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ItemList list = ((MainActivity)getActivity()).getList();
        //itemListView = view.findViewById(R.id.itemListView);
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
                break;
        }

        return super.onContextItemSelected(item);
    }

}