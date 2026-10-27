package com.example.b10acivityswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private TextView tvNum1Show, tvNum2Show;
    private Button btnJump1;
    private int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvNum1Show = findViewById(R.id.textView3);
        tvNum2Show = findViewById(R.id.textView5);
        btnJump1 = findViewById(R.id.button); // El botón de JUMP #1 en tu XML de la segunda pantalla

        // Recogemos los números enviados desde MainActivity
        num1 = getIntent().getIntExtra("NUM_1", 0);
        num2 = getIntent().getIntExtra("NUM_2", 0);

        // Los mostramos en los TextViews
        tvNum1Show.setText(String.valueOf(num1));
        tvNum2Show.setText(String.valueOf(num2));

        // Al pulsar el botón para volver a la 1
        btnJump1.setOnClickListener(v -> {
            int suma = num1 + num2;

            // Preparamos el Intent de vuelta con la suma
            Intent resultIntent = new Intent();
            resultIntent.putExtra("SUMA_TOTAL", suma);
            setResult(RESULT_OK, resultIntent);

            // Cerramos esta actividad para volver a la principal
            finish();
        });
    }
}