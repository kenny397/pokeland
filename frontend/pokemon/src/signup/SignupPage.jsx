/* eslint-disable no-useless-escape */
import React, { useState, useEffect } from "react";
import "./SignupPage.scss";
import { changeHeaderDisplay } from "../headerDisplay";
import axios from "axios";

import { useNavigate } from "react-router-dom";

import { signupRequest } from "../api";

export default function SignupPage() {
  const navigate = useNavigate();

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);
  
  const [userInfo, setUserInfo] = useState({
    email: '',
    nickname: '',
    password: '',
    passwordConfirm: '',
    isEmailChecked: false,
    isNicknameChecked: false,
  });

  const [isLoading, setIsLoading] = useState(false);

  const { email, nickname, password, passwordConfirm, isEmailChecked, isNicknameChecked } = userInfo;

  const changeState = (title, value) => {
    setUserInfo((prevState) => {
      return {
        ...prevState,
        [title]: value
      };
    });
  };

  const onChangeInputs = (e) => {
    const { value, name } = e.target;
    // 체크후에 값 바꿀경우 다시 중복확인 하기위함
    if (name === "nickname") {
      changeState("isNicknameChecked", false);
    }
    if (name === "email") {
      changeState("isEmailChecked", false);
    }

    changeState(name, value);
  };
  
  async function onClickSubmit(){
    console.log(isPassword(password));
    if (!isPassword(password)) {
      alert("비밀번호는 영문, 숫자 포함 8~15자 이내여야 합니다.");
      return;
    }
    if (password !== passwordConfirm) {
      alert("패스워드 입력값이 다릅니다.");
      return;
    }
    if (email === '') {
      alert("이메일을 입력해주세요.");
      return;
    }
    if (nickname === '') {
      alert('닉네임을 입력해주세요.');
      return;
    }
    if ( !isEmailChecked ) {
      alert("이메일 중복검사를 해주세요!");
      return;
    }
    if ( !isNicknameChecked ) {
      alert("닉네임 중복검사를 해주세요!");
      return;
    }
    setIsLoading(true);
    const response = await signupRequest(email, nickname, password);
    console.log(response);
    navigate('/');
    setIsLoading(false);
  }

  const isEmail = (email) => {
    const emailRegex =
    /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return emailRegex.test(String(email).toLowerCase());
  };

  const isNickName = (nickname) => {
    const nicknameRegex = 
    /^(?=.{2,10}$)[^0-9][^-!$%^&*()_+|~=`\\#{}\[\]:";'<>?,.\/]+$/;
    return nicknameRegex.test(nickname);
  };

  const isPassword = (password) => {
    const passwordRegex = 
    /^(?=.*[a-z])(?=.*[0-9]).{8,20}$/;
    return passwordRegex.test(password);
  };

  async function onClickEmailCheck() {
    if (isEmail(email) === false) {
      alert('이메일 양식을 맞춰주세요.');
      return '';
    }
    try {
      const response = await axios.get(`https://j6b208.p.ssafy.io/api/v1/users/check/email/${email}`);
      if (response.data.flag === 1) {
        changeState("isEmailChecked", false);
        alert('중복된 이메일입니다.');
      } else {
        alert('사용가능한 이메일입니다.');
        changeState("isEmailChecked", true);
      }
    } catch (err) {
      console.log(err);
    }
  }
  
  async function onClickNicknameCheck() {
    if (isNickName(nickname) === false) {
      alert('닉네임은 특수문자 제외 \n2글자 이상 10글자 이하여야 합니다.');
      return '';
    }
    try {
      const response = await axios.get(`https://j6b208.p.ssafy.io/api/v1/users/check/nickname/${nickname}`);
      console.log(isNickName(nickname));
      if (response.data.flag === 1) {
        changeState("isNicknameChecked", false);
        alert('중복된 닉네임입니다.');
      } else {
        changeState("isNicknameChecked", true);
        alert('사용가능한 닉네임입니다.');
      }
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <div className="SignupPage">
      <div className="signup-container">
        <div className="signup-header">
          <span>회원가입</span>
          <img className="picachu-img" src="/images/static/pokemonStickerGif/picachurunning.gif" alt="no-image" />
        </div>

        <div className="form-container">
          <div className="email-input-wrapper" >
            <p>이메일</p>
            <input name="email" type="text" placeholder="이메일을 입력해 주세요" onChange={(e) => onChangeInputs(e)}/>
            <button 
              className={"dup-btn email-dup-btn " + (isEmailChecked ? ' btn-dp-none' : '')}
              onClick={ () => onClickEmailCheck() }
            >중복확인</button>
            <div className={"dup-check-div" + (isEmailChecked ? '' : ' btn-dp-none')}>사용가능한 이메일 입니다.</div>
          </div>
          <div className="nickname-input-wrapper" >
            <p>닉네임</p>
            <input name="nickname" type="text" placeholder="닉네임을 입력해 주세요" onChange={(e) => onChangeInputs(e)}/>
            <button 
              className={"dup-btn nickname-dup-btn" + (isNicknameChecked ? ' btn-dp-none' : '') + (isEmailChecked ? ' btn-down' : '')} 
              onClick={() => onClickNicknameCheck()}
            >중복확인</button>
            <div className={"dup-check-div" + (isNicknameChecked ? '' : ' btn-dp-none')}>사용가능한 닉네임 입니다.</div>
          </div>
          <div className="password-input-wrapper">
            <p>비밀번호</p>
            <input name="password" type="password" placeholder="비밀번호를 입력해 주세요" onChange={(e) => onChangeInputs(e)}/>
          </div>
          <div className="password-confirm-input-wrapper" >
            <p>비밀번호 확인</p>
            <input name="passwordConfirm" type="password" placeholder="비밀번호를 재확인해 주세요" onChange={(e) => onChangeInputs(e)}/>
          </div>
          <button className="submit-btn" onClick={() => onClickSubmit()}>가입하기</button>
        </div>
      </div>
      { isLoading &&
        <>
          <div className="body-blackout"/>
          <img src="/images/static/pokemonStickerGif/picachuhappy.gif" alt="" className="pokeball-spinning-img"/> 
        </>
      }
    </div>
  );
}
