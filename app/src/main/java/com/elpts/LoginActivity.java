package com.elpts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.esia_button)
    MaterialButton esiaButton;
    @BindView(R.id.sber_button)
    MaterialButton sberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        esiaButton.setOnClickListener((b) -> {
            startActivity(MainActivity.intentFor(getApplicationContext()));
        });
        sberButton.setOnClickListener((b) -> {
            startActivity(MainActivity.intentFor(getApplicationContext()));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
