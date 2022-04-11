import React from "react";
import './Card.scss';
import { useState } from "react";
import ReactCardFlip from 'react-card-flip';
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { setCardgameFlipped } from "../../redux/actions";
import { useMediaQuery } from "react-responsive";

import Confetti from 'react-confetti';
import { updateBalance } from "../../redux/actions";
import { setCardgameOrder } from "../../redux/actions";

import { requestBonus } from "../../api";

export default function Card({ isPicachu }) {

  const [ cardFlipped, setCardFlipped ] = useState(false);
  const [ confetti, setConfetti ] = useState(false);
  const cardgameFlipped = useSelector((state) => 
    state.cardgameFlipped
  );
  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });

  const dispatch = useDispatch();

  const onClickCardFlip = () => {
    
    if (!cardgameFlipped) {
      setCardFlipped(!cardFlipped);
      setTimeout(() => {
        if (isPicachu) {
          dispatch(setCardgameOrder(2));
          setConfetti(true);
          
          setTimeout(() => {
            alert('피카츄를 뽑으셨군요! \n300SSF가 지급됩니다.');
            onRequestBonus();  
          }, 200);
          
        } else {
          dispatch(setCardgameOrder(1));
          setTimeout(() => {
            alert('꽝 !! 메타몽이지롱 !');
          }, 200);
          
        }
      }, 500);
      dispatch(setCardgameFlipped(true));
    } else {
      alert('한번만 뒤집을 수 있어요!');
    } 
  };
  const onRequestBonus = async () => {
    const { data: { money } } = await requestBonus(localStorage.getItem('jwt'));
    localStorage.setItem("balance", money); 
    dispatch(updateBalance(localStorage.getItem('balance'))); 
    
  };

  return (
    <div className="Card">
      
      <ReactCardFlip isFlipped={cardFlipped} flipDirection="vertical">
        <div onClick={() => onClickCardFlip()} className="card-wrapper">
          ❔
        </div>
        
        <div onClick={() => onClickCardFlip()} className="card-wrapper">
          {
            isPicachu 
              ? 
              <img src="images/pokemonImg/colored/no.25_colored.jpg" className="pokemon-image picachu-image"/>
              :
              <img src="/images/pokemonImg/colored/no.132_colored.jpg" className="pokemon-image metamong-image"/>
          }
          
        </div>
      </ReactCardFlip>
      { confetti &&
        <Confetti
          width={ isDeskTop ? 1030 : window.innerWidth}
          height={isDeskTop ? window.innerHeight-80 : window.innerHeight}
          numberOfPieces={200}
          gravity={ isDeskTop ? 0.03 : 0.07}
        />
      }
    </div>
  );
}
