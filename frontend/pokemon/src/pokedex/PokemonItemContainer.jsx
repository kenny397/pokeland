import React from "react";
import { useSelector } from "react-redux";
import getImgPath from "../utils/getImgPath";
import { useNavigate } from 'react-router-dom';

import PokemonItem from "./PokemonItem";

export default function PokemonItemContainer({ item }) {
  const { id, name } = item;
  const hasNfps = useSelector(state => state.existingPokemons).find(ele => ele === id);
  
  const pokemonShadowImgPath = getImgPath(id, 'shadow');
  const pokemonColorImgPath = getImgPath(id, 'colored');
  const pokemonNum = (id + "").padStart(3, '0');

  const navigate = useNavigate();

  const handleClickPokemonCard = () => {
    if (!hasNfps) {
      alert(`보유한 ${name}이/가 없습니다!`);
    } else {
      navigate(`/pokedex/nfps/${item.id}`);
    }
  };

  // const test = () => {
  //   console.log('click');
  // };

  return (
    <PokemonItem
      pokemonNum={pokemonNum}
      pokemonName={name}
      pokemonImgPath={hasNfps ? pokemonColorImgPath : pokemonShadowImgPath}
      onClick={() => handleClickPokemonCard()}
      key={item['id']}
    />
  );
}
