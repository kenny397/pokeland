import React, { useState } from "react";
import "./LoginDialog.scss";

import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

import { requestLogin, getBalance } from "../../api";

import { useDispatch } from "react-redux";
import { updateBalance } from "../../redux/actions";

export default function LoginDialog({ handleClickCloseModal }) {
  
  const dispatch = useDispatch();
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

  const onClickSubmitBtn = async () => {
    const { email, password } = userInfo;
    const accessToken = await requestLogin(email, password);
    if (accessToken) {
      const { data: { money } } = await getBalance();
      localStorage.setItem("balance", money);
      dispatch(updateBalance(localStorage.getItem('balance')));
      console.log(money);

      navigate('/main');
      handleClickCloseModal();
    }
  };

  const onKeyPress = (e) => {
    if(e.key === 'Enter') {
      onClickSubmitBtn();
    }
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
          placeholder=" 아이디"
          onChange={(e) => onChangeInputs(e)}
          onKeyPress={ onKeyPress }
        />
      </div>
      <div>
        <input 
          name="password"
          type="password"
          placeholder=" 비밀번호"
          onChange={ (e) => onChangeInputs(e) }
          onKeyPress={ onKeyPress }
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
