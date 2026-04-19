package com.example.productapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * MainActivity displays list of products using RecyclerView
 *
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;
    private int productIdCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        productList = new ArrayList<>();

        adapter = new ProductAdapter(productList, MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(this, AddEditProductActivity.class), 1);
        });
    }

    /**
     * Handles Add and Edit results
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                // ADD
                productList.add(new Product(
                        productIdCounter++,
                        data.getStringExtra("name"),
                        data.getDoubleExtra("price", 0),
                        data.getStringExtra("desc")
                ));
            } else if (requestCode == 2) {
                // EDIT
                int id = data.getIntExtra("id", -1);

                for (Product p : productList) {
                    if (p.getId() == id) {
                        p.setName(data.getStringExtra("name"));
                        p.setPrice(data.getDoubleExtra("price", 0));
                        p.setDescription(data.getStringExtra("desc"));
                        break;
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}