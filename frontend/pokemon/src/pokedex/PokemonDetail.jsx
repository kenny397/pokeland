import React from "react";
import './PokemonNotFound.scss';
import { getImgPath } from '../utils/utils';
import pokemonList from '../fixtures/pokemonList';

import './PokemonDetail.scss';

export default function PokemonDetail({ nfpInModal }) {
  const { pokedexId, grade } = nfpInModal;
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

  const imgPath = getImgPath(id, 'colored');
  return (
    <div className="pokemon-detail-container">
      <div className="pokedex-top">
        <div className="pokedex-pokemon-screen">
          <div className='pokemon-img-div'>
            <img className='pokemon-img' src={imgPath} alt="pokemon-img" />
          </div>
        </div>
      </div>
      <div className="pokedex-bottom">
        <div className="pokedex-contents-screen">
          <div className="pokedex-content-item">
            <p>{ id }</p>
            <p>{ name }</p>
          </div>
          <div className="pokedex-content-item">
            <p>[{ grade }]</p>
          </div>
          <div className="pokedex-content-item">
            <p>타입</p>
            <p>{ type }</p>
          </div>
          <div className="pokedex-content-item">
            <p>키</p>
            <p>{ height }</p>
          </div>
          <div className="pokedex-content-item">
            <p>분류</p>
            <p>{ category }</p>
          </div>
          <div className="pokedex-content-item">
            <p>몸무게</p>
            <p>{ weight }</p>
          </div>
          <div className="pokedex-content-item">
            <p>특성</p>
            <p>{ abilities }</p>
          </div>
        </div>
      </div>
    </div>
  );
}
