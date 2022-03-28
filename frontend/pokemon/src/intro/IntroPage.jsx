import React from "react";
import { useState } from "react";
import "./IntroPage.css";
import LoginModal from "./components/LoginModal";
import BodyBlackoutStyle from "./components/BodyBlackoutStyle";

export default function IntroPage() {
  const [isLoginModalVisible, setIsLoginModalVisible] = useState(false);

  const onSetIsLoginModalVisible = (active) => {
    setIsLoginModalVisible(active);
  };

  return (
    <div className="intro-container">
      <div>
        <img src='/images/static/introPage/logoLg.png' alt=""  className="intro-logo"/>
      </div>
      <div>
        <img src='/images/static/introPage/logodesc.png' alt=""  className="intro-logo-desc"/>
      </div>
      <div className="intro-running-container">
        <img src='/images/static/introPage/introRunning.gif' alt="" className="intro-running"/>
      </div>
      <div className="intro-shadow-container">
        <img src='/images/static/shadow.png' alt="" className="intro-shadow"/>
      </div>
      <img src='/images/static/monsterBall.png' alt="" className="intro-monster-ball"/>

      <div>
        {/* TODO: jwtToken이 있는 유저일 경우 메인페이지로 이동 */}
        <button className="intro-start-btn" onClick={() => onSetIsLoginModalVisible(true)}>시작하기</button>
      </div>

      {/* 모달 컴포넌트 */}
      { isLoginModalVisible && <LoginModal setIsLoginModalVisible= {setIsLoginModalVisible}/> }
      { isLoginModalVisible && <BodyBlackoutStyle onSetIsLoginModalVisible= {onSetIsLoginModalVisible}/> }
    </div>
  );
}
