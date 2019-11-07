package com.example.pokemondatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokemonDAO {
    //crud - create, read, update, delete
    @Delete
    int delete(Pokemon pokemon);

    @Update
    int edit(Pokemon pokemon);

    @Query("select * from pokemon where id = :id")
    Pokemon get(int id);

    @Insert
    //Devuelve el id
    long insert(Pokemon pokemon);

    @Insert
    List<Long> insert(List<Pokemon> pokemons);

    @Insert
    long[] insert(Pokemon[] pokemons);

    @Query("SELECT * FROM pokemon order by tipo, nombre, id desc")
    LiveData<List<Pokemon>> getAllLive();

    /*------------------------------------------------------------------

    @Query("SELECT * FROM pokemon WHERE nombre LIKE :nombre AND " +
            "tipo LIKE :tipo LIMIT 1")
    Pokemon findByName(String nombre, String tipo);

    @Query("SELECT imagen FROM pokemon WHERE id = :id LIMIT 1")
    Pokemon findById(int id);
    */
    @Query("SELECT * FROM pokemon WHERE id IN (:ids)")
    List<Pokemon> loadAllByIds(int[] ids);
}
