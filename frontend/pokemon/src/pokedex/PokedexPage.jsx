import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { loadExistingPokemons } from "../redux/actions";
import { useNavigate, useParams } from "react-router-dom";
import { useMediaQuery } from "react-responsive";

import { changeHeaderDisplay } from "../headerDisplay";
import { pcSize, tabletSize, mobileSize } from '../utils/querys';
import { isLogin, generateLayout, calcMaxPage } from '../utils/utils';
import { default as pokemons } from "../fixtures/pokemonList";

import PokemonList from "./PokemonList";

import "./PokedexPage.scss";

export default function PokedexPage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
    const jwt = isLogin();
    dispatch(loadExistingPokemons(jwt));
  }, []);

  const isPc = useMediaQuery(pcSize);
  const isTablet = useMediaQuery(tabletSize);
  const isMobile = useMediaQuery(mobileSize);
  const viewPort = { isPc, isTablet, isMobile };
  const layout = generateLayout(viewPort);

  let pokemonList = pokemons;
  const existingPokemons = useSelector(state => state.existingPokemons);
  const [ showOnlyMyPokemons, setShowOnlyMyPokemons ] = useState(false);

  if (showOnlyMyPokemons) {
    const myPokemonList = pokemonList.filter(
      pokemon => existingPokemons.find(ele => ele == pokemon.id)
    );
    pokemonList = myPokemonList;
  }
  
  let { page } = useParams();
  page *= 1;
  
  const maxPage = calcMaxPage(pokemonList.length, layout.each);
  if (page > maxPage) {
    navigate(`/pokedex/${maxPage}`);
  }

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
      <div className="filter-wrapper">
        <span>가지고 있는 포켓몬만 보기</span>
        <input type="checkbox" name="MyPokemonCheckbox" id="MyPokemonCheckbox" className="filter-input"
          onChange={() => setShowOnlyMyPokemons(!showOnlyMyPokemons)}
        />
      </div>
      <PokemonList
        pokemonList={pokemonList}
        page={page}
        onClickGoToPrev={handleClickGoToPrev}
        onClickGoToNext={handleClickGoToNext}
        showOnlyMyPokemons={showOnlyMyPokemons}
      />
    </div>
  );
}
