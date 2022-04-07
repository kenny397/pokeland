import React from "react";

import './NfpList.scss';

export default function NfpItem({ onClickToggleModalVisibility, pokemonNum, pokemonName, nfpImgPath, grade }) {
  const backgroundStyle ={
    backgroundImage: `url(/images/backgroundImg/${grade}.png)`
  };
  console.log(backgroundStyle);
  
  return (
    <div className="pokemon-item" onClick={onClickToggleModalVisibility} style={ backgroundStyle }>
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
