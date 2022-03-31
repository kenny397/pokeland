import axios from 'axios';

const BASE_URL = 'https://j6b208.p.ssafy.io/api/v1';

export async function getBalance() {
  try {
    const jwt = localStorage.getItem('jwt');

    const response = await axios({
      method : 'GET',
      url : `${BASE_URL}/users/balance`,
      headers: { 'Authorization': 'Bearer ' + jwt },
    });
    
    return response;
  } catch {
    return '';
  }
}

export async function requestLogin(email, password) {
  try {
    const response = await axios.post(
      `${BASE_URL}/users/login`,
      {
        email,
        password
      });
    
    let { accessToken, publicKey } = response.data;
    
    localStorage.setItem("jwt", accessToken);
    localStorage.setItem("publicKey", publicKey);
    return accessToken;
  } catch {
    return '';
  }
}

export async function fetchExistingPokemons(jwt) {
  const response = await axios(
    {
      method : 'get',
      url : `${BASE_URL}/pokedex`,
      headers: { 'Authorization': 'Bearer ' + jwt },
      data : '',
    }
  );

  let { pokemonList } = response.data;
  return pokemonList;
}

export async function fetchExistingNfps(publicKey, pokedexId) {
  const response = await axios(
    {
      method : 'get',
      url : `${BASE_URL}/nfp/${publicKey}/${pokedexId}`,
      data : '',
    }
  );

  let { nfpList } = response.data;
  console.log(`fetchExistingNfps excuted nft list is ${JSON.stringify(nfpList)}`);
  return nfpList;
}

export async function doGacha() {
  try {
    const jwt = localStorage.getItem('jwt');

    const response = await axios({
      method : 'POST',
      url : `${BASE_URL}/gacha`,
      headers: { 'Authorization': 'Bearer ' + jwt },
    });
    
    return response;
  } catch {
    return '';
  }
}

export async function writeSupport(address, category, message, title) {
  try {
    const publicKey = localStorage.getItem('publicKey');
    const response = await axios.post(
      `${BASE_URL}/support/`,
      {
        address,
        category,
        message,
        title,
        publicKey
      });
    
    return response;
  } catch {
    return '';
  }
}
