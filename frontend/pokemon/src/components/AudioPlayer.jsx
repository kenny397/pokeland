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
  
  const dispatch = useDispatch();

  const pathNow = window.location.pathname;
  const bgmSelection = {
    "/" : 'opening.mp3',
    "/tutorial" : 'oaklab.mp3',
    
  };
  const bgmTitle = bgmSelection[pathNow];
  const audioContainer = document.querySelector('#audio-player');

  const onClickAudioStart = () => {
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
        />
        {isMusicMuted 
          ? 
          <FontAwesomeIcon icon={faVolumeXmark} className="audio-btn audio-muted-btn" onClick={onClickAudioStart} />
          :
          <FontAwesomeIcon icon={faVolumeHigh} className="audio-btn" onClick={onClickAudioStart} />
        }
      </div>
  );
}
