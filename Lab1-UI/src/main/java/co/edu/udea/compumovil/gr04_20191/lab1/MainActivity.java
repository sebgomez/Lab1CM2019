package co.edu.udea.compumovil.gr04_20191.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dishes = findViewById(R.id.dishes);
        dishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DishesIntent = new Intent(MainActivity.this, DishesActivity.class);
                startActivity(DishesIntent);
            }
        });

        TextView test = findViewById(R.id.intent_text);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vogella.com/"));
                startActivity(intent);
            }
        });

        /*TextView drinks = findViewById(R.id.drinks);
        drinks.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                Intent drinksIntent = new Intent(MainActivity.this, DrinksActivity.class);
                startActivity(drinksIntent);
            }
        });*/


    }
}

