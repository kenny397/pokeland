import React, { useState } from "react";
import { useParams } from "react-router-dom";

import NfpList from "./NfpList";

export default function NfpsByPokemon() {
  const { pokedexId } = useParams();

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

  return(
    <div>
      <h1>nfp리스트 by pokemon</h1>
      <p>{ pokedexId }</p>
      <div className="PokedexPage">
        <NfpList
          pokedexId={pokedexId}
          page={page}
          onClickGoToPrev={handleClickGoToPrev}
          onClickGoToNext={handleClickGoToNext}
        />
      </div>
    </div>
  );
}
