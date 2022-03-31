import React from "react";
import { useSelector } from "react-redux";
import getImgPath from "../utils/getImgPath";
import PokemonItem from "./PokemonItem";

export default function PokemonItemContainer({ item }) {
  const { id, name } = item;
  const hasNfps = useSelector(state => state.existingPokemons).find(ele => ele === id);
  
  const pokemonShadowImgPath = getImgPath(id, 'shadow');
  const pokemonColorImgPath = getImgPath(id, 'colored');
  const pokemonNum = (id + "").padStart(3, '0');
  const toNfps = `nfps/${id}`;
  const toPokemonNotFound = `pokemon-not-found/${id}`;
  return (
    <PokemonItem
      pokemonNum={pokemonNum}
      pokemonName={name}
      pokemonImgPath={hasNfps ? pokemonColorImgPath : pokemonShadowImgPath}
      to={hasNfps ? toNfps : toPokemonNotFound}
      key={item['id']}
    />
  );
}
