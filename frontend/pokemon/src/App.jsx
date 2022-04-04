import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Router } from './Router';
import { useSelector } from 'react-redux';

import { useDispatch } from 'react-redux';
import { updateJwt, updateBalance } from './redux/actions';

import NavBar from './mainpage/NavBar';

import './App.scss';
// js
import { decideHeaderDisplay } from './headerDisplay';

import ReactAudioPlayer from 'react-audio-player';

export default function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(updateJwt(localStorage.getItem('jwt')));
    dispatch(updateBalance(localStorage.getItem('balance')));
  }, []);

  // header, container 표시 or not
  let { headerDisplay } = useSelector((state) => ({
    headerDisplay: state.headerDisplay,
  }));
  
  headerDisplay = headerDisplay || decideHeaderDisplay(window.location.pathname);

  return (
    <BrowserRouter>
      {headerDisplay
        ?
        <>
          <NavBar/>
          <div className="container">
            <Router />
          </div>
        </>
        :
        <Router />
      }
      <iframe src={require('./static/audio/silence.mp3')} allow="autoplay" id="audio" ></iframe>
    </BrowserRouter>
  );
}
