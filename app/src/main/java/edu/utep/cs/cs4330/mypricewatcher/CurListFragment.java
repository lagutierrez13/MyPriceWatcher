package edu.utep.cs.cs4330.mypricewatcher;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CurListFragment extends Fragment {
    private static final int EDIT = 0, DELETE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ListView itemListView = view.findViewById(R.id.itemListView);

        itemListView.setAdapter(((MainActivity) getActivity()).getItemAdapter());

        registerForContextMenu(itemListView);

        view.findViewById(R.id.add_item).setOnClickListener(view1 -> NavHostFragment.findNavController(CurListFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        if (view.getId() == R.id.itemListView) {
            ListView lv = (ListView) view;
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Item item = (Item) lv.getItemAtPosition(adapterContextMenuInfo.position);

            menu.setHeaderTitle("Item Options");
            menu.add(0, item.id(), 0, "Delete: " + item.getName());
            menu.add(1, item.id(), 1, "Edit: " + item.getName());
        }
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case EDIT:
                // TODO: Add save edit code
                break;
            case DELETE:
                ((MainActivity) getActivity()).getDbHelper().delete(item.getItemId());
                ((MainActivity) getActivity()).getItemAdapter().remove(((MainActivity) getActivity()).getItemAdapter().getItem(info.position));
                ((MainActivity) getActivity()).getItemAdapter().notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}