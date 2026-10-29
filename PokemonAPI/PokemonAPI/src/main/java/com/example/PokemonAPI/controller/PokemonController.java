package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> listarPokemones(){
        return pokemonService.obtenerTodos();
    }

    @PostMapping
    public Pokemon crear(
            @Valid @RequestBody Pokemon pokemon){
        return pokemonService.crear(pokemon);
    }

    @GetMapping("/{id}")
    public Pokemon obtenerPorId(@PathVariable Long id){
        return pokemonService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Pokemon actualizarCompleto(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.editar(id, pokemon).orElse(null);
    }

    @PatchMapping("/{id}")
    public Pokemon actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.editarParcial(id, pokemon).orElse(null);
    }

    // Eliminar un pokemon por id
    @DeleteMapping("/{id}")
    public void eliminarPokemon(@PathVariable Long id) {
        pokemonService.eliminar(id);
    }





}
