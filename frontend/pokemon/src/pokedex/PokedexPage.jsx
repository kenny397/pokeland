import React from "react";
import pokemonList from "../fixtures/pokemonList";
import PokemonItem from "./PokemonItem";

export default function PokedexPage() {
  return (
    <>
      <h1>Pokedex</h1>
      {pokemonList.map((item) => (
        <PokemonItem
          item={item}
          key={item['id']}
        />
      ))}
    </>
  );
}
