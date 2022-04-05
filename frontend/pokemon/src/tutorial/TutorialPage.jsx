import React from "react";
import { useState } from "react";
import './TutorialPage.scss';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSortDown } from "@fortawesome/free-solid-svg-icons";

import Typewriter from 'typewriter-effect';
import { Link } from "react-router-dom";
import { useMediaQuery } from "react-responsive";

import { useEffect } from "react";
import { changeHeaderDisplay } from "../headerDisplay";

export default function TutorialPage() {
  useEffect(() => {
    changeHeaderDisplay(window.location.pathname);
  }, []);

  const isDeskTop = useMediaQuery({
    query: "(min-width: 1030px)"
  });
  const scriptList = [
    "",
    "안녕하신가!\n내 이름은 오박사.\n포켓몬을 연구하고 있단다.\n포켓몬 세상에 온 것을 환영한다!", 
    '혹시 NFT에 대해서 들어보았나? \n포켓몬 세상에서는 NFT가 발명된 이후 아주 떠들썩한 변화가 있었단다.', 
    'NFT는 Non Fungible Token! \n대체할 수 없는 토큰이라네! \n포켓몬에 NFT라는 고유한 값을 부여함으로써 이 포켓몬 만이 "진짜"고 누가 소유하고 있는지를 전 세계 모든 트레이너 에게 인증할 수 있지!', 
    '그렇다! \n바로 네가 있는 세상과 포켓몬 세상이 실제로 연결된다는 것이다!',
    '이 몬스터 볼에는 아직 NFT화 되지 않은 포켓몬이 너를 기다리고 있단다! \n클릭해서 몬스터볼을 열어보렴!',
    '불꽃 포켓몬 파이리를 선택했구나! 아주 매력적인 포켓몬이지.', 
    '자, 방금 파이리가 NFT로 생성되었다! 이 파이리는 이제 전 세계에서 한마리밖에 존재하지 않는 너만의 파이리인 것이야!',
    '이 파이리를 네 지갑에 저장하기만 한다면, 네가 이 파이리를 소유한다는 것을 전 세계가 보증하게 되지!!',
    '파이리는 나 오박사가 압수해가지! \n하지만 뽑기를 할 수 있는 충분한 토큰을 주었으니 포켓몬 뽑기를 해보도록 하거라 !! \n그럼 태초마을에서 보자꾸나 '];
  const [ scriptNo, setScriptNo ] = useState(1);
  const [ isAllTyped, setIsAllTyped ] = useState(false);
  
  const changeScriptNo = (btnColor) => {
    if (isAllTyped) {
      if (btnColor ==="red") {
        setScriptNo(scriptNo + 2);
      } else {
        setScriptNo(scriptNo + 1);
      }
      setIsAllTyped(false);
    } else {
      setIsAllTyped(true);
    }
  };

  return (
    <div className="TutorialPage">
      <div className="tutorial-page-wrapper">
        { isDeskTop
          &&
          <div>
            <img src="/images/static/pokemonLogo.png" className="tutorial-pokemon-logo" />
          </div>
        }
        { scriptNo === 5
          &&
          <img src="/images/static/monsterballs.png" className="monster-balls-img" onClick={() => changeScriptNo(false)}/>
        }
        {
          [6,7,8].find((e) => e == scriptNo)
            &&
            <img src="/images/static/pairi.png" className="pairi-img" />
          
        }
        {
          [1,2,3,4,9].find((e) => e == scriptNo)
          &&
          <img src="/images/static/profOak.png" className="prof-oak-image" />
        }
        {
          [7,8].find((e) => e == scriptNo)
            &&
            <div>
              <img src="/images/static/newnft.png" className="new-nft-img" />
            </div>
        }
        
        <div 
          className="script-wrapper"
        >
          <span>
            
            { 
              isAllTyped
                ?
                scriptNo === 2
                  ?
                  <>
                    <span>{scriptList[scriptNo]}</span>
                    <div className="tutorial-btn-wrapper">
                      <button className="tutorial-red-btn" onClick={()=> changeScriptNo("red")}> 알고있어요!</button>
                      <button className="tutorial-blue-btn" onClick={()=> changeScriptNo("blue")}> NFT요?</button>
                    </div>
                  </>
                  :
                  <span>{scriptList[scriptNo]}</span>
                :
                <span className="script-to-cursor" onClick={() => setIsAllTyped(true)}>
                  <Typewriter
                    options={{
                      delay: 70
                    }}
                    onInit={(typewriter) => {
                      typewriter.typeString(scriptList[scriptNo])
                        .callFunction(() => {
                          setIsAllTyped(true);
                        })
                        .start();
                    }}
                    
                  />
                </span>
            }
          </span>
          {
            '134678'.includes(scriptNo+"")
            &&
            <div className="down-button-wrapper">
              <FontAwesomeIcon icon={faSortDown} className="tutorial-down-button" onClick={() => changeScriptNo(false)}/>
            </div>
          }
          {
            scriptNo === 9 
            &&
              isAllTyped
              &&
              <Link to="/">
                <div className="down-button-wrapper">
                  <FontAwesomeIcon icon={faSortDown} className="tutorial-down-button" onClick={() => changeScriptNo(false)}/>
                </div>
              </Link>
          }
        </div>
      </div>
    </div>
  );
}
