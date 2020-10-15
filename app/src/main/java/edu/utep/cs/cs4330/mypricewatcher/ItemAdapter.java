package edu.utep.cs.cs4330.mypricewatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/*
    Authors: Luis Gutierrez and Antonio Zavala
    Class: CS4330
 */
/** Provide views for an AdapterView by returning a view
 * for each ToDoItem contained in a list. */
public class ItemAdapter extends ArrayAdapter<Item> {

    private TextView nameTextView;
    private TextView priceTextView;
    private TextView changeTextView;
    private TextView addedTextView;
    private ImageButton launchUrlButton;
    private TextView productText;

    public ItemAdapter(Context context, int resourceId, List<Item> items) {
        super(context, resourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            nameTextView = convertView.findViewById(R.id.nameTextView);
            priceTextView = convertView.findViewById(R.id.priceTextView);
            changeTextView = convertView.findViewById(R.id.changeTextView);
            addedTextView = convertView.findViewById(R.id.addedTextView);
            launchUrlButton = convertView.findViewById(R.id.launchUrlButton);

            //launchUrlButton.setOnClickListener(view -> {
            //    ImageButton launchUrl = (ImageButton) view;
            //   Item item = (Item) launchUrl.getTag();
            //    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?tbm=isch&q=El Paso"));
            //    startActivity(intent);
            //});
        }

        Item current = getItem(position);
        nameTextView = convertView.findViewById(R.id.nameTextView);
        assert current != null;
        nameTextView.setText(current.getName());
        nameTextView.setTag(current);

        priceTextView = convertView.findViewById(R.id.priceTextView);
        priceTextView.setText(String.format("Current Price: %s\nOriginal Price: %s", current.getCurrentPrice(), current.getInitPrice()));
        priceTextView.setTag(current);

        changeTextView = convertView.findViewById(R.id.changeTextView);
        changeTextView.setText(current.getChange());
        changeTextView.setTag(current);

        addedTextView = convertView.findViewById(R.id.addedTextView);
        addedTextView.setText(String.format("Date added: %s", current.getDateAdded()));
        addedTextView.setTag(current);

        return convertView;
    }
}