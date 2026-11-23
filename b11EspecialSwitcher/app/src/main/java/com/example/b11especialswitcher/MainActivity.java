package com.example.b11especialswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText Galones, Pintas;
    private Button Enviar;
    private TextView Resultado;

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

        Galones = findViewById(R.id.Galones);
        Pintas = findViewById(R.id.Pintas);
        Enviar = findViewById(R.id.Enviar);
        Resultado = findViewById(R.id.Resultado);

        if (getIntent().hasExtra("ResultadoFinal")) {
            String resultadoFinal = getIntent().getStringExtra("ResultadoFinal");
            Resultado.setText("Resultado devuelto: " + resultadoFinal);
        }

        Enviar.setOnClickListener(v -> {
            String galones = Galones.getText().toString();
            String pintas = Pintas.getText().toString();

            if (galones.isEmpty() || pintas.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce galones y pintas", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int galones2 = Integer.parseInt(galones);
                int pintas2 = Integer.parseInt(pintas);

                if (pintas2 < 0 || pintas2 > 7) {
                    Toast.makeText(this, "Error: La cantidad máxima de pintas admitidas es 7", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Galones", galones2);
                intent.putExtra("Pintas", pintas2);
                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Introduce números enteros válidos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Se ejecuta al volver desde SecondActivity (CLEAR_TOP | SINGLE_TOP no recrea la actividad)
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.hasExtra("ResultadoFinal")) {
            String resultadoFinal = intent.getStringExtra("ResultadoFinal");
            Resultado.setText("Resultado devuelto: " + resultadoFinal);
        }
    }
}