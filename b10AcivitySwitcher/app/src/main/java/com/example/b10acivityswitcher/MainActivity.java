package com.example.b10acivityswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1, etNum2;
    private Button btnJump2;
    private TextView tvResultado;

    // Lanzador para recibir el resultado de la suma cuando volvamos de SecondActivity
    private ActivityResultLauncher<Intent> secondActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNum1 = findViewById(R.id.editTextNumber);
        etNum2 = findViewById(R.id.editTextNumber2);
        btnJump2 = findViewById(R.id.button);
        tvResultado = findViewById(R.id.resultado);

        // Inicializamos el contrato para startActivityForResult moderno
        secondActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        int suma = result.getData().getIntExtra("SUMA_TOTAL", 0);
                        tvResultado.setText(String.valueOf(suma));
                    }
                }
        );

        btnJump2.setOnClickListener(v -> {
            String texto1 = etNum1.getText().toString();
            String texto2 = etNum2.getText().toString();

            if (texto1.isEmpty() || texto2.isEmpty()) {
                Toast.makeText(this, "Introduce ambos números", Toast.LENGTH_SHORT).show();
                return;
            }

            int num1 = Integer.parseInt(texto1);
            int num2 = Integer.parseInt(texto2);

            // Creamos el Intent para ir a SecondActivity y enviamos los números
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("NUM_1", num1);
            intent.putExtra("NUM_2", num2);

            // Lanzamos esperando un resultado de vuelta
            secondActivityLauncher.launch(intent);
        });
    }
}