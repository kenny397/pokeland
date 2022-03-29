import React, { useState } from "react";
import "./LoginDialog.scss";
import axios from "axios";

import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function LoginDialog({ handleClickCloseModal }) {
  const navigate = useNavigate();

  const [ userInfo, setUserInfo ] = useState({
    email: '',
    password: ''
  });

  const onChangeInputs = (e) => {
    const { value, name } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value
    });
  };

  async function onClickSubmitBtn() {
    const { email, password } = userInfo;
    
    try {
      const response = await axios.post(
        'https://j6b208.p.ssafy.io/api/v1/users/login',
        { 
          email,
          password
        });
      
      const { accessToken, publicKey } = response.data;

      localStorage.setItem("jwtToken", accessToken);
      localStorage.setItem("publicKey", publicKey);

      if (accessToken !== null) {
        navigate('/main');
        handleClickCloseModal();
      }
    } catch(err) {
      alert('아이디나 비밀번호가 틀립니다.');
    }
    
  }

  return (
    <div className="LoginDialog">
      <div>
        <img src="/images/static/introPage/logoLg.png" alt="" className="login-logo"/>
      </div>
      <div>
        <input 
          name="email" 
          type="text" 
          placeholder=" 이메일"
          onChange={(e) => onChangeInputs(e)}
        />
      </div>
      <div>
        <input 
          name="password"
          type="password"
          placeholder=" 비밀번호"
          onChange={ (e) => onChangeInputs(e) }
        />
      </div>

      <button 
        className="login-btn"
        onClick={() => onClickSubmitBtn()}
      >
        로그인
      </button>
      <br />
      <Link to="/signup">
        <p className="to-signup">아직 회원이 아니신가요?</p>
      </Link>
    </div>
  );
}
