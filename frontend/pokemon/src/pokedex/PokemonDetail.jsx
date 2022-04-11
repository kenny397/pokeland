import React from "react";
import './PokemonNotFound.scss';
import pokemonList from '../fixtures/pokemonList';

import './PokemonDetail.scss';

export default function PokemonDetail({ nfpInModal }) {
  const { pokedexId, grade, ipfsImageUri } = nfpInModal;
  const pokemon = pokemonList[pokedexId - 1];  
  const {
    id,
    name, 
    type,
    height,
    category,
    weight,
    abilities,
  } = pokemon;

  const backgroundStyle = {
    backgroundImage: `url(/images/backgroundImg/${grade}.png)`
  };

  const colorByGradeTable = {
    Rare: 'red',
    Unique: 'purple',
    Epic: 'hotpink',
    Grass: 'green',
  };

  const fontStyle = {
    color: `${colorByGradeTable[grade] ? colorByGradeTable[grade] : grade}`
  };

  const imgPath = ipfsImageUri;
  return (
    <div className="pokemon-detail-container">
      <div className="pokedex-top">
        <div className="pokedex-pokemon-screen">
          <div className='pokemon-img-div' style={ backgroundStyle }>
            <img className='pokemon-img' src={imgPath} alt="pokemon-img" />
          </div>
        </div>
      </div>
      <div className="pokedex-bottom">
        <div className="pokedex-contents-screen">
          <div className="pokedex-content-item">
            <p className="title">{ id }</p>
            <p className="content pokemon-name">{ name }</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title"></p>
            <p className="content"></p>
          </div>
          <div className="pokedex-content-item">
            <p className="grade" style={ fontStyle }>[ { grade } ]</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title">타입</p>
            <p className="content">{ type }</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title">키</p>
            <p className="content">{ height }</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title">분류</p>
            <p className="content">{ category }</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title">몸무게</p>
            <p className="content">{ weight }</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title">성별</p>
            <p className="content">남, 녀</p>
          </div>
          <div className="pokedex-content-item">
            <p className="title">특성</p>
            <p className="content">{ abilities }</p>
          </div>
        </div>
      </div>
    </div>
  );
}
