import React, { useState } from "react";
import PokemonList from "./PokemonList";
import "./PokedexPage.css";

export default function PokedexPage() {
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
    <div>
      <PokemonList
        page={page}
        onClickGoToPrev={handleClickGoToPrev}
        onClickGoToNext={handleClickGoToNext}
      />
    </div>
  );
}
