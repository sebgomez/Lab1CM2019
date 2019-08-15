package co.edu.udea.compumovil.gr04_20191.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DrinkAdapter extends ArrayAdapter<Drink> {
    public DrinkAdapter(Activity context, ArrayList<Drink> drinks) {
        super(context, 0, drinks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item2, parent, false);
        }
        Drink currentDrink = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.drink_name_text_view);
        nameTextView.setText(currentDrink.getName());

        TextView priceTextView = listItemView.findViewById(R.id.drink_price_text_view);
        priceTextView.setText(getContext().getString(R.string.price_message, currentDrink.getPrice()));

        TextView ingredientsTextView = listItemView.findViewById(R.id.drink_ingredients_text_view);
        ingredientsTextView.setText(getContext().getString(R.string.ingredients_message, currentDrink.getIngredients()));

        return listItemView;
    }


}
