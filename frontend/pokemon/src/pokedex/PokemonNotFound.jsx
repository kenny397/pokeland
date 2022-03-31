import React from "react";
import { Link, useParams } from 'react-router-dom';
import './PokemonNotFound.scss';
import pokemonList from '../fixtures/pokemonList';

export default function PokemonNotFound() {
  const { pokedexId } = useParams();
  const pokemonName = pokemonList[pokedexId - 1]["name"];
  return (
    <div className="not-found-container">
      <p>보유한 {pokemonName}이/가 없습니다.</p>
      <img className='crying-pikachu' src='/images/static/pokemonStickerGif/picachucrying2.gif' alt="" />
      <Link to="/pokedex">
        <button>뒤로</button>
      </Link>
    </div>
  );
}
