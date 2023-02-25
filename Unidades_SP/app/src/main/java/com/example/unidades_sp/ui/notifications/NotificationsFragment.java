package com.example.unidades_sp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unidades_sp.R;
import com.example.unidades_sp.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    //Longitud
    Spinner entrada, salida;
    EditText txt_entrada;
    TextView txt_salida;
    Button btn_convertir;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootVie = inflater.inflate(R.layout.fragment_notifications,container,false);

        entrada = (Spinner) rootVie.findViewById(R.id.text_OpcionesEntradaL);
        salida = (Spinner) rootVie.findViewById(R.id.text_OpcionesSalidaL);
        txt_entrada = (EditText) rootVie.findViewById(R.id.text_entradaL);
        btn_convertir = (Button) rootVie.findViewById(R.id.btn_ConvertirL);
        txt_salida = (TextView) rootVie.findViewById(R.id.text_salidaL);

        String[] deConvertir = {"Centímetros (cm)","Metros (m)", "Kilometros (km)" };
        ArrayAdapter<String> adapterDeConvertir = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, deConvertir);
        entrada.setAdapter(adapterDeConvertir);

        String[] aConvertir = {"Centímetros (cm)","Metros (m)", "Kilometros (km)" };
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

                //CM
                if (entrada.getSelectedItem().toString() == "Centímetros (cm)" && salida.getSelectedItem().toString() == "Metros (m)"){
                    operacion = numEntrada * 0.01;
                    txt_salida.setText(Double.toString(operacion)+" m");
                    //Toast.makeText(getApplicationContext(),operacion.toString(),Toast.LENGTH_LONG).show();
                }
                else if (entrada.getSelectedItem().toString() == "Centímetros (cm)" && salida.getSelectedItem().toString() == "Kilometros (km)"){
                    operacion = numEntrada * 0.00001;
                    txt_salida.setText(Double.toString(operacion)+" km");

                }
                else if (entrada.getSelectedItem().toString() == "Centímetros (cm)" && salida.getSelectedItem().toString() == "Centímetros (cm)"){
                    txt_salida.setText(Double.toString(numEntrada)+" cm");

                }

                //Metro
                else if (entrada.getSelectedItem().toString() == "Metros (m)" && salida.getSelectedItem().toString() == "Centímetros (cm)"){
                    operacion = numEntrada * 100;
                    txt_salida.setText(Double.toString(operacion)+"cm");
                }
                else if (entrada.getSelectedItem().toString() == "Metros (m)" && salida.getSelectedItem().toString() == "Kilometros (km)"){
                    operacion = numEntrada * 0.001;
                    txt_salida.setText(Double.toString(operacion)+" km");
                }
                else if (entrada.getSelectedItem().toString() == "Metros (m)" && salida.getSelectedItem().toString() == "Metros (m)"){
                    txt_salida.setText(Double.toString(numEntrada)+" m");

                }

                //KM
                else if (entrada.getSelectedItem().toString() == "Kilometros (km)" && salida.getSelectedItem().toString() == "Centímetros (cm)"){
                    operacion = numEntrada * 100000;
                    txt_salida.setText(Double.toString(operacion)+" cm");
                }
                else if (entrada.getSelectedItem().toString() == "Kilometros (km)" && salida.getSelectedItem().toString() == "Metros (m)"){
                    operacion = numEntrada * 1000;
                    txt_salida.setText(Double.toString(operacion)+" m");
                }
                else if (entrada.getSelectedItem().toString() == "Kilometros (km)" && salida.getSelectedItem().toString() == "Kilometros (km)"){
                    txt_salida.setText(Double.toString(numEntrada)+" km");

                }

            }
        });

        return rootVie;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}