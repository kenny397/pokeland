import React from "react";
import { useRoutes } from "react-router-dom";

import IntroPage from './pages/IntroPage';
import MainPage from './pages/MainPage';
import TutorialPage from './TutorialPage';
import SupportPage from './support/SupportPage';
import NotFound from './pages/NotFound';

export const routers = [
  { path: "/", element: <IntroPage /> },
  { path: "/main", element: <MainPage /> },
  { path: "/tutorial", element: <TutorialPage /> },
  { path: "/support", element: <SupportPage /> },
  { path: "*", element: <NotFound /> }
];

export const Router = () => {
  let element = useRoutes(routers);
  return element;
};
