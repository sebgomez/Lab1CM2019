///*
//package co.edu.udea.compumovil.gr04_20191.lab1;
//
//import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class DishAdapter extends ArrayAdapter<Dish> {
//    public DishAdapter(Activity context, ArrayList<Dish> dishes) {
//        super(context, 0, dishes);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View listItemView = convertView;
//        if(listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.list_item, parent, false);
//        }
//        Dish currentDish = getItem(position);
//
//        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
//        defaultTextView.setText(currentDish.getDefaulTranslation());
//
//        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
//        miwokTextView.setText(currentDish.getMiwokTranslation());
//
//        // Return the whole list item layout (containing 2 TextViews and an ImageView)
//        // so that it can be shown in the ListView
//        return listItemView;
//    }
//}
//*/
