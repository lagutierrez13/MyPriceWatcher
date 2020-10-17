package edu.utep.cs.cs4330.mypricewatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class AddItemFragment extends Fragment {
    ItemDatabaseHelper dbHelper;
    ItemAdapter itemAdapter;
    EditText newItemName;
    EditText newItemSource;
    EditText newItemURL;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        dbHelper = new ItemDatabaseHelper(this.getContext());
        newItemName = view.findViewById(R.id.itemName);
        newItemSource = view.findViewById(R.id.itemSource);
        newItemURL = view.findViewById(R.id.itemURL);

        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item itemToAdd = new Item(newItemName.getText().toString(), newItemURL.getText().toString(), newItemSource.getText().toString());
                addToList(itemToAdd);
                Toast.makeText(getActivity(),"Added To List "+ newItemName.getText().toString(), Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(AddItemFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    public void addToList(Item item){
        ItemList list = ((MainActivity) requireActivity()).getList();
        list.addItem(item);
        ((MainActivity) requireActivity()).setList(list);
        itemAdapter.notifyDataSetChanged();
    }
}