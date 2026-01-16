package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addButton;
    Button deleteButton;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Toronto", "Vancouver", "Boston", "Melbourne", "Dhaka", "Tokyo", "Osaka", "Berlin"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content,R.id.content_view , dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = new EditText(MainActivity.this);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Add City")
                        .setView(input)
                        .setPositiveButton("CONFIRM", (dialog, which) -> {
                            String city = input.getText().toString();
                            if (!city.isEmpty()) {
                                dataList.add(city);
                                cityAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("CANCEL", null)
                        .show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition = -1;
                }
            }
        });



    }
}