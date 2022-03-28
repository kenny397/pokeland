import React, { useState } from "react";
import "./LoginDialog.css";

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
    <div className="login-dialog-container">
      <div>
        <img src="/images/static/introPage/logoLg.png" alt="" className="login-dialog-logo"/>
      </div>
      <input name="email" type="text" onChange={(e) => onChangeInputs(e)}/>
      <input name="password" type="password" onChange={(e) => onChangeInputs(e)}/>

      <button onClick={() => handleClickCloseModal()}>로그인</button>
      <p>아직 회원이 아니신가요?</p>
      <Link to="/main">
        <button>메인임시버튼</button>
      </Link>
    </div>
  );
}
