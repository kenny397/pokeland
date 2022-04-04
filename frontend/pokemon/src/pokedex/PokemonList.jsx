import React from "react";
import { useMediaQuery } from "react-responsive";
import pokemonList from "../fixtures/pokemonList";

import range from '../utils/range';

import PokemonItemContainer from "./PokemonItemContainer";

export default function PokemonList({ page, onClickGoToPrev, onClickGoToNext }) {
  const isPc = useMediaQuery({
    query : "(min-width:1024px)"
  });
  const isTablet = useMediaQuery({
    query : "(min-width:768px) and (max-width:1023px)"
  });
  const isMobile = useMediaQuery({
    query : "(max-width:600px)"
  });
  const layOut = {
    each: 6,
    gridTemplateColumns: '1fr 1fr',
    gridColumn: '2/3',
  };

  const setLayout = (isPc, isTablet, isMobile) => {
    if (isMobile) {
      layOut.gridTemplateColumns = '1fr 1fr';
      layOut.each = 6;
      layOut.gridColumn = `${layOut.gridTemplateColumns.split(' ').length}/${layOut.gridTemplateColumns.split(' ').length + 1}`;
    } else if (isTablet) {
      layOut.gridTemplateColumns = '1fr 1fr 1fr';
      layOut.each = 9;
      layOut.gridColumn = `${layOut.gridTemplateColumns.split(' ').length}/${layOut.gridTemplateColumns.split(' ').length + 1}`;
    } else if (isPc) {
      layOut.gridTemplateColumns = '1fr 1fr 1fr 1fr 1fr';
      layOut.each = 15;
      layOut.gridColumn = `${layOut.gridTemplateColumns.split(' ').length}/${layOut.gridTemplateColumns.split(' ').length + 1}`;
    }
    return layOut;
  };

  let { each, gridTemplateColumns, gridColumn } = setLayout(isPc, isTablet, isMobile);

  const start = (page - 1) * each;
  const end = page * each;
  
  const paginatedPokemonList = pokemonList.slice(start, end);
  let emptyGridItems = range(1, each);
  for (let i = 0; i < paginatedPokemonList.length; i++) {
    emptyGridItems.pop();
  }

  return (
    <div className="pokemon-list" style={{ gridTemplateColumns }}>
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
