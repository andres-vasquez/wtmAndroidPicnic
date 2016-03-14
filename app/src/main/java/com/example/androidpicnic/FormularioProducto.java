package com.example.androidpicnic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioProducto extends AppCompatActivity {

    //Context: donde se ejecuta la App
    private Context context;

    //Definimos variables para el funcionamiento del formulario
    private EditText txtCodigoBarras;
    private Button btnAgregar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_producto);

        //Aca se ejecuta la App
        context=this;

        //Vinculamos las variables de interfaz con las presentes en: activity_formulario_producto.xml
        txtCodigoBarras=(EditText)findViewById(R.id.txtCodigoBarras);
        btnAgregar=(Button)findViewById(R.id.btnAgregar);
        btnVolver=(Button)findViewById(R.id.btnVolver);

        //Agregamos evento CLICK al botón
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validamos que el EditText no esté vacío
                if(txtCodigoBarras.getText().toString().compareTo("")==0)
                {
                    Toast.makeText(context,"Ingrese un código de barras para continuar",Toast.LENGTH_SHORT).show();
                    return;
                }

                //Devolvemos el código ingresado en el campo para adicionarlo a la lista
                Intent intent=new Intent();
                intent.putExtra("codigo", txtCodigoBarras.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        //Evento CLICK al botón volver para finalizar la activity
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
