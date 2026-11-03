package com.example.b07numerosprimos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button boton;
    EditText texto;
    TextView respuesta;

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

        texto = findViewById(R.id.eTMeterNumeros);
        boton = findViewById(R.id.boton);
        respuesta = findViewById(R.id.respuesta);

        boton.setOnClickListener(v -> {
            String numeros = texto.getText().toString();

            if (!numeros.trim().isEmpty()) {
                try {
                    int numeroTexto = Integer.parseInt(numeros);
                    String resultado = retornarPrimo(numeroTexto);

                    // 1. Mostrar resultado abajo
                    respuesta.setText(resultado);

                    // 2. Vaciar la caja de texto
                    texto.setText("");

                } catch (NumberFormatException e) {
                    respuesta.setText("Número no válido");
                }
            } else {
                respuesta.setText("Introduce un número");
            }
        });
    }

    public String retornarPrimo(int posicion) {
        if (posicion <= 0 || posicion > 1000) {
            return "Error: Introduce un ordinal entre 1 y 1000";
        }

        int contador = 0;
        int numeroActual = 1;

        while (contador < posicion) {
            numeroActual++;
            if (esPrimo(numeroActual)) {
                contador++;
            }
        }

        return "El número primo en la posición " + posicion + " es:\n" + numeroActual;
    }

    private boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}