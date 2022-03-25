import React from "react";
import { getImgPath, getPokemonNum } from "../utils/pokemonNum";

export default function PokemonItem({ item: { id, name } }) {
  const pokemonShadowImgPath = getImgPath(id, name);
  const pokemonNum = getPokemonNum(id);

  return (
    <div className="pokemon-item">
      <div className="pokemon-title">
        <div className="pokemon-num">
          <p>{pokemonNum}</p>
        </div>
        <div className="pokemon-name">
          <p>{name}</p>
        </div>
      </div>
      <img className="pokemon-img" src={pokemonShadowImgPath} alt="pokemonImg" />
      <div className="pokemon-copyright"><p>ⓒPokémon</p></div>
    </div>
  );
}
