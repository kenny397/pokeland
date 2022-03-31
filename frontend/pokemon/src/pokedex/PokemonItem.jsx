import React from "react";
import { Link } from 'react-router-dom';

export default function PokemonItem({ pokemonNum, pokemonName, pokemonImgPath, to }) {
  return (
    <div className="pokemon-item">
      <Link to={to}>
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
      </Link>
    </div>
  );
}
