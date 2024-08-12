package com.example.calculadoraeje3mod3;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Calculadora extends AppCompatActivity {

    private EditText numero1, numero2;
    private TextView textResultado;
    private Button btnSuma, btnResta, btnDivision, btnMultiplicacion, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);  // Asegúrate de que este nombre coincide con el XML

        // Enlazar las vistas con los componentes en el layout
        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        textResultado = findViewById(R.id.textResultado);
        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion);
        btnSalir = findViewById(R.id.btnSalir);

        // Programar los botones para las operaciones
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("suma");
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("resta");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("division");
            }
        });

        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("multiplicacion");
            }
        });

        // Programar el botón "Salir" para cerrar la aplicación
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoSalir();
            }
        });
    }

    // Método para realizar las operaciones
    private void realizarOperacion(String operacion) {
        String strNum1 = numero1.getText().toString();
        String strNum2 = numero2.getText().toString();

        // Validaciones de campos vacíos
        if (TextUtils.isEmpty(strNum1)) {
            Toast.makeText(this, "Por favor, ingrese el primer número", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strNum2)) {
            Toast.makeText(this, "Por favor, ingrese el segundo número", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir los textos a números
        int num1 = Integer.parseInt(strNum1);
        int num2 = Integer.parseInt(strNum2);
        int resultado = 0;

        // Realizar la operación
        switch (operacion) {
            case "suma":
                resultado = num1 + num2;
                break;
            case "resta":
                resultado = num1 - num2;
                break;
            case "division":
                if (num2 == 0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultado = num1 / num2;
                break;
            case "multiplicacion":
                resultado = num1 * num2;
                break;
        }

        // Mostrar el resultado
        textResultado.setText(String.valueOf(resultado));
    }

    // Método para mostrar un diálogo de confirmación antes de salir de la aplicación
    private void mostrarDialogoSalir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Calculadora.this);
        builder.setMessage("¿Está seguro que desea salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();  // Cierra la aplicación
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();  // Cierra el diálogo y continúa en la aplicación
            }
        });

        // Mostrar el diálogo
        builder.show();
    }
}
