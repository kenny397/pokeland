import React from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { changeHeaderDisplay } from "../headerDisplay";
import { useMediaQuery } from "react-responsive";
import './MainPage.scss';

export default function MainPage() {

  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div className="MainPage">
      { !isDeskTop 
        ?
        <img src={ '/images/static/mainPage/mainPageImage.png' } alt="" className="main-image"/>
        :
        <img src={ '/images/static/mainPage/mainPageImageDsk.png' } alt="" className="main-image"/>
      }
      <div className="menu-container">
        <Link to={'/pokedex'} className="menu menu1">
          <p>포켓몬 도감</p>
          <img src={ '/images/static/pokedex.png' } alt="" className="h12 pokedex-image"/>
        </Link>
        <Link to={'/gacha'} className="menu menu2">
          <p>포켓몬 뽑기</p>
          <img src={ '/images/static/gacha.png' } alt="" className="h15"/>
        </Link>
        <Link to={'/support'} className="menu menu3">
          <p>고객센터</p>
          <img src={ '/images/static/nurse.png' } alt="" className="h15"/>
        </Link>
        <div className="menu menu4" onClick={() => alert('아직 준비중이에요!')}>
          <p>스티커 거래</p>
          <img src={ '/images/pokemonImg/colored/no.132_colored.jpg' } alt="" className="h15 metamong-img"/>
        </div>
      </div>
    </div>
  );
}
