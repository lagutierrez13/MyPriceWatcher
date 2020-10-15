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
        newItemName = (EditText) view.findViewById(R.id.itemName);
        newItemSource = (EditText) view.findViewById(R.id.itemSource);
        newItemURL = (EditText) view.findViewById(R.id.itemURL);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final String name = newItemName.getText().toString();

        final String source = newItemSource.getText().toString();

        final String url = newItemURL.getText().toString();


        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addToList(name, url, source);
                Toast.makeText(getActivity(),"Added To List"+name +url +source, Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(AddItemFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    public void addToList(String name, String url, String source){
        Item item = new Item(name, url, source);
        ItemList list = ((MainActivity) requireActivity()).getList();
        list.addItem(item);
        ((MainActivity) requireActivity()).setList(list);
        itemAdapter.notifyDataSetChanged();

    }
}