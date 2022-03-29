import React from "react";
import { useParams } from "react-router-dom";

export default function NfpsByPokemon() {
  const { pokedexId } = useParams();
  return(
    <div>
      <h1>nfp리스트 by pokemon</h1>
      <p>{ pokedexId }</p>
    </div>
  );
}
