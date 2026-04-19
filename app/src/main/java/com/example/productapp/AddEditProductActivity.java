package com.example.productapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for adding and editing products
 */

public class AddEditProductActivity extends AppCompatActivity {

    EditText etName, etPrice, etDesc;
    int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etDesc = findViewById(R.id.etDesc);

        // CHECK EDIT MODE
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", -1);
            etName.setText(getIntent().getStringExtra("name"));
            etPrice.setText(String.valueOf(getIntent().getDoubleExtra("price", 0)));
            etDesc.setText(getIntent().getStringExtra("desc"));
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> {

            // VALIDATION
            if (etName.getText().toString().isEmpty()) {
                etName.setError("Required");
                return;
            }

            Intent result = new Intent();
            result.putExtra("id", id);
            result.putExtra("name", etName.getText().toString());
            result.putExtra("price", Double.parseDouble(etPrice.getText().toString()));
            result.putExtra("desc", etDesc.getText().toString());

            setResult(RESULT_OK, result);
            finish();
        });
    }
}