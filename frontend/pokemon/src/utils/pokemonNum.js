export function getPokemonNum(id) {
  const str = "" + id;
  const pad = "000";
  const pokemonNum = pad.substring(0, pad.length - str.length) + str;
  return pokemonNum;
}

export function getImgPath(id, name, option) {
  const pokemonNum = getPokemonNum(id);
  const pokemonShadowImgPath =  `/images/${option}s/NO. ${pokemonNum}${name}.jpg`;
  return pokemonShadowImgPath;
}
