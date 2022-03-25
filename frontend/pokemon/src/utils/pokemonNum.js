export function getPokemonNum(id) {
  const str = "" + id;
  const pad = "000";
  const pokemonNum = pad.substring(0, pad.length - str.length) + str;
  return pokemonNum;
}

export function getImgPath(id, name) {
  const pokemonNum = getPokemonNum(id);
  const pokemonShadowImgPath =  `/images/shadows/NO. ${pokemonNum}${name}.jpg`;
  return pokemonShadowImgPath;  
}
