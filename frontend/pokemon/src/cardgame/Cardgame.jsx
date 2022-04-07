import React,{ useEffect } from "react";
import './Cardgame.scss';
import { useMediaQuery } from "react-responsive";
import Card from "./components/Card";

import { useDispatch } from "react-redux";
import { setCardgameFlipped } from "../redux/actions";
import { changeHeaderDisplay } from "../headerDisplay";
import { Link } from "react-router-dom";
import { setCardgameOrder } from "../redux/actions";

export default function Cardgame() {
  const dispatch = useDispatch();
  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
    dispatch(setCardgameFlipped(false));
    dispatch(setCardgameOrder(0));
  },[]);

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
      <Link to="/main">
        <button className="re-btn">메인으로</button>
      </Link>
    </div>
  );
}
