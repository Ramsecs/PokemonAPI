package com.example.PokemonAPI.service;

import com.example.PokemonAPI.exception.ResourceNotTimeException;
import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> obtenerTodos(){
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPorId(Long id){
        return pokemonRepository.findById(id).orElseThrow(() -> new ResourceNotTimeException("El pokemon no existe"));
    }

    public Pokemon crear(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public void eliminar(Long id){
        pokemonRepository.deleteById(id);
    }

    public Optional<Pokemon> editar(Long id, Pokemon pokemon){
        return pokemonRepository.findById(id).map(pokemonExist -> {
            pokemonExist.setNombre(pokemon.getNombre());
            pokemonExist.setNivel(pokemon.getNivel());
            pokemonExist.setDefensa(pokemon.getDefensa());
            pokemonExist.setDescripcion(pokemon.getDescripcion());
            pokemonExist.setImagenURL(pokemon.getImagenURL());
            pokemonExist.setTipo(pokemon.getTipo());
            return pokemonRepository.save(pokemonExist);
        });
    }

    public Optional<Pokemon> editarParcial(Long id, Pokemon pokemon){
        return pokemonRepository.findById(id).map(pokemonExist -> {
            if (pokemon.getNombre() != null) pokemonExist.setNombre(pokemon.getNombre());
            if (pokemon.getNivel() != null) pokemonExist.setNivel(pokemon.getNivel());
            if (pokemon.getDefensa() != null) pokemonExist.setDefensa(pokemon.getDefensa());
            if (pokemon.getDescripcion() != null) pokemonExist.setDescripcion(pokemon.getDescripcion());
            if (pokemon.getImagenURL() != null) pokemonExist.setImagenURL(pokemon.getImagenURL());
            if (pokemon.getTipo() != null) pokemonExist.setTipo(pokemon.getTipo());
            return pokemonRepository.save(pokemonExist);
        });
    }

}
