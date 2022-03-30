import React, { useState, useEffect } from "react";
import { useDispatch } from 'react-redux';
import { loadExistingPokemons } from "../redux/actions";
import isLogin from '../utils/isLogin';
import PokemonList from "./PokemonList";

import "./PokedexPage.scss";

export default function PokedexPage() {
  const dispatch = useDispatch();
  useEffect(() => {
    const jwt = isLogin();
    dispatch(loadExistingPokemons(jwt));
  }, []);

  const [page, setPage] = useState(1);
  
  const handleClickGoToPrev = () => {
    setPage(page - 1);
    if (page <= 1) {
      setPage(1);
    }
  };

  const handleClickGoToNext = () => {
    setPage(page + 1);
    if (page >= 26) {
      setPage(26);
    }
  };

  return (
    <div className="PokedexPage">
      <PokemonList
        page={page}
        onClickGoToPrev={handleClickGoToPrev}
        onClickGoToNext={handleClickGoToNext}
      />
    </div>
  );
}
