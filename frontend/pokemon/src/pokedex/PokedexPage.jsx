import React, { useEffect } from "react";
import { useDispatch } from 'react-redux';
import { loadExistingPokemons } from "../redux/actions";
import { useNavigate, useParams } from "react-router-dom";
import { useMediaQuery } from "react-responsive";

import { changeHeaderDisplay } from "../headerDisplay";
import { pcSize, tabletSize, mobileSize } from '../utils/querys';
import { isLogin, generateLayout, calcMaxPage } from '../utils/utils';
import PokemonList from "./PokemonList";
import "./PokedexPage.scss";

export default function PokedexPage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  let { page } = useParams();
  page *= 1;
  const isPc = useMediaQuery(pcSize);
  const isTablet = useMediaQuery(tabletSize);
  const isMobile = useMediaQuery(mobileSize);
  const viewPort = { isPc, isTablet, isMobile };

  const layout = generateLayout(viewPort);
  const maxPage = calcMaxPage(151, layout.each);

  if (page > maxPage) {
    navigate(`/pokedex/${maxPage}`);
  }

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
