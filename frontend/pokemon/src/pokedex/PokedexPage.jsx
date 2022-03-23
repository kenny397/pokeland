import React, { useState } from "react";
import PokemonList from "./PokemonList";

export default function PokedexPage() {
  const [page, setPage] = useState(1);
  
  const onClickGoToPrev = () => {
    setPage(page - 1);
    if (page <= 1) {
      setPage(1);
    }
  };

  const onClickGoToNext = () => {
    setPage(page + 1);
    if (page >= 26) {
      setPage(26);
    }
  };

  return (
    <>
      <h1>Pokedex</h1>
      <PokemonList 
        page={page}
      />
      <button onClick={onClickGoToPrev}>왼쪽</button>
      <button onClick={onClickGoToNext}>오른쪽</button>
    </>
  );
}
