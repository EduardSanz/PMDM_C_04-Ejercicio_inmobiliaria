package com.cieep.a04_ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cieep.a04_ejercicioinmobiliaria.configuraciones.Constantes;
import com.cieep.a04_ejercicioinmobiliaria.databinding.ActivityEditInmuebleBinding;
import com.cieep.a04_ejercicioinmobiliaria.modelos.Inmueble;

public class EditInmuebleActivity extends AppCompatActivity {

    private ActivityEditInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentMain = getIntent();
        Bundle bundleMain = intentMain.getExtras();
        Inmueble inmuebleEdit = (Inmueble) bundleMain.getSerializable(Constantes.INMUEBLE);

        Log.d("INMU", inmuebleEdit.toString());

        binding.txtDireccionEditInmueble.setText(inmuebleEdit.getDireccion());
        binding.txtNumeroEditInmueble.setText(String.valueOf(inmuebleEdit.getNumero()));
        binding.txtCPEditInmueble.setText(inmuebleEdit.getCp());
        binding.txtCiudadEditInmueble.setText(inmuebleEdit.getCiudad());
        binding.txtProvinciaEditInmueble.setText(inmuebleEdit.getProvincia());
        binding.rbValEditInmueble.setRating(inmuebleEdit.getValoracion());


        binding.btnActualizarEditInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmueble = crearInmueble();
                if (inmueble != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.INMUEBLE, inmueble);
                    bundle.putInt(Constantes.POSICION, bundleMain.getInt(Constantes.POSICION));
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(EditInmuebleActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnBorrarEditInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constantes.POSICION, bundleMain.getInt(Constantes.POSICION));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Inmueble crearInmueble() {

        if (
                binding.txtCiudadEditInmueble.getText().toString().isEmpty() ||
                        binding.txtCPEditInmueble.getText().toString().isEmpty() ||
                        binding.txtProvinciaEditInmueble.getText().toString().isEmpty() ||
                        binding.txtNumeroEditInmueble.getText().toString().isEmpty() ||
                        binding.txtDireccionEditInmueble.getText().toString().isEmpty()
        )
            return null;

        return new Inmueble(
                binding.txtDireccionEditInmueble.getText().toString(),
                Integer.parseInt(binding.txtNumeroEditInmueble.getText().toString()),
                binding.txtCPEditInmueble.getText().toString(),
                binding.txtCiudadEditInmueble.getText().toString(),
                binding.txtProvinciaEditInmueble.getText().toString(),
                binding.rbValEditInmueble.getRating()
        );
    }
}