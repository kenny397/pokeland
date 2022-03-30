import { fetchExistingPokemons } from '../api';

export function updateHeaderDisplay(headerDisplay) {
  return {
    type: 'updateHeaderDisplay',
    payload: {
      headerDisplay,
    },
  };
}

export function updateJwt(jwt) {
  return {
    type: 'updateJwt',
    payload: {
      jwt,
    },
  };
}

export function updateBalance(balance) {
  return {
    type: 'updateBalance',
    payload: {
      balance,
    },
  };
}

export function setExistingPokemons(existingPokemons) {
  return {
    type: 'setExistingPokemons',
    payload: {
      existingPokemons,
    },
  };
}

export function loadExistingPokemons(jwt) {
  return async (dispatch) => {
    const existingPokemons = await fetchExistingPokemons(jwt);
    dispatch(setExistingPokemons(existingPokemons));
  };
}
