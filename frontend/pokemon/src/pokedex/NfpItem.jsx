import React from "react";

export default function NfpItem({ pokemonNum, pokemonName, nfpImgPath }) {
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
      <img className="pokemon-img" src={nfpImgPath} alt="pokemonImg" />
      <div className="pokemon-copyright"><p>ⓒPokémon</p></div>
    </div>
  );
}
