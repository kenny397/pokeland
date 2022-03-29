import React, { useState } from "react";
import "./LoginDialog.scss";

import { Link } from "react-router-dom";

export default function LoginDialog({ handleClickCloseModal }) {
  const [ inputs, setInputs ] = useState({
    email: '',
    password: ''
  });

  const onChangeInputs = (e) => {
    const { value, name } = e.target;
    setInputs({
      ...inputs,
      [name]: value
    });
  };

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
        onClick={() => handleClickCloseModal()}
      >
        로그인
      </button>
      <Link to="/signup">
        <p className="to-signup">아직 회원이 아니신가요?</p>
      </Link>
      <Link to="/main">
        <button>메인임시버튼</button>
      </Link>
    </div>
  );
}
