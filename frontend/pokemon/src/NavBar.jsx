import React from "react";
import './NavBar.css';
import SideBar from "./SideBar";
import { useMediaQuery } from 'react-responsive';

function NavBar(){
  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  return (
    <div className="navbar-container">
      {!isDeskTop 
        ?
        <SideBar/>
        :
        <>
          <div>logo</div>
          
          <div className="navbar-right-wrapper">
            <div className="navbar-menu-wrapper">
              <div>포켓몬 도감</div>
              <div>포켓몬 뽑기</div>
              <div>고객센터</div>
            </div>
            <div className="navbar-asset-wrapper">
              <div>내 자산: </div>  
              <div>500.0SSF</div>
            </div>
          </div>
        </>
      }

    </div>
  );
}
export default NavBar;
