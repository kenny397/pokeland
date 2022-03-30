import React from "react";
import { useState } from "react";
import pokemonList from "../fixtures/pokemonList";
import { useSelector, useDispatch } from "react-redux";

import GachaPage from "./GachaPage";

import { doGacha, getBalance } from "../api";
import { updateBalance } from "../redux/actions";

export default function GachaContainer() {
  const dispatch = useDispatch();
  const [pokeballDisplay, setPokeballDisplay] = useState(false);
  const [drawnPokemon, setDrawnPokemon] = useState(null);
  const [grade, setGrade] = useState(null);
  const [loading, setLoading] = useState(null);
  const [confetti, setConfetti] = useState(null);

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
    const { data: { grade, pokeDexId } } = await doGacha();
    const response = await getBalance();
    localStorage.setItem('balance', response.data.money);
    dispatch(updateBalance(localStorage.getItem('balance')));
    
    setPokeballDisplay(false);
    setLoading(true);
    setTimeout(() => { setDrawnPokemon(pokemonList[pokeDexId-1]); setLoading(false); setGrade(grade); setConfetti(true); }, 2000);
    
  };
  
  const handleClickGoBackToGacha = () => {
    setDrawnPokemon(null);
    setGrade(null);
    setConfetti(false);
  };
  
  return (
    <GachaPage
      pokeballDisplay={pokeballDisplay}
      drawnPokemon={drawnPokemon}
      grade={grade}
      loading={loading}
      confetti={confetti}
      onClickGetPokemon={handleClickGetPokemon}
      onClickOpenPokeball={handleClickOpenPokeball}
      onClickGoBackToGacha={handleClickGoBackToGacha}
    />
  );
}
