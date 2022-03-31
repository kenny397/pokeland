import React from "react";
import getImgPath from "../utils/getImgPath";
import "./GachaPage.scss";
import { useSelector } from "react-redux";
import { Link } from 'react-router-dom';

import Confetti from 'react-confetti';
import whatPageInPokedex from "../utils/whatPageInPokedex";

export default function GachaPage({
  pokeballDisplay,
  drawnPokemon,
  grade,
  onClickGetPokemon,
  onClickOpenPokeball,
  onClickGoBackToGacha,
  loading,
  confetti,
  isDeskTop
}) {

  let pokemonImgPath = null;
  let pokedexId = '';

  if (drawnPokemon) {
    const { id } = drawnPokemon;
    pokedexId = id;
    pokemonImgPath = getImgPath(id, 'colored');
  }

  const { balance } = useSelector(state => ({
    balance: state.balance
  }));

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
            <span>내 자산: </span><span>{balance} SSF</span>
          </div>
        </div>
        :
        <div className="gacha-container">
          <img className="drawn-pokemon-img" src={pokemonImgPath} alt="뽑은 포켓몬 이미지" />
          <div className="pokemon-name-wrapper">{drawnPokemon.name}<span className="grade-wrapper">{` [${grade}]`}</span></div>
          <div>
            <button className="re-gacha-btn" onClick={onClickGoBackToGacha}>다시 뽑기</button>
            <Link to={`/pokedex/${whatPageInPokedex(pokedexId, 6)}`}>
              <button className="pokedex-btn">도감 가기</button>
            </Link>
          </div>
        </div>
      }
      {loading &&
        <>
          <div className="body-blackout"/>
          <img src="/images/static/pokemonStickerGif/monsterball.gif" alt="" className="pokeball-spinning-img"/> 
        </>
      }
      { confetti &&
        <Confetti
          width={ isDeskTop ? 1030 : window.innerWidth}
          height={isDeskTop ? window.innerHeight-80 : window.innerHeight}
          numberOfPieces={200}
          gravity={ isDeskTop ? 0.03 : 0.07}
        />
      }
      
    </div>
  );
}
