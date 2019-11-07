package com.example.pokemondatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Pokemon>> listPokemon;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        listPokemon = repository.getPokemonsLive();
    }

    public LiveData<List<Pokemon>> getListPokemon() {
        return listPokemon;
    }

    public void insert(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }

    public void get(Pokemon pokemon) {
        repository.getPokemon(pokemon);
    }

    public void delete(Pokemon pokemon) {
        repository.deletePokemon(pokemon);
    }
}
