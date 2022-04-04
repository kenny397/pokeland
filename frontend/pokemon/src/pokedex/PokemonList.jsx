import React from "react";
import { useMediaQuery } from "react-responsive";
import pokemonList from "../fixtures/pokemonList";

import range from '../utils/range';

import PokemonItemContainer from "./PokemonItemContainer";

export default function PokemonList({ page, onClickGoToPrev, onClickGoToNext }) {
  const isPc = useMediaQuery({ query : "(min-width:1024px)" });
  const isTablet = useMediaQuery({ query : "(min-width:768px) and (max-width:1023px)" });
  const isMobile = useMediaQuery({ query : "(max-width:600px)" });

  const generateLayout = (columns) => {
    return {
      each: columns * 3,
      gridTemplateColumns: '1fr '.repeat(columns).slice(0, -1),
      gridColumn: `${columns}/${columns + 1}`,
    };
  };

  let layout = generateLayout(2);

  if (isMobile) {
    layout = generateLayout(2);
  } else if (isTablet) {
    layout = generateLayout(4);
  } else if (isPc) {
    layout = generateLayout(6);
  }

  let { each, gridTemplateColumns, gridColumn } = layout;

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
