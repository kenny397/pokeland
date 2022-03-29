import React, { useState, useEffect } from "react";
import { slide as Menu } from "react-burger-menu";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from "@fortawesome/free-solid-svg-icons";

import './SideBar.scss';

export default function SideBar() {

  const [isOpen, setIsOpen] = useState(false);

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
        isOpen={isOpen}
        disableOverlayClick
        disableAutoFocus 
      >
        <p className="sidebar-cross-btn" onClick={() => setIsOpen(false)}>X</p>
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
