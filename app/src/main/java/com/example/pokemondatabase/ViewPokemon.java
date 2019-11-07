package com.example.pokemondatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPokemon extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4,textView5;
    ImageView imageView;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pokemon);

        textView1 = findViewById(R.id.nombreTV);
        textView2 = findViewById(R.id.tipoTV);
        textView3 = findViewById(R.id.habilidadTV);
        textView4 = findViewById(R.id.nombreTV);
        textView5 = findViewById(R.id.nombreTV);
        imageView = findViewById(R.id.imageView3);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Intent intent = getIntent();
        intent.getIntExtra(getResources().getString(R.string.id), -1);
        //viewModel.get(p);
    }
}
