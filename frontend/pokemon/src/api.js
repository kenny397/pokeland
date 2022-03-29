import axios from 'axios';
import html2canvas from 'html2canvas';

export async function fetchLogin() {
  const url = '';
  const response = await fetch(url);
  const data = await response.token;
  return data;
}

export async function loadClothesByUserName(userName) {
  const res = await axios({
    method: 'get',
    url: `http://i6b108.p.ssafy.io:8000/clothing/list/${userName}`,
  });

  return res.data.data;
}

export async function createFile(element) {
  const canvas = await html2canvas(element);
  const data = canvas.toDataURL("image/jpg");
  const response = await axios.get(data, { responseType: "blob" });
  const blob = response.data;
  return new File([blob], "filename.jpeg");
}

export async function loadCodyByUserName(userName) {
  let res = [];
  if (localStorage.getItem('friendName') !== JSON.parse(localStorage.getItem('userInfo')).username){
    res = await axios.get(`http://i6b108.p.ssafy.io:8000/cody/list/${userName}`);
  } else {
    res = await axios.get(`http://i6b108.p.ssafy.io:8000/cody/read/${userName}`);
  }
  let cody = [];
  if (res.data.data) {
    cody = res.data.data.sort(function (a, b) {
      return a.updateDate > b.updateDate ? -1 : a.updateDate < b.updateDate ? 1 : 0;
    });
  }
  return cody;
}

export async function putCody(payload) {
  const { imageId, content, clothingList, codyId, codyName, userName, secret, codyTag } = payload;
  const data = {
    clothingList,
    codyId,
    codyName,
    userName,
    content,
    secret,
    codyTag,
    imageId,
  };
  // const res = await axios.get(image, { responseType: "blob" });
  // const blob = res.data;
  // const file = new File([blob], "filename.jpeg");
  // const fd = new FormData();
  // fd.append('updateCody', new Blob([JSON.stringify(data)], { type: 'application/json' }));
  // fd.append('imageId', imageId);
  // const config = {
  //   Headers: {
  //     'Content-Type': 'application/json'
  //   },
  // };
  const response = await axios.put('http://i6b108.p.ssafy.io:8000/cody/update', data);
  return response;
} 

export async function postCody(payload) {
  const { file, codyItems, content, isNotSecret, tags, userName } = payload;

  const fd = new FormData();
  fd.append('imageFile', file);

  const itemsIncody = codyItems.map(item => {
    const { clothingId, position, size } = item;
    return {
      clothingId,
      x: position.x,
      y: position.y,
      z: position.z,
      m: size.m,
    };
  });

  const data = {
    codyName: 'name',
    secret: isNotSecret ? 0 : 1,
    clothingList: itemsIncody,
    codyTag: tags.join(' '),
    userName,
    content
  };

  fd.append('createCody', new Blob([JSON.stringify(data)], { type: 'application/json' }));

  const config = {
    Headers: { 'Content-Type': 'multipart/form-data' },
  };

  const response = await axios.post('http://i6b108.p.ssafy.io:8000/cody/create', fd, config);
  return response;
}

export async function signIn(data) {
  const response = await axios.post(`http://i6b108.p.ssafy.io:8000/user/signup`, data);
  return response.data;
}

export async function authLogin(data) {
  const response = await axios.post(`http://i6b108.p.ssafy.io:8000/user/login`, data);
  return response.data;
}

export async function loadUsersToFollow(userName) {
  const response = await axios.get(`http://i6b108.p.ssafy.io:8000/user/find/follow/${userName}`);
  return response.data.data;
}

export async function loadFollowers(userName) {
  const response = await axios.get(`http://i6b108.p.ssafy.io:8000/user/find/follower/${userName}`);
  return response.data.data;
}

export async function loadFollowings(userName) {
  const response = await axios.get(`http://i6b108.p.ssafy.io:8000/user/find/following/${userName}`);
  return response.data.data;
}

export async function requestFollow(following, userName) {
  const config = {
    params: {
      following,
      userName,
    },
  };
  await axios.get('http://i6b108.p.ssafy.io:8000/user/follow', config);
}

export async function requestUnfollow(unfollowing, userName) {
  const config = {
    params: {
      following: unfollowing,
      userName,
    },
  };
  await axios.get('http://i6b108.p.ssafy.io:8000/user/unfollow', config);
}

export async function deleteClothes(clothingId) {
  await axios.delete(`http://i6b108.p.ssafy.io:8000/clothing/${clothingId}`);
}
