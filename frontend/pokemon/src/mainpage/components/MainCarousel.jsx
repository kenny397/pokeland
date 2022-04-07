import React from "react";
import './MainCarousel.scss';

import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';
import { useMediaQuery } from "react-responsive";

export default function MainCarousel() {

  const isFhd = useMediaQuery({
    query: "(min-width: 1031px)"
  });
  const isQhd = useMediaQuery({
    query: "(min-width: 1921px)"
  });
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
          {
            isQhd ?
              <img src={ '/images/static/mainPage/qhd1.png' } alt="" className="main-image"/>
              :
              isFhd ?
                <img src={ '/images/static/mainPage/fhd1.png' } alt="" className="main-image"/>
                :
                <img src={ '/images/static/mainPage/mobile1.png' } alt="" className="main-image"/>
          }
          <p className="legend">이로치 포켓몬 보러가기</p>
        </div>
        <div className="main-image-wrapper" onClick={(e) => onClickCarousel(e)}>
          {
            isQhd ?
              <img src={ '/images/static/mainPage/qhd2.png' } alt="" className="main-image"/>
              :
              isFhd ?
                <img src={ '/images/static/mainPage/fhd2.png' } alt="" className="main-image"/>
                :
                <img src={ '/images/static/mainPage/mobile2.png' } alt="" className="main-image"/>
          }
          <p className="legend">패치노트 보러가기</p>
        </div>
        <div className="main-image-wrapper" onClick={(e) => onClickCarousel(e)}>
          {
            isQhd ?
              <img src={ '/images/static/mainPage/qhd3.png' } alt="" className="main-image"/>
              :
              isFhd ?
                <img src={ '/images/static/mainPage/fhd3.png' } alt="" className="main-image"/>
                :
                <img src={ '/images/static/mainPage/mobile3.png' } alt="" className="main-image"/>
          }
          <p className="legend">뽑기확률 보러가기</p>
        </div>
      </Carousel>
    </div>
  );
}
