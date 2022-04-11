import React from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { changeHeaderDisplay } from "../headerDisplay";
import './MainPage.scss';

// components
import MainCarousel from "./components/MainCarousel";

export default function MainPage() {

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div className="MainPage">
      <MainCarousel/>
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
          <p className='ssf'>+500SSF</p>
          <img src={ '/images/static/nurse.png' } alt="" className="h15"/>
        </Link>
        <Link to={'/cardgame'} className="menu menu4">
          <p>카드게임</p>
          <img src={ '/images/pokemonImg/colored/no.132_colored.jpg' } alt="" className="h15 metamong-img"/>
        </Link>
      </div>
    </div>
  );
}
