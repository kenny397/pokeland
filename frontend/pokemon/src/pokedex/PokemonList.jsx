import React from "react";
import pokemonList from "../fixtures/pokemonList";
import PokemonItem from "./PokemonItem";

export default function PokemonList({ page }) {
  const start = (page - 1) * 6;
  const end = page * 6;
  
  const paginatedPokemonList = pokemonList.slice(start, end);

  return (
    <div className="pokemon-list">
      {paginatedPokemonList.map((item) => (
        <PokemonItem
          page={page}
          item={item}
          key={item['id']}
        />
      ))}
    </div>
  );
}
