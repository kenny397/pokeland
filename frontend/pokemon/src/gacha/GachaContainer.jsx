import React from "react";
import { useState } from "react";
import pokemonList from "../fixtures/pokemonList";

import GachaPage from "./GachaPage";

export default function GachaContainer() {
  const [pokeballDisplay, setPokeballDisplay] = useState(false);
  const [drawnPokemon, setDrawnPokemon] = useState(null);

  const handleClickGetPokemon = () => {
    setPokeballDisplay(true);
  };
  
  const handleClickOpenPokeball = () => {
    const randomIdx = Math.round(Math.random() * 10000) % 151;
    console.log(randomIdx);
    setPokeballDisplay(false);
    setDrawnPokemon(pokemonList[randomIdx]);
  };
  
  const handleClickGoBackToGacha = () => {
    setDrawnPokemon(null);
  };
  
  return (
    <GachaPage
      pokeballDisplay={pokeballDisplay}
      drawnPokemon={drawnPokemon}
      onClickGetPokemon={handleClickGetPokemon}
      onClickOpenPokeball={handleClickOpenPokeball}
      onClickGoBackToGacha={handleClickGoBackToGacha}
    />
  );
}
