import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Router } from './Router';
import { useSelector } from 'react-redux';

import NavBar from './NavBar';

import './App.css';
// js
import { decideHeaderDisplay } from './headerDisplay';

export default function App() {

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
