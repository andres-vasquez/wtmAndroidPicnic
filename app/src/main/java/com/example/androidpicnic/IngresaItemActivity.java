package com.example.androidpicnic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class IngresaItemActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    //Elemento de interfaz ESCANER
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        //Inicializamos el Escaner de códigos
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    //Cuando la App se cierra, detiene la camara
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    //Cuando la App se pone activa, activa la camara
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    //Si detecta un código lo muestra
    public void handleResult(Result rawResult) {
        Intent intent=new Intent();
        intent.putExtra("codigo", rawResult.getText()); //Encapsula el resultado en la variable código
        setResult(RESULT_OK,intent); // !Importante. Devuelve resultado como OK
        finish();
    }
}
