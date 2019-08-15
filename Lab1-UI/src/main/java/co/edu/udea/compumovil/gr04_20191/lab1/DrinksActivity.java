/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.edu.udea.compumovil.gr04_20191.lab1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DrinksActivity extends AppCompatActivity {

    ArrayList<Drink> drinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinks_list);

        loadData();
        /*for(int cont = 0; cont<=drinks.size(); cont++){
            showDrinks(drinks.get(cont));
        }*/
        if (drinks.size() > 0) {
            showDrinks(drinks.get(0));
        }
        //showDrinks(drinks.get(1));

        Button addDrink = findViewById(R.id.add_drink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drink newDrink = createDrink();
                drinks.add(newDrink);
                saveDrinks();
                cleanFields();
                showDrinks(newDrink);
            }
        });
    }

    private void saveDrinks() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences drinks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(drinks);
        editor.putString("listdrinks", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences drinks", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("listdrinks", null);
        Type type = new TypeToken<ArrayList<Drink>>() {
        }.getType();
        drinks = gson.fromJson(json, type);
        if (drinks == null) {
            drinks = new ArrayList<>();
        }
    }

    private Drink createDrink() {
        EditText nameEdit = findViewById(R.id.drink_name_edit_text);
        String name = nameEdit.getText().toString();

        EditText priceEdit = findViewById(R.id.price_edit_text);
        int price = Integer.parseInt(priceEdit.getText().toString());

        EditText ingredientsEdit = findViewById(R.id.ingredients_edit_text);
        String ingredients = ingredientsEdit.getText().toString();

        return (new Drink(name, price, ingredients));
    }

    private void showDrinks(Drink drink) {
        DrinkAdapter adapter;
        ListView listView = findViewById(R.id.rootViewDrinks);
        adapter = (DrinkAdapter) listView.getAdapter();
        if (adapter == null) {
            adapter = new DrinkAdapter(DrinksActivity.this, drinks);
        } else {
            adapter.add(drink);
        }
        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        cleanFields();
    }

    private void cleanFields() {
        EditText nameEdit = findViewById(R.id.drink_name_edit_text);
        nameEdit.setText("");

        EditText priceEdit = findViewById(R.id.price_edit_text);
        priceEdit.setText("");

        EditText ingredientsEdit = findViewById(R.id.ingredients_edit_text);
        ingredientsEdit.setText("");
    }

    public void close(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
