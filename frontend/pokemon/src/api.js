import axios from 'axios';

export async function requestLogin(email, password) {
  try {
    const response = await axios.post(
      'https://j6b208.p.ssafy.io/api/v1/users/login',
      { 
        email,
        password
      });
    
    let { accessToken, publicKey } = response.data;
    
    localStorage.setItem("jwtToken", accessToken);
    localStorage.setItem("publicKey", publicKey);
    return accessToken;
  } catch {
    return '';
  }
}
