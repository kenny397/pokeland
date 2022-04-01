import React, { useState } from "react";
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import { loadExistingNfps } from "../redux/actions";

import NfpList from "./NfpList";
import PokemonNotFound from "./PokemonNotFound";

export default function NfpsByPokemon() {
  const dispatch = useDispatch();
  const { pokedexId } = useParams();
  const publicKey = localStorage.getItem('publicKey');

  useEffect(() => {
    dispatch(loadExistingNfps(publicKey, pokedexId));
  }, []);

  const existingNfps = useSelector(state => state.existingNfps);

  const numOfNfps =  existingNfps ? existingNfps.length : 0;
  const [page, setPage] = useState(1);
  
  const handleClickGoToPrev = () => {
    setPage(page - 1);
    if (page <= 1) {
      setPage(1);
    }
  };

  const handleClickGoToNext = () => {
    const maxPage = Math.ceil(numOfNfps / 6);
    setPage(page + 1);
    if (page >= maxPage) {
      setPage(maxPage);
    }
  };

  return(
    existingNfps
      ?
      <div className="pokemon-list">
        <div className="PokedexPage">
          <NfpList
            pokedexId={pokedexId}
            page={page}
            existingNfps={existingNfps}
            onClickGoToPrev={handleClickGoToPrev}
            onClickGoToNext={handleClickGoToNext}
          />
        </div>
      </div>
      :
      <PokemonNotFound pokedexId={pokedexId}/>
  );
}
