import React from "react";
import { useEffect } from "react";
import { changeHeaderDisplay } from "../headerDisplay";
import './MainPage.css';
import { Link } from "react-router-dom";

export default function MainPage() {

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div>
      <img src={ '/images/static/mainPageImageDskVer.png' } alt="" className="main-image"/>
      <div className="menu-container">
        <Link to={'/'} className="menu menu1">
          <p>포켓몬 도감</p>
          <img src={ '/images/static/pokedex.png' } alt="" className="h12 pokedex-image"/>
        </Link>
        <div className="menu menu2">
          <p>포켓몬 뽑기</p>
          <img src={ '/images/static/gatcha.png' } alt="" className="h15"/>
        </div>
        <div className="menu menu3">
          <p>고객센터</p>
          <img src={ '/images/static/nurse.png' } alt="" className="h15"/>
        </div>
        <div className="menu menu4">
          <p>지갑설정</p>
          <img src={ '/images/static/metamask.png' } alt="" className="h12"/>
        </div>
      </div>
    </div>
  );
}
