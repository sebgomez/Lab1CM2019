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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DishesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishes_list);

        NumberPicker np = findViewById(R.id.prep_time_picker);
        np.setMinValue(5);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(true);

        Button addDish = findViewById(R.id.add_dish);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout rootView = findViewById(R.id.rootViewDishes);
                LinearLayout childView = findViewById(R.id.childViewDishes);
                TextView newTextView = new TextView(DishesActivity.this);
                String temp = getString(R.string.time);

                EditText dishName = findViewById(R.id.dish_name_edit_text);
                newTextView.setText(dishName.getText());
                childView.addView(newTextView);

                CheckBox checkBox1 = findViewById(R.id.morning_checkbox);
                CheckBox checkBox2 = findViewById(R.id.afternoon_checkbox);
                CheckBox checkBox3 = findViewById(R.id.night_checkbox);
                if (checkBox1.isChecked()) {
                    temp = temp + ":" + getString(R.string.morning);
                }
                if (checkBox2.isChecked()) {
                    temp = temp + ":" + getString(R.string.afternoon);
                }
                if (checkBox1.isChecked()) {
                    temp = temp + ":" + getString(R.string.night);
                }
                newTextView.setText(temp);
                childView.addView(newTextView);

                RadioGroup radioGroup = findViewById(R.id.type_group);
                RadioButton selectedRadioButton  = findViewById(radioGroup.getCheckedRadioButtonId());
                newTextView.setText(getString(R.string.dish_type)+": "+selectedRadioButton.getText());

                rootView.addView(childView);

            }
        });

        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new

                Dish("one", "lutti"));
        /*words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");*/

        //DishAdapter adapter = new DishAdapter(this, dishes);
        LinearLayout rootView = findViewById(R.id.rootViewDishes);
        for (
                int i = 0; i < dishes.size(); i++) {
            TextView dishView = new TextView(this);
            dishView.setText(dishes.get(i).getDefaulTranslation());
            rootView.addView(dishView);
        }

    }
}
