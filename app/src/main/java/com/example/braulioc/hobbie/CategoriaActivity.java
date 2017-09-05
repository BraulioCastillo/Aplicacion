package com.example.braulioc.hobbie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoriaActivity extends AppCompatActivity {

    final String[] categorias = new String[]{"Fútbol","Surfing","Ciclismo","Skateboard","Atletismo",
            "Tennis"};
    final int[] imagenes = new int[] {R.drawable.futbol, R.drawable.surf, R.drawable.ciclismo,
            R.drawable.skateboard, R.drawable.atletismo};
    ListView cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categorias);
        cat = (ListView)findViewById(R.id.listaCategoria);
        cat.setAdapter(adaptador);
    }
}
