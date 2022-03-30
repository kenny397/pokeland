import React from "react";
import { useState, useEffect } from "react";
import pokemonList from "../fixtures/pokemonList";
import { useSelector } from "react-redux";

import GachaPage from "./GachaPage";

import { doGacha } from "../api";

export default function GachaContainer() {
  const [pokeballDisplay, setPokeballDisplay] = useState(false);
  const [drawnPokemon, setDrawnPokemon] = useState(null);
  const [grade, setGrade] = useState(null);

  const { balance } = useSelector(state => ({
    balance: state.balance
  }));

  const handleClickGetPokemon = () => {
    if (balance >= 100) {
      setPokeballDisplay(true);
    } else {
      alert("자산이 부족합니다");
    }
  };
  
  const handleClickOpenPokeball = async () => {
    const { data: { grade, ipfsImageUri, pokeDexId } } = await doGacha();
    console.log(grade, ipfsImageUri, pokeDexId);
    
    setPokeballDisplay(false);
    setDrawnPokemon(pokemonList[pokeDexId-1]);
    setGrade(grade);
  };
  
  const handleClickGoBackToGacha = () => {
    setDrawnPokemon(null);
    setGrade(null);
  };
  
  return (
    <GachaPage
      pokeballDisplay={pokeballDisplay}
      drawnPokemon={drawnPokemon}
      grade={grade}
      onClickGetPokemon={handleClickGetPokemon}
      onClickOpenPokeball={handleClickOpenPokeball}
      onClickGoBackToGacha={handleClickGoBackToGacha}
    />
  );
}
