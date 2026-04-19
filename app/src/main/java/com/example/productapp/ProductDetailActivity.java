package com.example.productapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays product details
 */

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView name = findViewById(R.id.txtName);
        TextView price = findViewById(R.id.txtPrice);
        TextView desc = findViewById(R.id.txtDesc);

        Intent i = getIntent();
        name.setText(i.getStringExtra("name"));
        price.setText("$" + i.getDoubleExtra("price", 0));
        desc.setText(i.getStringExtra("desc"));
    }
}