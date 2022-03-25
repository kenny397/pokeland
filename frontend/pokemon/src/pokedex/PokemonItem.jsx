import React from "react";

export default function PokemonItem({ item: { id, name } }) {
  const str = "" + id;
  const pad = "000";
  const pokemonNum = pad.substring(0, pad.length - str.length) + str;
  const pokemonShadowImgPath =  `/images/shadows/NO. ${pokemonNum}${name}.jpg`;

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
