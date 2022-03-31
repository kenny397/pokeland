import React from "react";
import { useSelector } from "react-redux";
import getImgPath from "../utils/getImgPath";
import { Link } from 'react-router-dom';

import PokemonItem from "./PokemonItem";

export default function PokemonItemContainer({ item }) {
  const { id, name } = item;
  const hasNfps = useSelector(state => state.existingPokemons).find(ele => ele === id);
  
  const pokemonShadowImgPath = getImgPath(id, 'shadow');
  const pokemonColorImgPath = getImgPath(id, 'colored');
  const pokemonNum = (id + "").padStart(3, '0');

  return (
    <Link to={`/pokedex/nfps/${item.id}`}>
      <PokemonItem
        pokemonNum={pokemonNum}
        pokemonName={name}
        pokemonImgPath={hasNfps ? pokemonColorImgPath : pokemonShadowImgPath}
        key={item['id']}
      />
    </Link>
  );
}
