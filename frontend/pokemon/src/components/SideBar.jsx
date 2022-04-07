import React, { useState, useEffect } from "react";
import { slide as Menu } from "react-burger-menu";
import { Link } from "react-router-dom";

// font-awesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import { faUser, faCopy } from "@fortawesome/free-regular-svg-icons";
// react router dom
import { useNavigate } from "react-router-dom";

import { CopyToClipboard } from 'react-copy-to-clipboard';

import './SideBar.scss';

export default function SideBar({ balance }) {
  useEffect(() => {
    overlay[0].addEventListener("click", clicked);

  });
  const navigate = useNavigate();

  const [isOpen, setIsOpen] = useState(false);
  const publicKey = localStorage.getItem('publicKey');
  
  const clicked = () => {
    setIsOpen(false);
  };
  const overlay = document.getElementsByClassName('bm-overlay');

  const handleClickLogOut = () => {
    localStorage.clear();
    navigate('/');
  };

  return (
    <div className="SideBar">
      <Menu 
        customBurgerIcon={ false } 
        customCrossIcon={ false }
        isOpen={ isOpen }
        disableOverlayClick
        disableAutoFocus 
      >
        <FontAwesomeIcon icon={faArrowLeft} className="sidebar-cross-btn" onClick={() => setIsOpen(false)}/>
        <div className="sidebar-user-wrapper">
          <div className="user-icon-wrapper">
            <FontAwesomeIcon icon={faUser} className="user-icon"/>
          </div>
          <div onClick={handleClickLogOut}>로그아웃</div>
          <div className="big-font">계정 주소</div>
          <div className="asset-wrapper">
            <span>{ publicKey ? publicKey.substring(0,4)+ "...."+ publicKey.slice(-4) : 0 }</span>
            <CopyToClipboard 
              text={localStorage.getItem('publicKey')}
              onCopy={() => alert('클립보드에 복사됐어요 !')}
            >
              <FontAwesomeIcon icon={faCopy} className="copy-icon"/>
            </CopyToClipboard>
          </div>
          <div className="big-font">자산</div>
          <div className="big-font blue-font">{balance} SSF</div>
        </div>
        <div className="menu-container">
          <Link to={'/pokedex'} className="menu menu1" onClick={clicked}>
            <p>포켓몬 도감</p>
            <img src={ '/images/static/pokedex.png' } alt="" className="h12 pokedex-image"/>
          </Link>
          <Link to={'/gacha'} className="menu menu2" onClick={clicked}>
            <p>포켓몬 뽑기</p>
            <img src={ '/images/static/gacha.png' } alt="" className="h15"/>
          </Link>
          <Link to={'/support'} className="menu menu3" onClick={clicked}>
            <p>고객센터</p>
            <p className='ssf'>+500SSF</p>
            <img src={ '/images/static/nurse.png' } alt="" className="h15"/>
          </Link>
          <div className="menu menu4" onClick={() => alert('아직 준비중이에요!')}>
            <p>스티커 거래</p>
            <img src={ '/images/pokemonImg/colored/no.132_colored.jpg' } alt="" className="h15 metamong-img"/>
          </div>
        </div>
        
      </Menu>
      <FontAwesomeIcon icon={faBars} className="burger-btn" onClick={() => setIsOpen(true)}/>
    </div>
  );
}
