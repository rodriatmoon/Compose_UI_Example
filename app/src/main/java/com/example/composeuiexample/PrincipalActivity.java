package com.example.composeuiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    EditText txtUv, txtCalif;
    TextView txtR;
    ArrayList<String> calificacion = new ArrayList<>();
    ArrayList<String> uv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtCalif = (EditText) findViewById(R.id.txtNota);
        txtUv = (EditText) findViewById(R.id.txtUV);
        txtR = (TextView) findViewById(R.id.txtResultado);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CUM COMPOSE UI");
        toolbar.setTitleTextColor(Color.parseColor("#FF03DAC5"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.borrar:
                calificacion.clear();
                uv.clear();
                txtR.setText("Borrado");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void agregar (View V){
        String unidadesv, nota;
        double c = 11;

        unidadesv = txtUv.getText().toString();
        nota = txtCalif.getText().toString();


        if(unidadesv.equals("") || nota.equals("")){
            Toast.makeText(this, "No puede deja campos vacíos", Toast.LENGTH_SHORT).show();
        } else {
            c = Double.valueOf(nota).doubleValue();

            if (c <= 10){
                calificacion.add(nota);
                uv.add(unidadesv);

                txtUv.setText("");
                txtCalif.setText("");
                Toast.makeText(this, "Se agregó correctamente. Ahora tiene: " + calificacion.size() + " Notas registradas" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La nota no puede ser mayor a 10", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void calcular (View V){
        double n, r = 0, cum;
        int u, ut = 0;
        if (calificacion.size() < 2){
            Toast.makeText(this, "Debe agregar más de una calificación", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < calificacion.size(); i++){
                String s, d;

                s = calificacion.get(i);
                n = Double.valueOf(s).doubleValue();

                d = uv.get(i);
                u = Integer.parseInt((d));

                r += n * u;
                ut += u;
            }
            cum = r / ut;
            DecimalFormat cm = new DecimalFormat("#.00");
            txtR.setText(cm.format(cum));
        }
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(txtUv.getWindowToken(), 0);
    }
}