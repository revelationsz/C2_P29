package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<Integer, Integer> protein = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> base = new HashMap<>();
    private HashMap<Integer, Integer> vegetable = new HashMap<>();
    Integer totalCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCalories = 0;
        TextView calCount = findViewById(R.id.calorieCounter);


        Spinner proteinSelection = (Spinner) findViewById(R.id.protein);
        Spinner baseSelection = (Spinner) findViewById(R.id.bases);
        Spinner vegetableSelection = (Spinner) findViewById(R.id.vegetables);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.protein, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinSelection.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterB = ArrayAdapter.createFromResource(this,
                R.array.bases, android.R.layout.simple_spinner_item);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baseSelection.setAdapter(adapterB);

        ArrayAdapter<CharSequence> adapterV = ArrayAdapter.createFromResource(this,
                R.array.vegtables, android.R.layout.simple_spinner_item);
        adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vegetableSelection.setAdapter(adapterV);

        protein.put(0, 0);
        protein.put(1, 678);
        protein.put(2, 189);
        protein.put(3,412);
        protein.put(4, 206);
        protein.put(5, 391);
        protein.put(6, 365);

        base.put(0, 0);
        base.put(1, 131);
        base.put(2, 209);
        base.put(3, 222);
        base.put(4, 140);
        base.put(5, 206);

        vegetable.put(0, 0);
        vegetable.put(1, 43);
        vegetable.put(2, 27);
        vegetable.put(3, 44);
        vegetable.put(4, 6 );
        vegetable.put(5, 8);
        vegetable.put(6,31);

        proteinSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateCals1(i, "proteinServings", protein);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        baseSelection.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateCals1(i, "basesServings", base);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        }));

        vegetableSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateCals1(i, "vegetableServings", vegetable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        EditText servingsPro = findViewById(R.id.proteinServings);

        servingsPro.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                updateCals2(textView, proteinSelection);
                return true;
            }
        });

        EditText servingsBas = findViewById(R.id.basesServings);
        servingsBas.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                updateCals2(textView, baseSelection);
                return false;
            }
        });

        EditText servingVeg = findViewById(R.id.vegetableServings);
        servingVeg.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                updateCals2(textView, vegetableSelection);
                return false;
            }
        });

    }


    public void updateCals1(int i, String type, HashMap<Integer, Integer> foodType){
        TextView calCount = findViewById(R.id.calorieCounter);
        EditText servings = (EditText) findViewById(getResources().getIdentifier(type, "id", getPackageName()));
        String serving = servings.getText().toString();
        if(serving == "") serving = "0";
        System.out.println(serving);
        if(serving != ""){
            totalCalories += Integer.parseInt(serving) * foodType.get(i);
            calCount.setText(totalCalories.toString());
        }
    }

    public void updateCals2(TextView textView, Spinner selectedFood){
        int selection = selectedFood.getSelectedItemPosition();
        String serving = textView.getText().toString();
        TextView calCount = findViewById(R.id.calorieCounter);
        System.out.println(selection + " " + serving);
        if(selection != 0 && serving != "" ){
            totalCalories += protein.get(selection) * Integer.parseInt(serving);
            calCount.setText(totalCalories.toString());
        }
    }


}