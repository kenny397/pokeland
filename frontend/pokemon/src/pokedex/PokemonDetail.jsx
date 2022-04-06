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
          <p>{ id }</p>
          <p>{ name }</p>
          <p>[{ grade }]</p>
          <p>{ type }</p>
          <p>{ height }</p>
          <p>{ category }</p>
          <p>{ weight }</p>
          <p>{ abilities }</p>
        </div>
      </div>
    </div>
  );
}
