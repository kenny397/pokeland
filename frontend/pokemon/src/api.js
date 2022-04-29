import axios from 'axios';

const BASE_URL = 'http://k6s2041.p.ssafy.io/api/v1';

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
    
    let { accessToken, publicKey, verified } = response.data;
    if (verified === 'No') {
      alert('이메일 인증 후 사용해주세요!');
      return ;
    } 
    
    if (accessToken) {
      localStorage.setItem("jwt", accessToken);
      localStorage.setItem("publicKey", publicKey);
      return accessToken;
    } else {
      alert('아이디나 비밀번호가 틀립니다.');
    }
    
  } catch {
    alert('아이디나 비밀번호가 틀립니다.');
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

export async function signupRequest(email,nickname,password) {
  try {
    await axios.post(
      `${BASE_URL}/users/register`,
      { 
        email,
        nickname,
        password
      });
    alert('회원가입 성공!');
  } catch (err) {
    console.log(err);
  }
}

export async function requestBonus(jwt) {
  const response = await axios(
    {
      method : 'post',
      url : `${BASE_URL}/users/bonus`,
      headers: { 'Authorization': 'Bearer ' + jwt },
      data : '',
    }
  );
  return response;
}

export async function emailCheck(email) {
  try {
    const response = await axios.get(`${BASE_URL}/users/check/email/${email}`);
    return response;
  } catch (err) {
    return err;
  }
}

export async function nicknameCheck(nickname) {
  try {
    const response = await axios.get(`${BASE_URL}/users/check/nickname/${nickname}`);
    return response;
  } catch (err) {
    return err;
  }
}
