package com.example.unidades_sp.ui.Peso;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unidades_sp.R;
import com.example.unidades_sp.databinding.FragmentNotificationsBinding;

public class PesoFragment extends Fragment {

    Spinner entrada, salida;
    EditText txt_entrada;
    TextView txt_salida;
    Button btn_convertir;
    private FragmentNotificationsBinding binding;

    public static PesoFragment newInstance() {
        return new PesoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootVie = inflater.inflate(R.layout.fragment_peso,container,false);

        entrada = (Spinner) rootVie.findViewById(R.id.text_OpcionesEntradap);
        salida = (Spinner) rootVie.findViewById(R.id.text_OpcionesSalidaP);
        txt_entrada = (EditText) rootVie.findViewById(R.id.text_entradaP);
        btn_convertir = (Button) rootVie.findViewById(R.id.btn_ConvertirP);
        txt_salida = (TextView) rootVie.findViewById(R.id.text_salidaP);

        String[] deConvertir = {"Toneladas (t)","Libras (lb)", "Onzas (oz)" };
        ArrayAdapter<String> adapterDeConvertir = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, deConvertir);
        entrada.setAdapter(adapterDeConvertir);

        String[] aConvertir = {"Toneladas (t)","Libras (lb)", "Onzas (oz)" };
        ArrayAdapter<String> adapterAConvertir = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, aConvertir);
        salida.setAdapter(adapterAConvertir);

        btn_convertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_entrada.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "Ingresa un número", Toast.LENGTH_SHORT).show();
                    return;
                }

                Double operacion;
                Double numEntrada = Double.parseDouble(txt_entrada.getText().toString());

                //Toneladas
                if (entrada.getSelectedItem().toString() == "Toneladas (t)" && salida.getSelectedItem().toString() == "Libras (lb)"){
                    operacion = numEntrada * 2204.62;
                    txt_salida.setText(Double.toString(operacion)+" lb");
                }
                else if (entrada.getSelectedItem().toString() == "Toneladas (t)" && salida.getSelectedItem().toString() == "Onzas (oz)"){
                    operacion = numEntrada * 35274;
                    txt_salida.setText(Double.toString(operacion)+" oz");

                }
                else if (entrada.getSelectedItem().toString() == "Toneladas (t)" && salida.getSelectedItem().toString() == "Toneladas (t)"){
                    txt_salida.setText(Double.toString(numEntrada)+" t");

                }

                //LIBRAS
                else if (entrada.getSelectedItem().toString() == "Libras (lb)" && salida.getSelectedItem().toString() == "Toneladas (t)"){
                    operacion = numEntrada * 0.00045359;
                    txt_salida.setText(Double.toString(operacion)+"t");
                }
                else if (entrada.getSelectedItem().toString() == "Libras (lb)" && salida.getSelectedItem().toString() == "Onzas (oz)"){
                    operacion = numEntrada * 16;
                    txt_salida.setText(Double.toString(operacion)+" oz");
                }
                else if (entrada.getSelectedItem().toString() == "Libras (lb)" && salida.getSelectedItem().toString() == "Libras (lb)"){
                    txt_salida.setText(Double.toString(numEntrada)+" lb");

                }

                //ONZAS
                else if (entrada.getSelectedItem().toString() == "Onzas (oz)" && salida.getSelectedItem().toString() == "Toneladas (t)"){
                    operacion = numEntrada * 0.0000283495;
                    txt_salida.setText(Double.toString(operacion)+" t");
                }
                else if (entrada.getSelectedItem().toString() == "Onzas (oz)" && salida.getSelectedItem().toString() == "Libras (lb)"){
                    operacion = numEntrada * 0.0625;
                    txt_salida.setText(Double.toString(operacion)+" lb");
                }
                else if (entrada.getSelectedItem().toString() == "Onzas (oz)" && salida.getSelectedItem().toString() == "Onzas (oz)"){
                    txt_salida.setText(Double.toString(numEntrada)+" oz");

                }

            }
        });

        return rootVie;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}