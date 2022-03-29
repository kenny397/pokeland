import React from "react";
import { useSelector } from "react-redux";

export default function NfpList({ pokedexId, page, onClickGoToPrev, onClickGoToNext }) {
  const nfpList = useSelector(state => state.nfps)[pokedexId+""];
  console.log(nfpList);
  const start = (page - 1) * 6;
  const end = page * 6;
  
  const paginatedNfpList = nfpList.slice(start, end);
  let emptyGridItems = [1, 2, 3, 4, 5, 6];
  for (let i = 0; i < paginatedNfpList.length; i++) {
    emptyGridItems.pop();
  }

  return (
    <div className="pokemon-list">
      {paginatedNfpList.map((item) => 
        JSON.stringify(item)
      )}

      {emptyGridItems.map((ele) => (
        <div className="empty-grid-item" key={ele}> </div>
      ))}
      <button className="prev-btn" onClick={onClickGoToPrev}>왼쪽</button>
      <button className="next-btn" onClick={onClickGoToNext}>오른쪽</button>
    </div>
  );
}
