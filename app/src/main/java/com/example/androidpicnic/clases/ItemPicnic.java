package com.example.androidpicnic.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andresvasquez on 9/3/16.
 */
public class ItemPicnic {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("codigo")
    @Expose
    public String codigo;

    @SerializedName("descripcion")
    @Expose
    public String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
