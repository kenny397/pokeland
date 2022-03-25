import React from "react";
import { slide as Menu } from "react-burger-menu";
import { Link } from "react-router-dom";
export default function SideBar() {
  return (
    <Menu>
      <Link to={'/pokedex'}>
        포켓몬 도감
      </Link>

      <Link to={'/gatcha'}>
        포켓몬 뽑기
      </Link>

      <Link to={'/support'}>
        고객센터
      </Link>

      <Link to={'/main'}>
        메인
      </Link>
    </Menu>
  );
}
