import React from "react";
import { useSelector } from "react-redux";
import getImgPath from "../utils/getImgPath";

import PokemonItem from "./PokemonItem";

export default function PokemonItemContainer({ item }) {
  const { id, name } = item;
  const hasNfps = useSelector(state => state.nfps)[id];
  
  const pokemonShadowImgPath = getImgPath(id, name, 'shadow');
  const pokemonColorImgPath = getImgPath(id, name, 'pokemon');
  const pokemonNum = (id + "").padStart(3, '0');

  return (
    <PokemonItem
      pokemonNum={pokemonNum}
      pokemonName={name}
      pokemonImgPath={hasNfps ? pokemonColorImgPath : pokemonShadowImgPath}
      key={item['id']}
    />
  );
}
