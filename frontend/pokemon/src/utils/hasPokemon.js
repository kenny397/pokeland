import { useSelector } from "react-redux";

const hasPokemon = (pokedexId) => useSelector(state => state.existingPokemons).find(ele => ele === pokedexId);

export default hasPokemon;
