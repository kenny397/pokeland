import React from "react";
import { Link } from 'react-router-dom';
import './MainPage.css';
import mainImage from '../assets/images/mainPageImage.png';

export default function MainPage() {
  return (    
    <div>
      <img src={ mainImage } alt="" className="main-image"/>
      <Link to="/support">
        <button>
          Support page
        </button>
      </Link>
    </div>
  );
}
