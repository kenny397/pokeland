import React from "react";
import { useMediaQuery } from "react-responsive";
import { pcSize, tabletSize, mobileSize } from "../utils/querys";
import { range, generateLayout } from '../utils/utils';

import PokemonItemContainer from "./PokemonItemContainer";

export default function PokemonList({ pokemonList, page, onClickGoToPrev, onClickGoToNext }) {
  const isPc = useMediaQuery(pcSize);
  const isTablet = useMediaQuery(tabletSize);
  const isMobile = useMediaQuery(mobileSize);
  const viewPort = { isPc, isTablet, isMobile };

  let layout = generateLayout(viewPort);
  let { each, gridTemplateColumns, gridTemplateRows, gridColumn } = layout;

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
        pokemonList.length > each && page != 1 &&
        <button className="prev-btn" onClick={() => onClickGoToPrev()}>왼쪽</button>
      }
      {
        paginatedPokemonList.length == each && 
        pokemonList.slice(start + each, end + each) != 0 &&
        <button className="next-btn" style={{ gridColumn }} onClick={() => onClickGoToNext()}>오른쪽</button>
      }
    </div>
  );
}
