import React from "react";
import { slide as Menu } from "react-burger-menu";
import { Link } from "react-router-dom";
export default function SideBar() {
  return (
    <Menu>
      <Link to={'/'}>
        포켓몬 도감
      </Link>

      <Link to={'/'}>
        포켓몬 도감
      </Link>

      <Link to={'/'}>
        포켓몬 도감
      </Link>

      <Link to={'/'}>
        포켓몬 도감
      </Link>
    </Menu>
  );
}
