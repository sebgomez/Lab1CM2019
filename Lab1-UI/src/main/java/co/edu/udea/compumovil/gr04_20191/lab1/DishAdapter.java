package co.edu.udea.compumovil.gr04_20191.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DishAdapter extends ArrayAdapter<Dish> {
    public DishAdapter(Activity context, ArrayList<Dish> dishes) {
        super(context, 0, dishes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Dish currentDish = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.name_text_view);
        nameTextView.setText(currentDish.getName());

        TextView typeTextView = listItemView.findViewById(R.id.type_text_view);
        typeTextView.setText(getContext().getString(R.string.type_message, currentDish.getType()));

        TextView timeTextView = listItemView.findViewById(R.id.time_text_view);
        ArrayList<String> servTime = currentDish.getTime();

        for (int i = servTime.size(); i < 3; i++) {
            servTime.add("");
        }
        timeTextView.setText(getContext().getString(R.string.time_message,
                servTime.get(0),
                servTime.get(1),
                servTime.get(2)));

        TextView prepTimeTextView = listItemView.findViewById(R.id.prep_time_text_view);
        prepTimeTextView.setText(getContext().getString(R.string.prep_time_message, currentDish.getPrepTime()));

        TextView priceTextView = listItemView.findViewById(R.id.price_text_view);
        priceTextView.setText(getContext().getString(R.string.price_message, currentDish.getPrice()));

        TextView ingredientsTextView = listItemView.findViewById(R.id.ingredients_text_view);
        ingredientsTextView.setText(getContext().getString(R.string.ingredients_message, currentDish.getIngredients()));

        return listItemView;
    }


}
