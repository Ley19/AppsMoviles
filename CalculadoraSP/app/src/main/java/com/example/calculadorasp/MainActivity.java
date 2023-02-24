package com.example.calculadorasp;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultado, procedimiento;
    Button cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, multiplicacion, suma, resta, divicion, limpiar, parentesis, punto, porcentaje;
    boolean ex_parentesis = false ;
    String proceso_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        procedimiento = findViewById(R.id.tv_proceso);
        resultado = findViewById(R.id.tv_resultado);

        cero = findViewById(R.id.btn_cero);
        uno = findViewById(R.id.btn_uno);
        dos = findViewById(R.id.btn_dos);
        tres = findViewById(R.id.btn_tres);
        cuatro = findViewById(R.id.btn_cuatro);
        cinco = findViewById(R.id.btn_cicno);
        seis = findViewById(R.id.btn_seis);
        siete = findViewById(R.id.btn_siete);
        ocho = findViewById(R.id.btn_ocho);
        nueve = findViewById(R.id.btn_nueve);
        multiplicacion = findViewById(R.id.btn_multiplicacion);
        suma = findViewById(R.id.btn_suma);
        resta = findViewById(R.id.btn_restar);
        divicion = findViewById(R.id.btn_division);
        limpiar = findViewById(R.id.btn_limpiar);
        parentesis = findViewById(R.id.btn_parentesis);
        punto = findViewById(R.id.btn_punto);
        porcentaje = findViewById(R.id.btn_porcentaje);

        Mostrar_valores();

    }

    public void Mostrar_valores(){

        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "0");
            }
        });
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "1");
            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "2");
            }
        });
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "3");
            }
        });
        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "4");
            }
        });
        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "5");
            }
        });
        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "6");
            }
        });
        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "7");
            }
        });
        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "8");
            }
        });
        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                procedimiento.setText(proceso_string + "9");
            }
        });
        multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                if (proceso_string.matches(".*\\d$") && !proceso_string.endsWith("*")) {
                    procedimiento.setText(proceso_string + "*");
                } else {
                    Toast.makeText(getApplicationContext(), "Formato no válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                if (proceso_string.matches(".*\\d$") && !proceso_string.endsWith("+")) {
                    procedimiento.setText(proceso_string + "+");
                } else {
                    Toast.makeText(getApplicationContext(), "Formato no válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proceso_string.matches(".*\\d$") && !proceso_string.endsWith("-")) {
                    procedimiento.setText(proceso_string + "-");
                } else {
                    Toast.makeText(getApplicationContext(), "Formato no válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        divicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                if (proceso_string.matches(".*\\d$") && !proceso_string.endsWith("/")) {
                    procedimiento.setText(proceso_string + "/");
                } else {
                    Toast.makeText(getApplicationContext(), "Formato no válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        parentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                proceso_string = procedimiento.getText().toString();
                // Verificar si el string está vacío
                if (proceso_string.isEmpty()) {
                    procedimiento.setText("(");
                    ex_parentesis = true;
                    return;
                }

                char ultimoCaracter = proceso_string.charAt(proceso_string.length()-1);

                if (ex_parentesis){
                    procedimiento.setText(proceso_string + ")");
                    ex_parentesis = false;
                }else{
                    if (Character.isDigit(ultimoCaracter)) {
                        procedimiento.setText(proceso_string + "*(");
                        ex_parentesis = true;
                    } else {
                        procedimiento.setText(proceso_string + "(");
                        ex_parentesis = true;
                    }
                }
            }
        });
        punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                if (!proceso_string.endsWith(".")) {
                    procedimiento.setText(proceso_string + ".");
                }
            }
        });
        porcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceso_string = procedimiento.getText().toString();
                if (proceso_string.matches(".*\\d$") && !proceso_string.endsWith("%")) {
                    procedimiento.setText(proceso_string + "%");
                } else {
                    Toast.makeText(getApplicationContext(), "Formato no válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void limpiar(View view) {

        procedimiento.setText("");
        resultado.setText("");

    }

    public void borrar(View view) {

        String borrar_string = procedimiento.getText().toString();

        if (borrar_string.length() >= 1){
            borrar_string = borrar_string.substring(0,borrar_string.length() - 1);
            procedimiento.setText(borrar_string);
        } else if (borrar_string.length() < 1) {
            procedimiento.setText("");
        }
    }

    public void Resultado_final(View view) {

        proceso_string = procedimiento.getText().toString();
        proceso_string = proceso_string.replaceAll("x", "*");
        proceso_string = proceso_string.replaceAll("%", "/100");

        Context context = Context.enter();
        context.setOptimizationLevel(-1);

        String resultado_final = "";

        try {
            Scriptable scriptable = context.initStandardObjects();
            resultado_final = context.evaluateString(scriptable, proceso_string, "javascript",1, null).toString();

        }catch (Exception e){
            resultado_final = "0";
        }

        resultado.setText(resultado_final);

    }
}