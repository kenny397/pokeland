import React from "react";
import pokemonList from "../fixtures/pokemonList";

import NfpItem from "./NfpItem";

export default function NfpList({ pokedexId, page, onClickGoToPrev, onClickGoToNext, existingNfps }) {
  const pokemonName = pokemonList[pokedexId - 1]["name"];
  const nfpList = existingNfps;
  console.log(nfpList);

  const start = (page - 1) * 6;
  const end = page * 6;
  const paginatedNfpList = nfpList ? nfpList.slice(start, end) : [];
  let emptyGridItems = [1, 2, 3, 4, 5, 6];
  for (let i = 0; i < paginatedNfpList.length; i++) {
    emptyGridItems.pop();
  }

  return (
    <div className="pokemon-list">
      {paginatedNfpList.map(({ pokedexId, ipfsImageUri }) => {
        let pokemonNum = (pokedexId+"").padStart(3, '0');
        return (
          <NfpItem
            pokemonNum={pokemonNum}
            pokemonName={pokemonName}
            nfpImgPath={ipfsImageUri}
            key={ipfsImageUri}
          />
        );
      }
      )}

      {emptyGridItems.map((ele) => (
        <div className="empty-grid-item" key={ele}> </div>
      ))}
      {
        nfpList &&
        <div className="prev-btn">
          <button onClick={onClickGoToPrev}>왼쪽</button>
        </div>
      }
      {
        nfpList &&
        <div className="next-btn">
          <button onClick={onClickGoToNext}>오른쪽</button>
        </div>
      }
    </div>
  );
}
