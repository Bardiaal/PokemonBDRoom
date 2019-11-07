package com.example.pokemondatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class InsertPokemon extends AppCompatActivity {

    private static final int PHOTO_SELECTED = 1;
    String ruta = "";
    Bitmap bitmapBig;
    ImageView imageView;
    EditText editText1, editText2, editText3, editText4, editText5;
    Button button;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pokemon);

        imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.home_timeline_avatar);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage(InsertPokemon.this, bitmapBig);
                if (ruta.equals("")) {
                    ruta = "/data/user/0/com.example.recyclerexample/files/saved_images/Image-default.jpg";
                }
                Pokemon pokemon = new Pokemon();
                pokemon.nombre = editText1.getText().toString();
                pokemon.tipo = editText2.getText().toString();
                pokemon.habilidad = editText3.getText().toString();
                pokemon.peso = Double.parseDouble(editText4.getText().toString());
                pokemon.altura = Integer.parseInt(editText5.getText().toString());
                pokemon.imagen = ruta;
                viewModel.insert(pokemon);
                startActivity(new Intent(InsertPokemon.this, MainActivity.class));
            }
        });


        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getListPokemon().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                MainActivity.adapter.setPokemons(pokemons);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PHOTO_SELECTED);
                imageView.setImageBitmap(bitmapBig);
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                bitmapBig = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(bitmapBig);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(InsertPokemon.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(InsertPokemon.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            imageView.setImageResource(R.drawable.home_timeline_avatar);
        }
    }

    private void saveImage(Context context, Bitmap finalBitmap) {

        String root = context.getFilesDir().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        ruta = root + "/saved_images/" + fname;
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.v("SUCCESS", "image saved");
        } catch (Exception e) {
            e.printStackTrace();
            ruta = "/data/user/0/com.example.recyclerexample/files/saved_images/Image-default.jpg";
        }
    }
}
