import React from "react";
import pokemonList from "../fixtures/pokemonList";

import PokemonItemContainer from "./PokemonItemContainer";

export default function PokemonList({ page, onClickGoToPrev, onClickGoToNext }) {
  const start = (page - 1) * 6;
  const end = page * 6;
  
  const paginatedPokemonList = pokemonList.slice(start, end);
  let emptyGridItems = [1, 2, 3, 4, 5, 6];
  for (let i = 0; i < paginatedPokemonList.length; i++) {
    emptyGridItems.pop();
  }

  return (
    <div className="pokemon-list">
      {paginatedPokemonList.map((item) => (
        <PokemonItemContainer
          item={item}
          key={item.id}
        />
      ))}

      {emptyGridItems.map((ele) => (
        <div className="empty-grid-item" key={ele}> </div>
      ))}
      {
        pokemonList.length > 6 &&
        <button className="prev-btn" onClick={() => onClickGoToPrev()}>왼쪽</button>
      }
      {
        paginatedPokemonList.length == 6 &&
        <button className="next-btn" onClick={() => onClickGoToNext()}>오른쪽</button>
      }
    </div>
  );
}
