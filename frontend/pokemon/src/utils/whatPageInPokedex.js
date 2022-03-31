const whatPageInPokedex = (pokedexId, each) => {
  return parseInt((pokedexId - 1) / each + 1);
};

export default whatPageInPokedex;
