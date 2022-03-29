import React, { useState, useEffect } from "react";
import { slide as Menu } from "react-burger-menu";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import { faUser, faCopy } from "@fortawesome/free-regular-svg-icons";

import './SideBar.scss';

export default function SideBar() {

  const [isOpen, setIsOpen] = useState(false);
  const publicKey = localStorage.getItem('publicKey');
  
  const clicked = () => {
    setIsOpen(false);
  };
  const overlay = document.getElementsByClassName('bm-overlay');

  useEffect(() => {
    overlay[0].addEventListener("click", clicked);

  });

  return (
    <>
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
          <div>계정 주소</div>
          <div className="asset-wrapper">
            <p>{ publicKey.substring(0,4)+ "...."+ publicKey.slice(-4) }</p>
            <FontAwesomeIcon icon={faCopy} className="copy-icon"/>
          </div>
          <div>자산</div>
          <p>1000.0 SSF</p>
        </div>
        <Link to={'/pokedex'} onClick={() => setIsOpen(false)}>
          포켓몬 도감
        </Link>

        <Link to={'/gacha'} onClick={() => setIsOpen(false)}>
          포켓몬 뽑기
        </Link>

        <Link to={'/support'} onClick={() => setIsOpen(false)}>
          고객센터
        </Link>
        
        <Link to={'/main'} onClick={() => setIsOpen(false)}>
          메인
        </Link>
        
      </Menu>
      <FontAwesomeIcon icon={faBars} className="burger-btn" onClick={() => setIsOpen(true)}/>
    </>
  );
}
