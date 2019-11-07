package com.example.pokemondatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private class InsertThread extends AsyncTask<Pokemon, Void, Void> {
        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDAO.insert(pokemons[0]);
            Log.v("xyz",pokemons[0].toString());
            return null;
        }
    }

    private class GetThread extends AsyncTask<Pokemon, Void, Void> {
        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDAO.get(pokemons[0].id);
            Log.v("xyz",pokemons[0].toString());
            return null;
        }
    }

    private class DeleteThread extends AsyncTask<Pokemon, Void, Void> {
        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDAO.delete(pokemons[0]);
            return null;
        }
    }

    private PokemonDAO pokemonDAO;
    private LiveData<List<Pokemon>> pokemonsLive;

    public Repository(Context contexto){
        PokemonDatabase db = PokemonDatabase.getDatabase(contexto);
        pokemonDAO = db.getPokemonDAO();
        //populateDb();
        pokemonsLive = pokemonDAO.getAllLive();
    }

    public LiveData<List<Pokemon>> getPokemonsLive(){
        return pokemonsLive;
    }

    public Repository insertPokemon(Pokemon pokemon){
        new InsertThread().execute(pokemon);
        return this;
    }

    public Repository getPokemon(Pokemon pokemon){
        new GetThread().execute(pokemon);
        return this;
    }

    public Repository deletePokemon(Pokemon pokemon){
        new DeleteThread().execute(pokemon);
        return this;
    }
}
