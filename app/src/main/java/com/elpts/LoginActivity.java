package com.elpts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import sberid.sdk.auth.SberIDButton;
import sberid.sdk.auth.SberIDLoginManager;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.esia_button)
    MaterialButton esiaButton;
    @BindView(R.id.sber_button)
    SberIDButton sberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        SberUri sberUri = new SberUri();
        esiaButton.setOnClickListener((b) -> {
            startActivity(MainActivity.intentFor(getApplicationContext()));
        });
        sberButton.setOnClickListener((b) -> {
            SberIDLoginManager.Companion.loginWithSberbankID(this, sberUri.getUri());
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getData() != null) {
            SberIDLoginManager.Companion.getSberIDAuthResult(intent);
            startActivity(MainActivity.intentFor(getApplicationContext()));
        }
    }

}
