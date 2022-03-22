import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Router } from './Router';
import { useSelector } from 'react-redux';

import './App.css';

export default function App() {
  
  //window.location.pathname
  // 최초에 uri값에 직접 support나 header값을 들고오는 페이지를 입력할 경우 생각해서 app에서는 다른 컴포를 하나 호출해야되나 생각중입니다...
  const { showHeader } = useSelector((state) => ({
    showHeader: state.showHeader,
  }));
  console.log(showHeader);
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
