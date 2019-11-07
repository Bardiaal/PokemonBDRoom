package com.example.pokemondatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon")
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "tipo")
    public String tipo;

    @ColumnInfo(name = "habilidad")
    public String habilidad;

    @ColumnInfo(name = "peso")
    public double peso;

    @ColumnInfo(name = "altura")
    public int altura;

    @ColumnInfo(name = "imagen")
    public String imagen;

    @Override
    public String toString() {
        String salida = "";
        salida += "[" + id + ", "
                + nombre + ", "
                + tipo + ", "
                + habilidad + ", "
                + peso + ", "
                + altura + ", "
                + imagen + "]";
        return salida;
    }
}
