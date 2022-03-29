import React from "react";
import pokemonList from "../fixtures/pokemonList";
import PokemonItem from "./PokemonItem";
import getImgPath from "../utils/getImgPath";

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
      {paginatedPokemonList.map((item) => {
        let hasNfps = false;
        const { id, name } = item;
        const pokemonShadowImgPath = getImgPath(id, name, 'shadow');
        const pokemonColorImgPath = getImgPath(id, name, 'pokemon');
        const pokemonNum = (id + "").padStart(3, '0');
        return (
          <PokemonItem
            page={page}
            pokemonNum={pokemonNum}
            pokemonName={name}
            pokemonImgPath={hasNfps ? pokemonColorImgPath : pokemonShadowImgPath}
            key={item['id']}
          />
        );
      })}

      {emptyGridItems.map((ele) => (
        <div className="empty-grid-item" key={ele}> </div>
      ))}
      <button className="prev-btn" onClick={onClickGoToPrev}>왼쪽</button>
      <button className="next-btn" onClick={onClickGoToNext}>오른쪽</button>
    </div>
  );
}
