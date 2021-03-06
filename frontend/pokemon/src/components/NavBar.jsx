import React from "react";
import './NavBar.scss';
import SideBar from "./SideBar";
import { useMediaQuery } from 'react-responsive';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

function NavBar(){
  const navigate = useNavigate();
  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  const { balance } = useSelector(state => ({
    balance: state.balance
  }));
  
  const handleClickLogOut = () => {
    localStorage.clear();
    navigate('/');
  };
  
  return (
    <div className="NavBar">
      <div className="navbar-container">
        {!isDeskTop 
          ?
          <>
            <SideBar
              balance={balance}
            />
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
              <div className="navbar-asset-wrapper">
                <div>내 자산:</div>
                <div className="asset-box">{balance}SSF</div>
              </div>
              <div className="navbar-menu-wrapper">
                <Link to={'/pokedex'} >포켓몬 도감</Link>
                <Link to={'/gacha'} >포켓몬 뽑기</Link>
                <Link to={'/support'} >고객센터</Link>
                <a onClick={handleClickLogOut}>로그아웃</a>
              </div>
            </div>
          </>
        }

      </div>
    </div>
  );
}
export default NavBar;
