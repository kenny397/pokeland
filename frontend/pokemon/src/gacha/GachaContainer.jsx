import React from "react";
import { useState, useEffect } from "react";
import pokemonList from "../fixtures/pokemonList";
import { useSelector, useDispatch } from "react-redux";

import GachaPage from "./GachaPage";

import { doGacha, getBalance } from "../api";
import { updateBalance } from "../redux/actions";

import { useMediaQuery } from "react-responsive";

import { changeHeaderDisplay } from "../headerDisplay";
import { setGachaOrder } from "../redux/actions";

export default function GachaContainer() {
  const dispatch = useDispatch();
  const [pokeballDisplay, setPokeballDisplay] = useState(false);
  const [drawnPokemon, setDrawnPokemon] = useState(null);
  const [grade, setGrade] = useState(null);
  const [loading, setLoading] = useState(null);
  const [confetti, setConfetti] = useState(null);

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
    dispatch(setGachaOrder(0));
  }, []);

  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  const { balance } = useSelector(state => ({
    balance: state.balance
  }));

  const handleClickGetPokemon = () => {
    if (balance >= 100) {
      setPokeballDisplay(true);
    } else {
      alert("자산이 부족합니다. \n자산을 더 얻으시려면 \n고객센터에서 리뷰를 작성해주세요.");
    }
  };
  
  const handleClickOpenPokeball = async () => {
    dispatch(setGachaOrder(1));
    setLoading(true);
    const { data: { grade, pokeDexId } } = await doGacha();
    const response = await getBalance();
    localStorage.setItem('balance', response.data.money);
    dispatch(updateBalance(localStorage.getItem('balance')));
    
    setPokeballDisplay(false);
    if (grade === 'Epic') {
      dispatch(setGachaOrder(4));  
    } else if (grade === 'Unique') {
      dispatch(setGachaOrder(3));
    } else {
      dispatch(setGachaOrder(2));
    }
    setTimeout(() => { setDrawnPokemon(pokemonList[pokeDexId-1]); setLoading(false); setGrade(grade); setConfetti(true); }, 100);
    
  };
  
  const handleClickGoBackToGacha = () => {
    dispatch(setGachaOrder(0));
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
      isDeskTop={isDeskTop}
      onClickGetPokemon={handleClickGetPokemon}
      onClickOpenPokeball={handleClickOpenPokeball}
      onClickGoBackToGacha={handleClickGoBackToGacha}
    />
  );
}
