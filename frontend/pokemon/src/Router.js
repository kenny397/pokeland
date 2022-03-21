import React from "react";
import { useRoutes } from "react-router-dom";

import IntroPage from './IntroPage';
import MainPage from './MainPage';
import TutorialPage from './TutorialPage';
import SupportPage from './support/SupportPage';
import NotFound from './NotFound';

const Router = () => {
  let element = useRoutes([
    { path: "/", element: <IntroPage /> },
    { path: "/main", element: <MainPage /> },
    { path: "/tutorial", element: <TutorialPage /> },
    { path: "/support", element: <SupportPage /> },
    { path: "*", element: <NotFound /> }
  ]);

  return element;
};

export default Router;
