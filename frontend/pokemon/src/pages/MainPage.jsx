import React from "react";
import { useEffect } from "react";
import { changeHeaderDisplay } from "../headerDisplay";
import { Link } from 'react-router-dom';
import './MainPage.css';
import mainImage from '../assets/images/mainPageImage.png';

export default function MainPage() {
  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  return (
    <div>
      <img src={ mainImage } alt="" className="main-image"/>
      <Link to="/support">
        <button>
          Support page
        </button>
      </Link>
      <Link to="/pokedex">
        <button>
          도감
        </button>
      </Link>
    </div>
  );
}
