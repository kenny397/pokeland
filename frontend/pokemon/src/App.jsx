import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Router } from './Router';
import { useSelector } from 'react-redux';
import { decideHeaderDisplay } from './headerDisplay';
import './App.css';

export default function App() {
  let { headerDisplay } = useSelector((state) => ({
    headerDisplay: state.headerDisplay,
  }));

  headerDisplay = headerDisplay || decideHeaderDisplay(window.location.pathname);

  return (
    <BrowserRouter>
      {headerDisplay
        ?
        <>
          <div>Nav Bar</div>
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
