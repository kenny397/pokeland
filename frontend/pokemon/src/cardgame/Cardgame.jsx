import React from "react";
import './Cardgame.scss';
import { useMediaQuery } from "react-responsive";
import Card from "./components/Card";

export default function Cardgame() {
  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  let isPicachuList = [false, false, false];
  const randomNum = Math.floor(Math.random() * 3);
  isPicachuList[randomNum] = true;
  
  return (
    <div className="Cardgame">
      <div className="cardgame-header">
        <div>
          <img src="images/static/pokemonStickerGif/picachuwink.gif" className="header-picachu" />
          <span>피카츄 카드게임</span>
          <img src="images/static/pokemonStickerGif/picachuwink.gif" className="header-picachu" />
        </div>

      </div>
      <div className="cardgame-desc">
        {
          isDeskTop 
            ? 
            '세 카드 중 피카츄의 위치를 맞춰 300SSF를 획득하세요 !'
            :
            '세 카드 중 피카츄의 위치를 맞춰 \n300SSF를 획득하세요!'
        }
      </div>
      <div className="cardgame-main-container">
        {isPicachuList.map((isPicachu, index) => (
          <Card 
            key={index}
            isPicachu={isPicachu}
          />
        ))}
      </div>
      <button className="re-btn">다시하기</button>
    </div>
  );
}
