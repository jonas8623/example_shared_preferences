package com.example.savename;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputEditText;
    private Button buttonSaveName;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditText = findViewById(R.id.editName);
        buttonSaveName = findViewById(R.id.button);
        textViewResult = findViewById(R.id.textViewResult);

        buttonSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(Constant.FILE_PREFERENCES, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String name = textInputEditText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha seu nome", Toast.LENGTH_LONG).show();
                } else {
                    editor.putString(Constant.KEY_NAME, name);
                    editor.apply();
                    textViewResult.setText("Olá, " + name);
                }
            }
        });

        // Recuperar os dados
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.FILE_PREFERENCES, 0);
        if (sharedPreferences.contains(Constant.KEY_NAME)) {
            String name = sharedPreferences.getString(Constant.KEY_NAME, "Olá, usuário não identificado");
            textViewResult.setText("Olá, " + name);
        } else textViewResult.setText("Olá, usuário não identificado");
    }
}