import React from "react";

export default function PokemonItem({ pokemonNum, pokemonName, pokemonImgPath }) {

  return (
    <div className="pokemon-item">
      <div className="pokemon-title">
        <div className="pokemon-num">
          <p>{pokemonNum}</p>
        </div>
        <div className="pokemon-name">
          <p>{pokemonName}</p>
        </div>
      </div>
      <img className="pokemon-img" src={pokemonImgPath} alt="pokemonImg" />
      <div className="pokemon-copyright"><p>ⓒPokémon</p></div>
    </div>
  );
}
