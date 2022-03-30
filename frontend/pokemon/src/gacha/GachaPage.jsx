import React from "react";
import getImgPath from "../utils/getImgPath";
import "./GachaPage.scss";

export default function GachaPage({
  pokeballDisplay,
  drawnPokemon,
  grade,
  onClickGetPokemon,
  onClickOpenPokeball,
  onClickGoBackToGacha,
}) {
  
  let pokemonImgPath = null;
  if (drawnPokemon) {
    const { id } = drawnPokemon;
    pokemonImgPath = getImgPath(id, 'colored');
  }

  const backgroundStyle ={
    backgroundImage: `url(/images/backgroundImg/${grade}.png)`
  };
  
  return (
    <div className="GachaPage" style={ backgroundStyle }>
      {!drawnPokemon ?
        <div className="gacha-container">
          
          <h1 className="header-text">포켓몬 뽑기</h1>
          <img className="gacha-img" src="/images/static/gacha.png" alt="뽑기기계이미지" />
          {pokeballDisplay && 
            <img className="pokeball-img" src="/images/static/pokeball.png" alt="몬스터볼" />
          }
          {!pokeballDisplay && 
            <>
              <button onClick={onClickGetPokemon}>포켓몬 뽑기</button>
              <p className="decrease-ssf">-100 SSF</p>
            </>
          }
          {pokeballDisplay &&
            <button 
              className="open-pokeball-btn" 
              onClick={onClickOpenPokeball}
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
          <button onClick={onClickGoBackToGacha}>다시 뽑기</button>
        </div>
      }
    </div>
  );
}
