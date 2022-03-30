import axios from 'axios';

const BASE_URL = 'https://j6b208.p.ssafy.io/api/v1/';

export async function getBalance() {
  try {
    const jwt = localStorage.getItem('jwt');

    const response = await axios({
      method : 'GET',
      url : `${BASE_URL}users/balance`,
      headers: { 'Authorization': 'Bearer ' + jwt },
    });
    
    let { money } = response.data;
    
    localStorage.setItem("asset", money);
    
    return money;
  } catch {
    return '';
  }
}

export async function requestLogin(email, password) {
  try {
    const response = await axios.post(
      `${BASE_URL}users/login`,
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
