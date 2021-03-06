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

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DishesActivity extends AppCompatActivity {

    ArrayList<Dish> dishes = new ArrayList<>();
    public static final int PICK_IMAGE = 1;
    Uri imageUri;
    String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishes_list);

        NumberPicker np = findViewById(R.id.prep_time_picker);
        np.setMinValue(5);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(true);



        loadData();
        /*for(int cont = 0; cont<=dishes.size(); cont++){
            showDishes(dishes.get(cont));
        }*/
        if (dishes.size() > 0) {

            showDishes(dishes.get(0));

        }
        //showDishes(dishes.get(1));

        Button dishImg = findViewById(R.id.dishImg);
        dishImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "select picture"), PICK_IMAGE);

            }
        });

        Button addDish = findViewById(R.id.add_dish);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dish newDish = createDish();
                System.out.println(imagen);
                newDish.setImagen(imagen);
                System.out.println(newDish.getImagen());
                dishes.add(newDish);
                saveDishes();
                cleanFields();
                showDishes(newDish);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {
                Bitmap imagen2 = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imagen2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                imagen = Base64.encodeToString(b, Base64.DEFAULT);
                Log.d("Image Log:", imagen);
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }

    private void saveDishes() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dishes);
        editor.putString("list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<Dish>>() {
        }.getType();
        dishes = gson.fromJson(json, type);
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
    }

    private Dish createDish() {
        EditText nameEdit = findViewById(R.id.dish_name_edit_text);
        String name = nameEdit.getText().toString();

        ArrayList<String> times = new ArrayList<>();
        CheckBox checkBox1 = findViewById(R.id.morning_checkbox);
        CheckBox checkBox2 = findViewById(R.id.afternoon_checkbox);
        CheckBox checkBox3 = findViewById(R.id.night_checkbox);

        if (checkBox1.isChecked()) {
            times.add(getString(R.string.morning));
        }
        if (checkBox2.isChecked()) {
            times.add(getString(R.string.afternoon));
        }
        if (checkBox3.isChecked()) {
            times.add(getString(R.string.night));
        }

        RadioGroup radioGroup = findViewById(R.id.type_group);
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String type = "";
        if (selectedRadioButton != null) type= selectedRadioButton.getText().toString();

        EditText priceEdit = findViewById(R.id.price_edit_text);
        String temp=priceEdit.getText().toString();
        int price = 0;
        if (!temp.equals("")) price = Integer.parseInt(temp);

        EditText ingredientsEdit = findViewById(R.id.ingredients_edit_text);
        String ingredients = ingredientsEdit.getText().toString();

        NumberPicker prepTimePicker = findViewById(R.id.prep_time_picker);
        int prep_time = prepTimePicker.getValue();

        return (new Dish(imagen, name, times, type, prep_time, price, ingredients));
    }

    private void showDishes(Dish dish) {

        DishAdapter adapter;
        ListView listView = findViewById(R.id.rootViewDishes);
        adapter = (DishAdapter) listView.getAdapter();
        if (adapter == null) {
            adapter = new DishAdapter(DishesActivity.this, dishes);
        } else {
            adapter.add(dish);
        }
        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        cleanFields();
    }

    private void cleanFields() {
        EditText nameEdit = findViewById(R.id.dish_name_edit_text);
        nameEdit.setText("");

        CheckBox checkBox1 = findViewById(R.id.morning_checkbox);
        CheckBox checkBox2 = findViewById(R.id.afternoon_checkbox);
        CheckBox checkBox3 = findViewById(R.id.night_checkbox);
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.type_group);
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        if (selectedRadioButton != null) selectedRadioButton.setChecked(false);

        EditText priceEdit = findViewById(R.id.price_edit_text);
        priceEdit.setText("");

        EditText ingredientsEdit = findViewById(R.id.ingredients_edit_text);
        ingredientsEdit.setText("");

        NumberPicker prepTimePicker = findViewById(R.id.prep_time_picker);
        prepTimePicker.setValue(5);
    }

    public void close(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
