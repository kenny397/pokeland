import React, { useEffect } from "react";
import { useDispatch } from 'react-redux';
import { loadExistingPokemons } from "../redux/actions";
import isLogin from '../utils/isLogin';
import PokemonList from "./PokemonList";
import { useNavigate, useParams } from "react-router-dom";
import { changeHeaderDisplay } from "../headerDisplay";

import "./PokedexPage.scss";

export default function PokedexPage() {
  let { page } = useParams();
  page *= 1;
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
    const jwt = isLogin();
    dispatch(loadExistingPokemons(jwt));
  }, []);

  const handleClickGoToPrev = () => {
    navigate(`/pokedex/${page - 1}`, { replace: true });
    if (page <= 1) {
      navigate(`/pokedex/${1}`, { replace: true });
    }
  };

  const handleClickGoToNext = () => {
    navigate(`/pokedex/${page + 1}`, { replace: true });
    if (page >= 26) {
      navigate(`/pokedex/${26}`, { replace: true });
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
