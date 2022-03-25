import React from "react";
import { useState } from "react";
import { getImgPath } from "../utils/pokemonNum";

import "./GachaPage.css";

export default function GachaPage() {
  const [pokeballDisplay, setPokeballDisplay] = useState(false);
  const [drawnPokemon, setDrawnPokemon] = useState(null);

  const handleClickGetPokemon = () => {
    setPokeballDisplay(true);
  };
  
  const handleClickOpenPokeball = () => {
    setPokeballDisplay(false);
    setDrawnPokemon({
      "id": 127,
      "name": "쁘사이저",
      "type": "벌레",
      "height": "1.5m",
      "category": "뿔집게포켓몬",
      "gender": "",
      "weight": "55.0kg",
      "abilities": "괴력집게"
    });
  };

  const handleClickGoBackToGacha = () => {
    setDrawnPokemon(null);
  };

  let pokemonImgPath = null;
  if (drawnPokemon) {
    const { id, name } = drawnPokemon;
    pokemonImgPath = getImgPath(id, name, 'pokemon');
  }

  return (
    <>
      {!drawnPokemon ?
        <div className="gacha-container">
          
          <h1 className="header-text">포켓몬 뽑기</h1>
          <img className="gacha-img" src="/images/static/gacha.png" alt="뽑기기계이미지" />
          {pokeballDisplay && 
            <img className="pokeball-img" src="/images/static/pokeball.png" alt="몬스터볼" />
          }
          {!pokeballDisplay && 
            <button onClick={handleClickGetPokemon}>포켓몬 뽑기</button>
          }
          {pokeballDisplay &&
            <button 
              className="open-pokeball-btn" 
              onClick={handleClickOpenPokeball}
            >
              몬스터볼 열기
            </button>
          }
          <div className="balance-div">
            <span>내 자산: </span><span>1000.0 SSF</span>
          </div>
        </div>
        :
        <div className="gacha-container">
          <img className="drawn-pokemon-img" src={pokemonImgPath} alt="뽑은 포켓몬 이미지" />
          <h2>{drawnPokemon.name}</h2>
          <button onClick={handleClickGoBackToGacha}>다시 뽑기</button>
        </div>
      }
    </>
  );
}
