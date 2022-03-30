import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Router } from './Router';
import { useSelector } from 'react-redux';

import { useDispatch } from 'react-redux';
import { updateJwt } from './redux/actions';

import NavBar from './mainpage/NavBar';

import './App.scss';
// js
import { decideHeaderDisplay } from './headerDisplay';

export default function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(updateJwt(localStorage.getItem('jwtToken')));
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
    </BrowserRouter>
  );
}
