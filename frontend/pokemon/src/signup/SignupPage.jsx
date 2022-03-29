import React, { useState, useEffect } from "react";
import "./SignupPage.scss";
import { changeHeaderDisplay } from "../headerDisplay";
import axios from "axios";

export default function SignupPage() {

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  const [userInfo, setUserInfo] = useState({
    userEmail: '',
    userNickname: '',
    userPassword: '',
    userPasswordConfirm: ''
  });

  const onChangeInputs = (e) => {
    const { value, name } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value
    });  
  };

  async function onClickSubmit(){
    if(userInfo.userPassword !== userInfo.userPasswordConfirm) {
      console.log('안돼 돌아가');
      return '';
    }
    const { userEmail, userNickname, userPassword, userPasswordConfirm } = userInfo;
 
    const response = await axios.get(
      `https://j6b208.p.ssafy.io/api/v1/users/register`
      ,userEmail
      ,userNickname
      ,userPassword
      ,userPasswordConfirm
    );

    console.log(response);

  }

  return (
    <div className="SignupPage">
      <div className="signup-header">
        <p>회원가입</p>
        <img className="picachu-img" src="/images/static/pokemonStickerGif/picachurunning.gif" alt="no-image" />
      </div>

      <div className="form-container">
        <div className="email-input-wrapper" >
          <p>이메일</p>
          <input name="userEmail" type="text" placeholder="이메일을 입력해 주세요" onChange={(e) => onChangeInputs(e)}/>
        </div>
        <div className="nickname-input-wrapper" >
          <p>닉네임</p>
          <input name="userNickname" type="text" placeholder="닉네임을 입력해 주세요" onChange={(e) => onChangeInputs(e)}/>
        </div>
        <div className="password-input-wrapper">
          <p>비밀번호</p>
          <input name="userPassword" type="password" placeholder="비밀번호를 입력해 주세요" onChange={(e) => onChangeInputs(e)}/>
        </div>
        <div className="password-confirm-input-wrapper" >
          <p>비밀번호 확인</p>
          <input name="userPasswordConfirm" type="password" placeholder="비밀번호를 재확인해 주세요" onChange={(e) => onChangeInputs(e)}/>
        </div>
        <button className="submit-btn" onClick={()=> onClickSubmit}>가입하기</button>
      </div>
    </div>
  );
}
