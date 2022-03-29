import React from "react";
import './NavBar.scss';
import SideBar from "./SideBar";
import { useMediaQuery } from 'react-responsive';
import { Link } from "react-router-dom";

function NavBar(){
  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  return (
    <div className="navbar-container">
      {!isDeskTop 
        ?
        <>
          <SideBar/>
          <Link to={'/main'} >
            <img src={ '/images/static/pokemonLogo.png' } className="pokemon-logo"/>
          </Link>
        </>
        :
        <>
          <Link to={'/main'}>
            <img src={ '/images/static/pokemonLogo.png' } className="pokemon-logo"/>
          </Link>
          
          <div className="navbar-right-wrapper">
            <div className="navbar-menu-wrapper">
              <Link to={'/pokedex'} >포켓몬 도감</Link>
              <Link to={'/gacha'} >포켓몬 뽑기</Link>
              <Link to={'/support'} >고객센터</Link>
            </div>
            <div className="navbar-asset-wrapper">
              <div>내 자산:</div>
              <div className="asset-box">500.0SSF</div>
            </div>
          </div>
        </>
      }

    </div>
  );
}
export default NavBar;
