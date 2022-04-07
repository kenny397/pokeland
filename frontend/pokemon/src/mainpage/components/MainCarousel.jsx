import React from "react";
import './MainCarousel.scss';

import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';

export default function MainCarousel() {
  const onClickCarousel = (e) => {
    console.log(e);
    window.open('https://www.notion.so/NFT-88e1b26f882d4254aacfc67aac8525ca');
  };

  return (
    <div className="MainCarousel">
      <Carousel
        swipeable
        autoPlay
        showStatus={false}
        showThumbs={false}
        infiniteLoop={true}
        showArrows={true}
      >
        <div className="main-image-wrapper" onClick={(e) => onClickCarousel(e)}>
          <img src={ '/images/static/mainPage/mainPageImage.png' } alt="" className="main-image"/>
          <p className="legend">Legend 1</p>
        </div>
        <div className="main-image-wrapper" onClick={(e) => onClickCarousel(e)}>
          <img src={ '/images/static/mainPage/mainPageImageDsk.png' } alt="" className="main-image"/>
          <p className="legend">Legend 1</p>
        </div>
      </Carousel>
    </div>
  );
}
