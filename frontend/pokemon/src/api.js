import axios from 'axios';

const BASE_URL = 'https://j6b208.p.ssafy.io/api/v1/';

// async function getBalance() {
//   try {
//     localStorage.getItem('jwt');

//     const response = await axios.get(
//       `${BASE_URL}users/balance`,
//       {
//         email,
//         password
//       });
    
//     let { accessToken, publicKey } = response.data;
    
//     localStorage.setItem("jwt", accessToken);
//     localStorage.setItem("publicKey", publicKey);
//     return accessToken;
//   } catch {
//     return '';
//   }
// }

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
