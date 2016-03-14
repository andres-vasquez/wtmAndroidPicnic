package com.example.androidpicnic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    //Definimos variables para los elementos visuales
    private TextView txtContador;
    private ListView lstProductos;
    private Button btnNuevoProducto;

    //Definimos variables para el funcionamiento de la lista
    private List<String> lista=new ArrayList<String>();
    private ArrayAdapter adapter;
    private int intCantidad=0;

    //Context: donde se ejecuta la App
    private Context context;

    //CODIGOS DE Acciones para llenar la lista
    public final int REQUEST_CODE_ESCANNER=100;
    public final int REQUEST_CODE_SIN_ESCANER=120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //Contexto: aca.
        context=this;

        //Llenamos las variables de los elementos de interfaz presentes en: activity_lista.xml
        txtContador=(TextView)findViewById(R.id.txtContador);
        lstProductos=(ListView)findViewById(R.id.lstProductos);
        btnNuevoProducto=(Button)findViewById(R.id.btnNuevoProducto);

        //Inicializamos el funcionamiento de la lista
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        lstProductos.setAdapter(adapter);
        actualizaCantidad();

        //Activamos el evento CLICK del boton NUEVO PRODUCTO
        btnNuevoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /********* ATENCION ************/

                //Si ejecutamos la App en un dispositivo con cámara
                Escanear();

                //Si ejecutamos en un emulador
                //IngresarManual();
            }
        });
    }

    //Funcion que iniciará la cámara y el Escaner de código de barras
    public void Escanear()
    {
        Intent i=new Intent(context,IngresaItemActivity.class);
        startActivityForResult(i, REQUEST_CODE_ESCANNER);
    }

    //Funcion que iniciará el formulario de ingreso de datos MANUAL
    public void IngresarManual()
    {
        Intent i=new Intent(context,FormularioProducto.class);
        startActivityForResult(i, REQUEST_CODE_SIN_ESCANER);
    }

    //Funcion que determina si un código de barras está en la lista y evitar repetidos
    private boolean existeEnLista(String strProducto)
    {
        boolean existe=false;
        for(String itemLista : lista)
            if(itemLista.compareTo(strProducto)==0)
                existe=true;
        return existe;
    }

    //Actualiza el TextView de la cantidad
    private void actualizaCantidad()
    {
        txtContador.setText("Cantidad: "+intCantidad);
    }

    //Agrega un item a la lista
    private void actualizaLista(String strProducto)
    {
        if(!existeEnLista(strProducto)) {
            //Solo lo agrega si no es repetido
            lista.add(strProducto);
            adapter.notifyDataSetChanged();
            //!!!!IMPORTANTE: con notifyDataSetChanged avisas a la lista que cambio (agregó/eliminó) items.

            //Actualizamos el TextView con la cantidad
            intCantidad=lista.size();
            actualizaCantidad();
        }
    }


    //Funcion que recibe los códigos provenientes de CAMARA o FORMULARIO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            //CODIGO proveniente del ESCANER
            if(requestCode==REQUEST_CODE_ESCANNER)
            {
                String codigo = data.getExtras().getString("codigo");
                actualizaLista(codigo);
            }
            //CODIGO proveniente del formulario manual
            else if(requestCode==REQUEST_CODE_SIN_ESCANER)
            {
                String codigo = data.getExtras().getString("codigo");
                actualizaLista(codigo);
            }
        }
    }
}
