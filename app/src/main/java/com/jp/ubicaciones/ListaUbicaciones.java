package com.jp.ubicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaUbicaciones extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnAdd;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ubicaciones);
        btnAdd=findViewById(R.id.btnAdd);
        lista=findViewById(R.id.lista);
        btnAdd.setOnClickListener(this);
        MyDbHelper db = new MyDbHelper(this);
        Ciudad ciudad = new Ciudad();

        ArrayList<Ciudad> ubicaciones= db.selectCiudad(db.getWritableDatabase());
        ArrayList<String> strin= new ArrayList<>();
            int i=1;
            for (int j = 0; j <ubicaciones.size() ; j++) {

                strin.add(i+". "+ubicaciones.get(j).getName()+" - "+ubicaciones.get(j).getPoblacion()+
                        " - "+ubicaciones.get(j).getLatitud()+" - "+ubicaciones.get(j).getLongitud());
                i++;

        }

            ArrayAdapter<Ciudad> adapter=new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,strin);
            lista.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}