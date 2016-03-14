package com.example.androidpicnic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Definimos una variable con el boton Ingresar
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //La buscamos con su ID proveniente de activity_main.xml
        btnIngresar=(Button)findViewById(R.id.btnIngresar);

        //Incluimos el evento CLICK
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Llamamos a la siguiente activity
                Intent intent=new Intent(getApplicationContext(),ListaActivity.class);
                startActivity(intent);
            }
        });
    }
}
