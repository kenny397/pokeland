import React from "react";

export default function PokemonItem({ item: { id, name } }) {
  const str = "" + id;
  const pad = "000";
  const pokemonNum = pad.substring(0, pad.length - str.length) + str;
  const pokemonShadowImgPath =  `/images/shadows/NO. ${pokemonNum}${name}.jpg`;

  return (
    <>
      <p>NO. {pokemonNum} {name}</p>
      <img src={pokemonShadowImgPath} alt="pokemonImg" width="150px" />
    </>
  );
}
