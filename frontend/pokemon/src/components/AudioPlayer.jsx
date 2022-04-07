import React from "react";
import "./BodyBlackoutStyle.scss";

import ReactAudioPlayer from "react-audio-player";

import { useDispatch, useSelector } from "react-redux";
import { setIsMusicMuted } from "../redux/actions";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVolumeHigh,faVolumeXmark } from "@fortawesome/free-solid-svg-icons";

import './AudioPlayer.scss';

export default function AudioPlayer() {
  let { isMusicMuted } = useSelector((state) => ({
    isMusicMuted: state.isMusicMuted,
  }));
  let { gachaOrder } = useSelector((state) => ({
    gachaOrder: state.gachaOrder,
  }));
  let { supportOrder } = useSelector((state) => ({
    supportOrder: state.supportOrder,
  }));
  let { cardgameOrder } = useSelector((state) => ({
    cardgameOrder: state.cardgameOrder,
  }));

  const dispatch = useDispatch();

  const pathNow = window.location.pathname;
  // const bgmSelection = {
  //   "/" : 'intro.mp3',
  //   "/tutorial" : 'tutorial.mp3',
  // };
  const gachaBgm = ['gacha.mp3', 'whilemonsterball.mp3', 'common.mp3', 'rare.mp3', 'unique.mp3'];
  const supportBgm = ['support.mp3', 'supportsuccess.mp3'];
  const cardgameBgm = ['cardgame.mp3', 'cardgamemetamong.mp3', 'cardgamepicachu.mp3'];
  let bgmTitle = null;
  const randomNum = Math.floor(Math.random() * 5) + 1;
  if (pathNow ==='/main') {
    bgmTitle = `main${randomNum}.mp3`;
  } else if (pathNow === '/support') {
    bgmTitle = supportBgm[supportOrder];
  } else if (pathNow === '/gacha') {
    bgmTitle = gachaBgm[gachaOrder];
  } else if (pathNow === '/') {
    bgmTitle = 'intro.mp3';
  } else if (pathNow === '/tutorial') {
    bgmTitle = 'tutorial.mp3';
  } else if (pathNow === '/cardgame') {
    bgmTitle = cardgameBgm[cardgameOrder];
  } else {
    bgmTitle = `main${randomNum}.mp3`;
  }
  const audioContainer = document.querySelector('#audio-player');
  console.log(bgmTitle);

  const onClickAudioSetting = () => {
    if (isMusicMuted) {
      dispatch(setIsMusicMuted(false));
      audioContainer.play();
    } else {
      dispatch(setIsMusicMuted(true));
      audioContainer.pause();
    }
  };
  console.log("isMusicMuted" + isMusicMuted);

  return ( 
    bgmTitle !== undefined &&
      <div className="AudioPlayer">
        <ReactAudioPlayer
          src={require(`../static/audio/${bgmTitle}`)}
          autoPlay={!isMusicMuted}
          loop={true}
          id="audio-player"
          volume={0.2}
        />
        <span className="muted-icon-wrapper" onClick={onClickAudioSetting}>
          {isMusicMuted 
            ? 
            <FontAwesomeIcon icon={faVolumeXmark} className="audio-btn audio-muted-btn"/>
            :
            <FontAwesomeIcon icon={faVolumeHigh} className="audio-btn"/>
          }
        </span>
      </div>
  );
}
