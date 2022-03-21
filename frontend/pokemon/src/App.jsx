import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { routers, Router } from './Router';

import './App.css';

export default function App() {
  const routerNow = window.location.pathname;

  // Header(Nav Bar)가 안 보여야 하는 페이지 분기를 위함 ex) NotFound, Intro, Tutorial
  let showHeader = false;
  for (let { path } of routers){
    if(routerNow === path){
      if (routerNow !== '/' && routerNow !== '/tutorial') {
        showHeader = true;
        break;
      }
    } else {
      showHeader = false;
    }
  }

  // 모든 path - 우리가 설정한 path => NotFoundPage
  return (
    <BrowserRouter>
      {showHeader 
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
