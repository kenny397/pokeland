import React from "react";
import './MainCarousel.scss';

import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';
import { useMediaQuery } from "react-responsive";

export default function MainCarousel() {
  const isMobile = useMediaQuery({
    query: "(max-width: 1029px)"
  });
  const isFhd = useMediaQuery({
    query: "(min-width: 1031px)"
  });
  const isQhd = useMediaQuery({
    query: "(min-width: 1921px)"
  });
  const onClickCarousel = (e) => {
    const targetNotion = e.target.className;
    if (targetNotion.includes('to-irochi')) {
      window.open('https://three-catcher-318.notion.site/ad7eb9688f4a4975981e0c85aea86d10');
    } else if(targetNotion.includes('to-patch-note')) {
      window.open('https://three-catcher-318.notion.site/1-0-4-7-18-00-d729090db7ba4528a5dbdc4f247f7f14');
    } else if(targetNotion.includes('to-gacha-percent')) {
      window.open('https://three-catcher-318.notion.site/45e6b9e7a79c4613a90ba035e226a26b');
    }
    
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
        <div className="main-image-wrapper to-irochi"  onClick={(e) => onClickCarousel(e)}>
          {
            isQhd ?
              <img src={ '/images/static/mainPage/qhd1.png' } alt="" className="main-image"/>
              :
              isFhd ?
                <img src={ '/images/static/mainPage/fhd1.png' } alt="" className="main-image"/>
                :
                <img src={ '/images/static/mainPage/mobile1.png' } alt="" className="main-image"/>
          }
          { !isMobile 
            &&
            <p className="legend to-irochi" onClick={(e) => onClickCarousel(e)}>이로치 포켓몬 보러가기</p>
          }
        </div>
        <div className="main-image-wrapper to-patch-note" onClick={(e) => onClickCarousel(e)}>
          {
            isQhd ?
              <img src={ '/images/static/mainPage/qhd2.png' } alt="" className="main-image"/>
              :
              isFhd ?
                <img src={ '/images/static/mainPage/fhd2.png' } alt="" className="main-image"/>
                :
                <img src={ '/images/static/mainPage/mobile2.png' } alt="" className="main-image"/>
          }
          { !isMobile 
            &&
            <p className="legend to-patch-note" onClick={(e) => onClickCarousel(e)}>패치노트 보러가기</p>
          }
        </div>
        <div className="main-image-wrapper to-gacha-percent" onClick={(e) => onClickCarousel(e)}>
          {
            isQhd ?
              <img src={ '/images/static/mainPage/qhd3.png' } alt="" className="main-image"/>
              :
              isFhd ?
                <img src={ '/images/static/mainPage/fhd3.png' } alt="" className="main-image"/>
                :
                <img src={ '/images/static/mainPage/mobile3.png' } alt="" className="main-image"/>
          }
          { !isMobile 
            &&
            <p className="legend to-gacha-percent" onClick={(e) => onClickCarousel(e)}>뽑기확률 보러가기</p>
          }
        </div>
      </Carousel>
    </div>
  );
}
