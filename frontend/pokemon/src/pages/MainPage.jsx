import React from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { changeHeaderDisplay } from "../headerDisplay";

import './MainPage.css';
import mainImage from '../assets/images/mainPageImageDskVer.png';
import pokeDex from '../assets/images/image 68.png';
import pokeGatcha from '../assets/images/image 69.png';

export default function MainPage() {
  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div>
      <img src={ mainImage } alt="" className="main-image"/>
      <div className="menu-container">
        <Link to="/pokedex">
          <div className="menu menu1">
            <p>포켓몬 도감</p>
            <img src={ pokeDex } alt="" className="pokedex-image"/>
          </div>
        </Link>
        <div className="menu menu2">
          <p>뽑기 하러가기</p>
          <img src={ pokeGatcha } alt="" className="g1"/>
        </div>
        <div className="menu menu3"><p>고객센터</p></div>
        <div className="menu menu4"><p>지갑설정</p></div>
      </div>
    </div>
  );
}
