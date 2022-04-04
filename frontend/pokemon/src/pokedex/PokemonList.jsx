import React from "react";
import { useMediaQuery } from "react-responsive";
// import { useNavigate } from "react-router-dom";
import pokemonList from "../fixtures/pokemonList";
import { pcSize, tabletSize, mobileSize } from "../utils/querys";

import range from '../utils/range';
// import whatPageInPokedex from '../utils/whatPageInPokedex';

import PokemonItemContainer from "./PokemonItemContainer";

export default function PokemonList({ page, onClickGoToPrev, onClickGoToNext }) {
  // localStorage.getItem('prevSize');
  // console.log(JSON.parse(localStorage.getItem('prevSize')));

  const isPc = useMediaQuery(pcSize);
  const isTablet = useMediaQuery(tabletSize);
  const isMobile = useMediaQuery(mobileSize);

  // localStorage.setItem('prevSize', JSON.stringify({ isPc, isTablet, isMobile }));
  // console.log(JSON.parse(localStorage.getItem('prevSize')));

  const generateLayout = (columns) => {
    return {
      each: columns * 3,
      gridTemplateColumns: '1fr '.repeat(columns).slice(0, -1),
      gridColumn: `${columns}/${columns + 1}`,
      gridTemplateRows: '1fr '.repeat(4).slice(0, -1),
    };
  };

  let layout = generateLayout(2);
  let columns = 2;

  if (isMobile) {
    columns = 2;
  } else if (isTablet) {
    columns = 4;
  } else if (isPc) {
    columns = 6;
  }

  layout = generateLayout(columns);
  let { each, gridTemplateColumns, gridTemplateRows, gridColumn } = layout;

  // let firstPokemonHere = ((page - 1) * each) + 1;
  // const navigate = useNavigate();

  // let curPage = page;
  // let newPage = whatPageInPokedex(firstPokemonHere, each);

  // if (page !== newPage) {
  //   navigate(`/pokedex/${newPage}`);
  // }

  const start = (page - 1) * each;
  const end = page * each;
  
  const paginatedPokemonList = pokemonList.slice(start, end);
  let emptyGridItems = range(1, each);
  for (let i = 0; i < paginatedPokemonList.length; i++) {
    emptyGridItems.pop();
  }

  return (
    <div className="pokemon-list" style={{ gridTemplateColumns, gridTemplateRows }}>
      {paginatedPokemonList.map((item) => (
        <PokemonItemContainer
          item={item}
          key={item.id}
        />
      ))}

      {emptyGridItems.map((ele) => (
        <div className="empty-grid-item" key={ele}> </div>
      ))}
      {
        pokemonList.length > each &&
        <button className="prev-btn" onClick={() => onClickGoToPrev()}>왼쪽</button>
      }
      {
        paginatedPokemonList.length == each &&
        <button className="next-btn" style={{ gridColumn }} onClick={() => onClickGoToNext()}>오른쪽</button>
      }
    </div>
  );
}
