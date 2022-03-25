import React from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { changeHeaderDisplay } from "../headerDisplay";

import './MainPage.css';

export default function MainPage() {

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div>
      <img src={ '/images/static/mainPageImageDskVer.png' } alt="" className="main-image"/>
      <div className="menu-container">
        <Link to={'/pokedex'} className="menu menu1">
          <p>포켓몬 도감</p>
          <img src={ '/images/static/pokedex.png' } alt="" className="h12 pokedex-image"/>
        </Link>
        <Link to={'/gatcha'} className="menu menu2">
          <p>포켓몬 뽑기</p>
          <img src={ '/images/static/gatcha.png' } alt="" className="h15"/>
        </Link>
        <Link to={'/support'} className="menu menu3">
          <p>고객센터</p>
          <img src={ '/images/static/nurse.png' } alt="" className="h15"/>
        </Link>
        <div className="menu menu4">
          <p>지갑설정</p>
          <img src={ '/images/static/metamask.png' } alt="" className="h12"/>
        </div>
      </div>
    </div>
  );
}
