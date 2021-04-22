package com.jp.ubicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName;
    private EditText txtPoblacion;
    private EditText txtLatitud;
    private EditText txtLongitud;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName=findViewById(R.id.txtName);
        txtPoblacion=findViewById(R.id.txtPoblacion);
        txtLatitud=findViewById(R.id.txtLatitud);
        txtLongitud=findViewById(R.id.txtLongitud);
        btnAgregar=findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAgregar) {
            String name = txtName.getText().toString();
            int poblacion = Integer.parseInt(txtPoblacion.getText().toString());
            String latitud = (txtLatitud.getText().toString());
            String longitud = txtLongitud.getText().toString();

            if (name.isEmpty()) {
                CustomToastView.makeInfoToast(this, "Error al validar Nombre", R.layout.custom_toast).show();
                return;
            }
            if (poblacion <=0) {
                CustomToastView.makeInfoToast(this, "Error al validar Poblacion", R.layout.custom_toast).show();
                return;
            }
            if (latitud.isEmpty()) {
                CustomToastView.makeInfoToast(this, "Error al validar Latitud", R.layout.custom_toast).show();
                return;
            }
            if (longitud.isEmpty()) {
                CustomToastView.makeInfoToast(this, "Error al validar Longitud", R.layout.custom_toast).show();
                return;
            }

            MyDbHelper db = new MyDbHelper(this);
            Ciudad ciudad = new Ciudad();
           // ArrayList<Ub> areas= db.selectArea(db.getWritableDatabase());
            //AQUI RECOJO EL DATO QUE ME RETORNA LA CONSULTA
           int idNext = db.selectNextId(db.getWritableDatabase());


            ciudad.setId(idNext);
            ciudad.setName(name);
            ciudad.setPoblacion(poblacion);
            ciudad.setLatitud(latitud);
            ciudad.setLongitud(longitud);

            db.InsertCiudad(db.getWritableDatabase(), ciudad);

            Intent intent= new Intent(this, ListaUbicaciones.class);
            startActivity(intent);
        }
    }
}