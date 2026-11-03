package com.example.b11especialswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private TextView ValRecibidos, ResultConversion;
    private Button Convertir, Regresar;
    private double totalLitros = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ValRecibidos = findViewById(R.id.ValRecibidos);
        ResultConversion = findViewById(R.id.ResultConversion);
        Convertir = findViewById(R.id.Convertir);
        Regresar = findViewById(R.id.Regresar);

        int galones = getIntent().getIntExtra("Galones", 0);
        int pintas = getIntent().getIntExtra("Pintas", 0);

        ValRecibidos.setText("Galones: " + galones + " | Pintas: " + pintas);

        Convertir.setOnClickListener(v -> {
            double litrosGalones = galones * 4.5461;
            double litrosPintas = pintas * 0.5683; // corregido: 1 pinta imperial = 0,5683 L
            totalLitros = litrosGalones + litrosPintas;

            String textoResultado = String.format("%d galones y %d pintas son %.4f litros.\n(%.4f + %.4f)",
                    galones, pintas, totalLitros, litrosGalones, litrosPintas);

            ResultConversion.setText(textoResultado);
        });

        Regresar.setOnClickListener(v -> {
            Intent intentRegreso = new Intent(SecondActivity.this, MainActivity.class);
            intentRegreso.putExtra("ResultadoFinal", String.format("%.4f litros", totalLitros));
            intentRegreso.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intentRegreso);
            finish();
        });
    }
}