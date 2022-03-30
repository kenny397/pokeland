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

// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aGtpbUBzc2FmeS5jb20iLCJpc3MiOiJzc2FmeS5jb20iLCJleHAiOjE2NDg3Mzg2MzQsImlhdCI6MTY0ODY1MjIzNH0.OCoJ8NH70e9v1o1M50W3EuCqNxb7rDanpmel0MNBy9OeGFL15h4E2KN-dOq3V2b8wABhlRC5Fbgv8OzQFIpvdg
