import React from "react";
import { useNavigate } from "react-router-dom";
import { useMediaQuery } from "react-responsive";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

import { pcSize, tabletSize, mobileSize } from '../utils/querys';
import { getImgPath, whatPageInPokedex } from "../utils/utils";
import pokemonList from "../fixtures/pokemonList";

import NfpItem from "./NfpItem";

export default function NfpList({ pokedexId, page, onClickGoToPrev, onClickGoToNext, existingNfps }) {
  const isPc = useMediaQuery(pcSize);
  const isTablet = useMediaQuery(tabletSize);
  const isMobile = useMediaQuery(mobileSize);
  const viewPort = { isPc, isTablet, isMobile };

  const nfpList = existingNfps;

  const start = (page - 1) * 6;
  const end = page * 6;
  const paginatedNfpList = nfpList ? nfpList.slice(start, end) : [];
  let emptyGridItems = [1, 2, 3, 4, 5, 6];
  for (let i = 0; i < paginatedNfpList.length; i++) {
    emptyGridItems.pop();
  }

  const navigate = useNavigate();
  const handleClickCloseNfps = () => {
    navigate(`/pokedex/${whatPageInPokedex(pokedexId, viewPort)}`);
  };

  return (
    <div className="pokemon-list">
      <div className="close-nfps-btn-div">
        <FontAwesomeIcon
          className="close-nfps-btn"
          icon={faXmark}
          onClick={handleClickCloseNfps}
        />
      </div>
      {paginatedNfpList.map(({ pokedexId, ipfsImageUri, grade }) => {
        let pokemonNum = (pokedexId+"").padStart(3, '0');
        const pokemonName = pokemonList[pokedexId - 1]["name"];
        const nfpImgPath = getImgPath(pokedexId, 'colored');
        return (
          <NfpItem
            pokemonNum={pokemonNum}
            pokemonName={pokemonName}
            nfpImgPath={nfpImgPath}
            grade={grade}
            key={ipfsImageUri}
          />
        );
      }
      )}

      {emptyGridItems.map((ele) => (
        <div className="empty-grid-item" key={ele}> </div>
      ))}
      {
        nfpList.length > 6 && 
        <div className="prev-btn">
          <button onClick={onClickGoToPrev}>왼쪽</button>
        </div>
      }
      {
        nfpList.length > 6 && paginatedNfpList.length === 6 &&
        <div className="next-btn">
          <button onClick={onClickGoToNext}>오른쪽</button>
        </div>
      }
    </div>
  );
}
