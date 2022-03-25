export default function getImgPath(id, name, option) {
  const pokemonNum = (id + "").padStart(3, '0');
  const pokemonShadowImgPath =  `/images/${option}s/NO. ${pokemonNum}${name}.jpg`;
  return pokemonShadowImgPath;
}
