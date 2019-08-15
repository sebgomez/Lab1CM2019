package co.edu.udea.compumovil.gr04_20191.lab1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        String img = currentDrink.getImagen();

        if (img != null) {
            byte[] decodedByte = Base64.decode(img, 0);
            Bitmap drinkImg = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
            ImageView imageView = listItemView.findViewById(R.id.image_drink_imageView);
            imageView.setImageBitmap(drinkImg);
        }


        TextView nameTextView = listItemView.findViewById(R.id.drink_name_text_view);
        nameTextView.setText(currentDrink.getName());

        TextView priceTextView = listItemView.findViewById(R.id.drink_price_text_view);
        priceTextView.setText(getContext().getString(R.string.price_message, currentDrink.getPrice()));

        TextView ingredientsTextView = listItemView.findViewById(R.id.drink_ingredients_text_view);
        ingredientsTextView.setText(getContext().getString(R.string.ingredients_message, currentDrink.getIngredients()));

        return listItemView;
    }


}
